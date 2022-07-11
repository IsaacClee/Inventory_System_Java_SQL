package Model;

import java.io.*;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import DAO.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MAIN APPLICATION
 * Used to launch application and establish Database Connection
 */
public class Main extends Application {

    /**
     * Launch first View
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }




    public static void main(String[] args) throws SQLException, IOException {

        // Acquire language alternatives
        ResourceBundle rb = ResourceBundle.getBundle("Model/Nat_fr", Locale.getDefault());

        // Open Connection
        JDBC.openConnection();

        // Launch Args
        launch(args);

        // Close Connection
        JDBC.closeConnection();

    }
}

