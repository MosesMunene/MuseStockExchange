package com.muse.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.muse.model.Company;
import com.muse.model.DailyQuote;
import com.muse.model.Stock;

public class DataImporter {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/muse_stock_exchange", "root","");
		
		populateSymbolColumnOfDailyQuotesTable(connection);
	}
	public static void populateSymbolColumnOfDailyQuotesTable(Connection connection) throws SQLException{
		String sql = "select symbol, company from daily_quotes";
		PreparedStatement dailyQuotesSmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet dailyQuuotesRs =  dailyQuotesSmt.executeQuery();
		
		String sql2 = "select company_id, company_name from stock";
		PreparedStatement stockSmt = connection.prepareStatement(sql2);
		ResultSet stockRs = stockSmt.executeQuery();
		
		String updateSql = "update daily_quotes set symbol = ? where company = ?";
		PreparedStatement updateSmt = connection.prepareStatement(updateSql);
		
		System.out.println("Starting...");
		while(stockRs.next()){
			String stockCompanyString = stockRs.getString(2).toUpperCase();
			String symbol = stockRs.getString(1).toUpperCase();
			while(dailyQuuotesRs.next()){
				String qouteCompanyString = dailyQuuotesRs.getString(2).toUpperCase();
				if(qouteCompanyString.matches(stockCompanyString)){
					System.out.println(qouteCompanyString + " from quotes  matches "+  stockCompanyString +" from stock");
					System.out.println("Setting symbol as " + symbol);
					updateSmt.setString(1, symbol);
					updateSmt.setString(2, dailyQuuotesRs.getString(2));
					updateSmt.execute();
					break;
				}
				else{
					//System.out.println("no match");
				}
			}
			dailyQuuotesRs.beforeFirst();
		}
		
	}
	
	public static void importDataFromFiles(Connection connection) throws IOException, SQLException{
		DirectoryStream<Path> stream = Files
				.newDirectoryStream(Paths.get("C:\\Users\\mfieldwork\\Desktop\\Market data\\"));
		//readQuotesFromFile(Paths.get("C:\\Users\\mfieldwork\\Desktop\\Market data\\Market Data (10).xlsx"));
		for (Path entry : stream) {
			System.out.println("processing " + entry.toString());
//			readQuotesFromFile(entry);
			saveDailyQuotesToDb(connection, readQuotesFromFile(entry));
		}
		System.out.println("Import Success!!");
	}

	public static List<DailyQuote> readQuotesFromFile(Path path) {

		List<DailyQuote> dailyQuotes = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(path.toFile()); XSSFWorkbook wb = new XSSFWorkbook(fis);) {

			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				XSSFSheet sheet = wb.getSheetAt(k);
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows + " row(s).");
				for (int r = 1; r < rows; r++) { // ignore first row.
													// Contains title
					XSSFRow row = sheet.getRow(r);
					if (row == null) {
						continue;
					}

					int cells = row.getPhysicalNumberOfCells();
					DailyQuote dailyQuote = new DailyQuote();
					for (int c = 1; c < cells; c++) { // skip first cell. It
														// just contains an
														// index
						XSSFCell cell = row.getCell(c);
						
						if (c == 1 && cell.getCellType() == XSSFCell.CELL_TYPE_STRING && (null == cell.getStringCellValue()|| cell.getStringCellValue().equals(""))){
							System.out.println("continuing");
							continue;
						}

						if (c == 1 && cell.getCellType() == XSSFCell.CELL_TYPE_STRING && null != cell.getStringCellValue()) {
							
							Company company = new Company();
							company.setName(cell.getStringCellValue());
							Stock stock = new Stock();
							stock.setCompany(company);
							dailyQuote.setStock(stock);
							//System.out.println(dailyQuote.getStock().getCompany().getName());
						}
						
						if (c == 2 && cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							dailyQuote.setDate(
									LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ISO_LOCAL_DATE));
						}

						if (c == 3 && cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							dailyQuote.setOpen(cell.getNumericCellValue());
						}

						if (c == 4 && cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							dailyQuote.setHigh(cell.getNumericCellValue());
						}

						if (c == 5 && cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							dailyQuote.setLow(cell.getNumericCellValue());
						}

						if (c == 6 && cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							dailyQuote.setClose(cell.getNumericCellValue());
						}

						if (c == 7 && cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							dailyQuote.setVolume((long) cell.getNumericCellValue());
						}

					}
					dailyQuotes.add(dailyQuote);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dailyQuotes;
	}

	public static void saveDailyQuotesToDb(Connection connection, List<DailyQuote> dailyQuotes) throws SQLException {
		for (DailyQuote quote : dailyQuotes) {
			PreparedStatement smt = connection.prepareStatement(
					"insert into daily_quotes (company, quote_date, open, high, low, close, volume) values ( ?,?,?,?,?,?,?) ");
			if (null == quote.getStock()) {
				continue; // do not save blank rows. //Find out why this is happening. Bad programming
			}
			
			System.out.println("Saving for " + quote.getStock().getCompany().getName());
			smt.setString(1, quote.getStock().getCompany().getName());
			smt.setDate(2, new java.sql.Date(
					Date.from(quote.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
			smt.setDouble(3, quote.getOpen());
			smt.setDouble(4, quote.getHigh());
			smt.setDouble(5, quote.getLow());
			smt.setDouble(6, quote.getClose());
			smt.setDouble(7, quote.getVolume());
			smt.execute();
		}
	}
}
