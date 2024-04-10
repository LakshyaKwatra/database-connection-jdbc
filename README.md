# Database Connector JDBC


1. ```import java.sql.*```
2. Load and register the driver (com.mysql.jdbc.Driver).
   1. IntelliJ &rarr; Project Structure &rarr; Modules &rarr; Dependencies &rarr; Add mysql connector
   2. Manual Loading: ```Class.forName("com.mysql.jdbc.Driver");```
   3. No need for manual loading as the driver is automatically registered via **SPI (Service Provider Interface)**.
   4. In the context of JDBC drivers, the JDBC API provides a standard interface for database connectivity.
   5. The SPI allows JDBC drivers to be dynamically discovered and loaded by the Java runtime environment.
3. Establish a connection to the MySQL database and get the **Connection** object: `DriverManager.getConnection(url, username, password);`
4. Create a **Statement** object from connection object for executing SQL queries `connection.createStatement();`
5. Execute the queries. 
   1. `statement.executeQuery(query)`: used for SELECT statements and returns a ResultSet. 
   2. `statement.executeUpdate(query)`: used for INSERT, UPDATE, DELETE statements and returns an int representing the number of rows affected.
   3. `statement.execute(query)`: can be used to execute any SQL statement, including SELECT, INSERT, UPDATE, DELETE, and DDL (Data Definition Language) statements. Returns a boolean value indicating the type of the first result, where true indicates the result is a ResultSet (for queries), and false indicates it is an update count or there are no results.
6. Process the results.
7. Close the connection.



* When we create an object of class Blocks `Blocks blocks = new Blocks();`, the code inside both **static** and **instance** blocks gets executed.
* When we do `Class.forName("example.Blocks");`, then only the code inside the **static block** gets executed.
* When we do `Class.forName("example.Blocks").newInstance();`, then a new instance gets created and both the blocks get executed.


