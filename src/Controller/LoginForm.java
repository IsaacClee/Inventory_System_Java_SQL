package Controller;

import DAO.DBUsers;
import Model.CheckUserInterface;
import Model.HoursInterface;
import Model.Users;
import javafx.collections.FXCollections;
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
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
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


    /**
     * initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTimeZone.setText(String.valueOf(ZoneId.systemDefault()));
        ResourceBundle rb = ResourceBundle.getBundle("Model/Nat_fr", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")){
            loginGreeting.setText(rb.getString("Welcome"));
            loginButton.setText(rb.getString("Login"));
            timeZoneLabel.setText(rb.getString("TimeZone"));
            userIdLabel.setText(rb.getString("UserID"));
            passwordLabel.setText(rb.getString("Password"));
            userIdField.setPromptText(rb.getString("EnterUserID"));
            passwordField.setPromptText(rb.getString("EnterPassword"));
        }
    }

    /**
     * Action Event - used to validate and submit logins
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionLogin(ActionEvent actionEvent) throws IOException {


        //!!!!!!!!! LAMBDA EXPRESSION CASE 3 !!!!!!!!!

        /**
         * Lambda Expression 3
         * Used to reference database and check a user id and password match
         * LAMBDA Justification:
         * 1. Login form requires a single-instant non-dynamic matching of data-base stored user id and password credentials
         * 2. Used only once and does not require another function call
         * 3. Must always be called to validate user credentials
         * 4. Anonymous function (lambda expression) supports security through obscurity best practice
         */

        CheckUserInterface checkUserLoginAttempt = () -> {
            boolean checkUser = false;
            String userID = userIdField.getText();
            String password = passwordField.getText();
            ObservableList<Users> usersList = DBUsers.getAllUsers();
            for(Users u : usersList){
                if(password.contains(u.getPassword()) && userID.contains(u.getName())){
                    checkUser = true;
                }
            }
            return checkUser;
        };
        // execute lambda expression

        boolean checkUser = checkUserLoginAttempt.checkUserLogin();
        String userID = userIdField.getText();
        String password = passwordField.getText();

        if(checkUser == true){
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("User: " + userID + " logged in successfully. Timestamp:" + Timestamp.from(Instant.now()) + " "  + ZoneId.systemDefault());
            pw.close();
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            stage.setTitle("Customer Management System");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            if(Locale.getDefault().getLanguage().equals("fr")){
                ResourceBundle rb = ResourceBundle.getBundle("Model/Nat_fr", Locale.getDefault());
                JOptionPane.showMessageDialog(null, rb.getString("Error"));
                loginGreeting.setText(rb.getString("Welcome"));
            } else {
                FileWriter fw = new FileWriter("login_activity.txt", true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("User: " + userID + " attempted to login but was unsuccessful. Timestamp:"
                        + Timestamp.from(Instant.now()) + " "  + ZoneId.systemDefault());
                pw.close();
                JOptionPane.showMessageDialog(null, "Please use a valid User ID and Password to proceed.");
            }
        }

    }


}
