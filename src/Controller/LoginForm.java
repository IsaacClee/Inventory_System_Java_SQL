package Controller;

import DAO.DBUsers;
import Model.Users;
import javafx.collections.ObservableList;
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

import javax.swing.*;
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
        boolean checkUser = false;
        String userID = userIdField.getText();
        String password = passwordField.getText();
        System.out.println(userID);
        System.out.println(password);
        ObservableList<Users> usersList = DBUsers.getAllUsers();
        for(Users u : usersList){
            if(password.contains(u.getPassword()) && userID.contains(Integer.toString(u.getId()))){
                checkUser = true;
            }
        }
        System.out.println(checkUser);
        if(checkUser == true){
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            stage.setTitle("Customer Management System");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            JOptionPane.showMessageDialog(null, "Please use a valid User ID and Password to proceed.");
        }

    }


}
