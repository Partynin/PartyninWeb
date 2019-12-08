package labratorywork2_4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Задание 4
 * Для задания 2 написать интерфейс пользователя, основанный на Swing.
 */

public class Task2GUI extends Application {

    // PreparedStatement for executing queries
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    private PreparedStatement preparedStatement3;
    private PreparedStatement preparedStatement4;
    private PreparedStatement preparedStatement5;
    private Button buttonSubmit = new Button("Submit");
    private TextField textFieldString = new TextField("_е%");
    private TextField textFieldMusicianId1 = new TextField("111111");
    private TextField textFieldMusicianId2 = new TextField("222222");
    private TextArea textAreaForDisplayResult = new TextArea();
    private TextField textFieldInsert = new TextField("Sidorov2");
    private Button buttonInsert = new Button("Insert");
    private TextField textFieldUpdate = new TextField("Sidorov2");
    private Button buttonUpdate = new Button("Update");
    private TextField textFieldDelete = new TextField("Sidorov3");
    private Button buttonDelete = new Button("Delete");

    private Pane mainPane() {
        VBox mainPane = new VBox(5);
        HBox hBoxForFirstQuery = new HBox(5);
        Pane paneForTextArea = new Pane();
        textAreaForDisplayResult.setMinWidth(430);
        textAreaForDisplayResult.setPrefHeight(300);
        paneForTextArea.getChildren().add(textAreaForDisplayResult);
        textFieldString.setPrefWidth(60);
        Label lb1 = new Label("select * from music_shop.musician where name_musician like", textFieldString);
        lb1.setContentDisplay(ContentDisplay.RIGHT);
        textFieldMusicianId1.setPrefWidth(80);
        textFieldMusicianId2.setPrefWidth(80);
        hBoxForFirstQuery.getChildren().addAll(lb1, textFieldMusicianId1, textFieldMusicianId2, buttonSubmit);
        HBox hBoxForInsertQuery = new HBox(5);
        Label lb2 = new Label("insert into music_shop.musician(name_musician) values(?) ", textFieldInsert);
        lb2.setContentDisplay(ContentDisplay.RIGHT);
        hBoxForInsertQuery.getChildren().addAll(lb2, buttonInsert);

        HBox hBoxForUpdateQuery = new HBox(5);
        Label lb3 = new Label("update music_shop.musician set name_musician = 'Sidorov3' where name_musician = ",
                textFieldUpdate);
        lb3.setContentDisplay(ContentDisplay.RIGHT);
        hBoxForUpdateQuery.getChildren().addAll(lb3, buttonUpdate);

        HBox hBoxForDeleteQuery = new HBox(5);
        Label lb4 = new Label("delete from music_shop.musician where name_musician = ", textFieldDelete);
        lb4.setContentDisplay(ContentDisplay.RIGHT);
        hBoxForDeleteQuery.getChildren().addAll(lb4, buttonDelete);


        mainPane.getChildren().addAll(hBoxForFirstQuery, hBoxForInsertQuery, hBoxForUpdateQuery, hBoxForDeleteQuery,
                paneForTextArea);

        return mainPane;
    }

    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane(), 680, 430);
        primaryStage.setTitle("Task2GUI"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        initializeDB();

        buttonSubmit.setOnAction(e -> {
            try {
                preparedStatement.setString(1, textFieldString.getText());
                preparedStatement.setInt(2, Integer.parseInt(textFieldMusicianId1.getText()));
                preparedStatement.setInt(3, Integer.parseInt(textFieldMusicianId2.getText()));
                ResultSet rset = preparedStatement.executeQuery();

                // Iterate through the result and print musician names and duration into textArea
                while (rset.next())
                    textAreaForDisplayResult.appendText(rset.getString(1) + "\t" +
                            rset.getString(2) + "\n");
            } catch (Exception ex) {
                System.out.println("Error during execute submit button");
                ex.printStackTrace();
            }
        });

        buttonInsert.setOnAction(e -> {
            try {
                preparedStatement2.setString(1, textFieldInsert.getText());
                preparedStatement2.executeUpdate();

                showMusicians();
            } catch (Exception ex) {
                System.out.println("Error during execute insert button");
                ex.printStackTrace();
            }
        });

        buttonUpdate.setOnAction(e -> {
            try {
                preparedStatement3.setString(1, textFieldUpdate.getText());
                preparedStatement3.executeUpdate();

                showMusicians();
            } catch (Exception ex) {
                System.out.println("Error during execute update button");
                ex.printStackTrace();
            }
        });

        buttonDelete.setOnAction(e -> {
            try {
                preparedStatement4.setString(1, textFieldDelete.getText());
                preparedStatement4.executeUpdate();

                showMusicians();
            } catch (Exception ex) {
                System.out.println("Error during execute delete button");
                ex.printStackTrace();
            }
        });
    }

    private void showMusicians() {
        try {
            ResultSet resultSet = preparedStatement5.executeQuery();
            textAreaForDisplayResult.clear();
            // Iterate through the result and print musician names and duration into textArea
            while (resultSet.next())
                textAreaForDisplayResult.appendText(resultSet.getString(1) + "\t" +
                        resultSet.getString(2) + "\n");
        } catch (SQLException e) {
            System.out.println("Error during showMusicians method");
            e.printStackTrace();
        }
    }

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

            preparedStatement5 = connection.prepareStatement("select * from music_shop.musician");
        } catch (Exception ex) {
            System.out.println("Error in initializeDB method!!!");
            ex.printStackTrace();
        }
    }
}
