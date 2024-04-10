package dao;

import config.DbConfig;
import entity.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDao {
    private Connection connection = null;
    public void connect() {
        try {
            connection = DriverManager.getConnection(DbConfig.url, DbConfig.username, DbConfig.password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Movie getById(int id) {
        Movie movie = new Movie();
        try {
            String query = "select * from Movie where id = "+ id;
            connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDirector(resultSet.getString(3));
                movie.setYear(resultSet.getInt(4));
                movie.setRating(resultSet.getFloat(5));
                statement.close();
                connection.close();
                return movie;
            }
            statement.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movie;
    }

    public void addMovie(Movie movie) {
        String query = "INSERT INTO Movie VALUES(?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement;
        try {
            connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setString(3, movie.getDirector());
            preparedStatement.setInt(4, movie.getYear());
            preparedStatement.setFloat(5, movie.getRating());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
