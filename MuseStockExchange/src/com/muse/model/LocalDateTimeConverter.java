package com.muse.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDateTime date) {
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date value) {
       
        return LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
    }
}