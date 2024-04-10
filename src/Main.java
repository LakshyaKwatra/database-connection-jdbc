import config.DbConfig;
import dao.MovieDao;
import entity.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
* 1. Import java.sql.*
* 2. Load and register the driver (com.mysql.jdbc.Driver).
*    IntelliJ -> Project Structure -> Modules -> Add mysql connector
* 3. Establish a connection to the MySQL database (DriverManager.getConnection).
* 4. Create a statement object for executing SQL queries (connection.createStatement()).
* 5. Execute the queries (statement.execute).
* 6. Process the results.
* 7. Close the connection.
* */
public class Main {
    public static void main(String[] args) throws Exception {

        Class.forName("example.Blocks"); // this will print "Inside static block."

        /* Load the MySQL JDBC driver class.
        *  Manual Loading: Class.forName("com.mysql.jdbc.Driver");
        *  No need for manual loading as the driver is automatically registered via SPI (Service Provider Interface).
        *  In the context of JDBC drivers, the JDBC API provides a standard interface for database connectivity.
        *  The SPI allows JDBC drivers to be dynamically discovered and loaded by the Java runtime environment.
        **/

        // 1. Establish a connection to the MySQL database.
        Connection connection = DriverManager.getConnection(DbConfig.url, DbConfig.username, DbConfig.password);

        // 2. Create a statement object for executing SQL queries.
        Statement statement = connection.createStatement();

        // 3. Create a table named "movie" with columns: id, title, director, release_year, rating.
        statement.execute("CREATE TABLE movie (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255) NOT NULL, director VARCHAR(255), release_year INT, rating DECIMAL(3,1));");

        // 4. Insert data into the "movie" table.
        statement.execute("INSERT INTO movie (title, director, release_year, rating) VALUES ('The Shawshank Redemption', 'Frank Darabont', 1994, 9.3);");

        // Using DAO design pattern for easy data access.
        MovieDao movieDao = new MovieDao();
        movieDao.addMovie(new Movie(2,"The Godfather", "Francis Ford Coppola", 1972, 9.2f));

        // 5. Execute a query to retrieve data from the "Movie" table.
        ResultSet resultSet = statement.executeQuery("select * from movie;");

        // 6. Iterate over the result set and print the id and title of each movie.
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
        }

        statement.execute("DROP DATABASE database_connection;");

        // 7. Close the statement and connection to release resources.
        statement.close();
        connection.close();
    }
}