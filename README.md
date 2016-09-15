# MuseStockExchange

#Synopsis
A stock exchange platform modeled after the Narirobi Securities Exchange. 
This provides the REST API. See https://github.com/MosesMunene/MuseStockExchange-web for web client.

#Motivation
 - To experiment with Web Services and get familirized with pitfalls, gotchas and design patterns of the approach.
 - To brush up on JavaEE features (try new approaches)
 - To experiment with AngularJS (1.5.8) and get familirized with pitfalls, gotchas and design patterns of the approach.
 - Also, I am tired of Java Server Faces.
  
#Criticism and comments 
 These are very much welcomed. Post them as isssues and they will be worked on
 
# Dev Tools
 - Eclipse (Currently using Mars2 with GlassFish tools)
 - GlassFish 4.1
 
#Procedure
 - Clone this repo on eclipse, 
 - install GlassFish 4.1 
 - Patch Glassfish by downloading jackson-module-jaxb-annotations-2.3.2.jar and putting it into the modules directory,
 - Run muse_stock_exchange_ddl.sql  found in META_INF in Mysql
 - Run muse_stock_exchange_initial.sql found in META_INF in Mysql
 
#Tests
 Coming soon...
