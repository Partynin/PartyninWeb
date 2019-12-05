package laboratorywork2;

import java.sql.*;

public class TryJDBC {

    // PreparedStatement for executing queries
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    private PreparedStatement preparedStatement3;
    private PreparedStatement preparedStatement4;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /** Задание 1
         Используя JDBC, подключиться к БД, созданной в лабораторной работе №1. Продемонстрировать выполнение
         одного любого запроса из лабораторной работы 1, а также добавление, удаление и редактирование данных
         в одной из таблиц.
         */

        // Load the JDBC driver
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded");

        // Connect to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://balarama.db.elephantsql.com/fbxkjxci", "fbxkjxci",
                        "BeLgcnKykF5xfpvEYIpJaaYzzxAq40Bn");
        System.out.println("Database connected");

        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
                ("select * from music_shop.musician where name_musician like '_е%' and" +
                        " musician_id in (222222, 111111)");

        // Iterate through the result and print musician names and duration
        while (resultSet.next())
            System.out.println(resultSet.getString(1) + "\t" +
                    resultSet.getString(2));

        showMusicianName(statement);

        // Insert in table musician new value
        int res = statement.executeUpdate("insert into music_shop.musician(name_musician) values('Sidorov')");

        showMusicianName(statement);
        // Update value in table musician
        res = statement.executeUpdate("update music_shop.musician set name_musician = 'Sidorov1' " +
                "where name_musician = 'Sidorov'");

        showMusicianName(statement);
        // Delete from table musician
        res = statement.executeUpdate("delete from music_shop.musician where name_musician = 'Sidorov1'");

        showMusicianName(statement);
        // Close the connection
        connection.close();

        // Задание 2
        System.out.println("\nЗадание 2");
        TryJDBC tryJDBCObject = new TryJDBC();
        tryJDBCObject.initializeDB();
        tryJDBCObject.startPreparedStatement();

        // Задание 3
        System.out.println("\nЗадание 3");
        tryJDBCObject.getMetadata();
    }

    private static void showMusicianName(Statement statement) throws SQLException {
        System.out.println("\nИмена музыкантов:");
        ResultSet resultSet;
        String sqlQueryFromMusician = "select * from music_shop.musician";
        resultSet = statement.executeQuery(sqlQueryFromMusician);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
    }

    /**
     * Задание 2
     * Выполнить задание 1, используя предварительную подготовку SQL запросов с последующей подстановкой в них значений.
     */

    private void initializeDB() {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded 2");

            // Establish a connection
            Connection connection = DriverManager.getConnection
                    ("jdbc:postgresql://balarama.db.elephantsql.com/fbxkjxci", "fbxkjxci",
                            "BeLgcnKykF5xfpvEYIpJaaYzzxAq40Bn");
            System.out.println("Database connected 2");

            String queryString = "select * from music_shop.musician where name_musician like ? " +
                    "and musician_id in (?, ?)";

            // Create a statement
            preparedStatement = connection.prepareStatement(queryString);

            // Insert in table musician new value
            preparedStatement2 = connection.prepareStatement("insert into music_shop.musician(name_musician) " +
                    "values(?)");
            preparedStatement3 = connection.prepareStatement("update music_shop.musician set name_musician =" +
                    " 'Sidorov3' where name_musician = ?");
            preparedStatement4 = connection.prepareStatement("delete from music_shop.musician where name_musician " +
                    "= ?");

        } catch (Exception ex) {
            System.out.println("Error in initializeDB method!!!");
            ex.printStackTrace();
        }
    }

    private void startPreparedStatement() {
        try {
            preparedStatement.setString(1, "_е%");
            preparedStatement.setInt(2, 111111);
            preparedStatement.setInt(3, 222222);
            ResultSet rset = preparedStatement.executeQuery();

            // Iterate through the result and print musician names and duration
            while (rset.next())
                System.out.println(rset.getString(1) + "\t" +
                        rset.getString(2));

            preparedStatement2.setString(1, "Sidorov2");
            preparedStatement2.executeUpdate();
            preparedStatement3.setString(1, "Sidorov2");
            preparedStatement3.executeUpdate();
            preparedStatement4.setString(1, "Sidorov3");
            preparedStatement4.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error in startPreparedStatement");
            ex.printStackTrace();
        }
    }

    /**
     * Задание 3
     * Используя метаданные, распечатать названия всех таблиц БД и названия всех столбцов одной из таблиц.
     */
    public void getMetadata() {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded 3");

            // Connect to a database
            Connection connection = DriverManager.getConnection
                    ("jdbc:postgresql://balarama.db.elephantsql.com/fbxkjxci", "fbxkjxci",
                            "BeLgcnKykF5xfpvEYIpJaaYzzxAq40Bn");
            System.out.println("Database connected 3");

            // Create a statement
            Statement statement = connection.createStatement();

            DatabaseMetaData dbMetaData = connection.getMetaData();

            ResultSet rsTables = dbMetaData.getTables(null, null, null,
                    new String[]{"TABLE"});
            System.out.print("User tables: ");
            while (rsTables.next())
                System.out.print(rsTables.getString("TABLE_NAME") + " ");

            // Execute a statement
            ResultSet resultSet = statement.executeQuery
                    ("select * from music_shop.composition");

            System.out.print("\nTable composition columns: ");
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
                System.out.printf("%-12s\t", rsMetaData.getColumnName(i));
            System.out.println();

            // Close the connection
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error in getMetadata method");
            ex.printStackTrace();
        }
    }
}
