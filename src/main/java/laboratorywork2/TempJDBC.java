package laboratorywork2;

import java.sql.*;

public class TempJDBC {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
         // Load the JDBC driver
         Class.forName("org.postgresql.Driver");
         System.out.println("Driver loaded");

         // Connect to a database
         Connection connection = DriverManager.getConnection
                 ("jdbc:postgresql://localhost:5432/Music_shop", "postgres", "123");
         System.out.println("Database connected");

         // Create a statement
         Statement statement = connection.createStatement();

         // Execute a statement
         ResultSet resultSet = statement.executeQuery
         ("select * from musician where name_musician like '_е%' and musician_id in (222222, 111111)");

         // Iterate through the result and print composition names and duration
         while (resultSet.next())
             System.out.println(resultSet.getString(1) + "\t" +
                 resultSet.getString(2));

        showMusicianName(statement);

        // Insert in table musician new value
         int res = statement.executeUpdate("insert into musician(name_musician) values('Sidorov')");

        showMusicianName(statement);
         // Update value in table musician
         res = statement.executeUpdate("update musician set name_musician = 'Sidorov1' " +
                 "where name_musician = 'Sidorov'");

        showMusicianName(statement);
         // Delete from table musician
         res = statement.executeUpdate("delete from musician where name_musician = 'Sidorov1'");

        showMusicianName(statement);
         // Close the connection
         connection.close();
         }

    private static void showMusicianName(Statement statement) throws SQLException {
        System.out.println("\nИмена музыкантов:");
        ResultSet resultSet;
        String sqlQueryFromMusician = "select * from musician";
        resultSet = statement.executeQuery(sqlQueryFromMusician);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }
}
