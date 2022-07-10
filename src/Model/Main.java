package Model;

import java.io.*;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import DAO.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        // stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }




    public static void main(String[] args) throws SQLException, IOException {

        ResourceBundle rb = ResourceBundle.getBundle("Model/Nat_fr", Locale.getDefault());

        // Open Connection
        JDBC.openConnection();


        ZoneId userZoneId = ZoneId.systemDefault();
        System.out.println(userZoneId);

        // Launch Args
        launch(args);


        JDBC.closeConnection();

    }
}

