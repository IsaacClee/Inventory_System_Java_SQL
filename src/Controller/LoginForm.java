package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;


public class LoginForm implements Initializable {
    @javafx.fxml.FXML
    private Label timeZoneLabel;
    @javafx.fxml.FXML
    private Label loginTimeZone;
    @javafx.fxml.FXML
    private Label loginGreeting;
    @javafx.fxml.FXML
    private TextField userIdField;
    @javafx.fxml.FXML
    private Button loginButton;
    @javafx.fxml.FXML
    private TextField passwordField;
    @javafx.fxml.FXML
    private Label userIdLabel;
    @javafx.fxml.FXML
    private Label passwordLabel;

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTimeZone.setText(String.valueOf(ZoneId.systemDefault()));
    }

    @FXML
    public void onActionLogin(ActionEvent actionEvent) throws IOException {
        int userID = Integer.parseInt(userIdField.getText());
        String password = passwordField.getText();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
        stage.setTitle("Customer Management System");
        stage.setScene(new Scene(scene));
        stage.show();
    }


}
