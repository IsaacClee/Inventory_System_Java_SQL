package Controller;

import DAO.DBContacts;
import DAO.DBCountries;
import DAO.DBFirstLevelDivisions;
import Model.Appointments;
import Model.Countries;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateAppointmentForm implements Initializable {
    @javafx.fxml.FXML
    private TextField appUserIDField;
    @javafx.fxml.FXML
    private TextField appCusIDField;
    @javafx.fxml.FXML
    private TextField appDescriptionField;
    @javafx.fxml.FXML
    private ComboBox appContactField;
    @javafx.fxml.FXML
    private TextField appTitleField;
    @javafx.fxml.FXML
    private DatePicker appEndField;
    @javafx.fxml.FXML
    private TextField appIDField;
    @javafx.fxml.FXML
    private TextField appLocationField;
    @javafx.fxml.FXML
    private DatePicker appStartField;
    @javafx.fxml.FXML
    private TextField appTypeField;

    @javafx.fxml.FXML
    private ComboBox appStartTimeField;
    @javafx.fxml.FXML
    private ComboBox appEndTimeField;

    private Appointments appointmentToBeUpdated = null;

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentToBeUpdated = MainForm.getAppointmentToBeUpdated();

        appIDField.setText(String.valueOf(appointmentToBeUpdated.getId()));
        appTitleField.setText(String.valueOf(appointmentToBeUpdated.getTitle()));
        appDescriptionField.setText(String.valueOf(appointmentToBeUpdated.getDescription()));
        appLocationField.setText(String.valueOf(appointmentToBeUpdated.getLocation()));
        appTypeField.setText(String.valueOf(appointmentToBeUpdated.getType()));
        ObservableList<String> timeSlotsList =
                FXCollections.observableArrayList(
                        "0",
                        "1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        "13",
                        "14",
                        "15",
                        "16",
                        "17",
                        "18",
                        "19",
                        "20",
                        "21",
                        "22",
                        "23"

                );
        appStartTimeField.setItems(timeSlotsList);
        appEndTimeField.setItems(timeSlotsList);
        appContactField.setItems(DBContacts.getAllContacts());
        appContactField.getSelectionModel().select(DBContacts.getContactByID(appointmentToBeUpdated.getContactID()));


        System.out.println(appointmentToBeUpdated.getStart());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instant = appointmentToBeUpdated.getStart().toInstant();
        appStartField.setValue(LocalDate.ofInstant(instant, ZoneId.systemDefault()));

        appCusIDField.setText(String.valueOf(appointmentToBeUpdated.getCustomerID()));
        appUserIDField.setText(String.valueOf(appointmentToBeUpdated.getUserID()));

    }


    @javafx.fxml.FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void onActionUpdateAppointment(ActionEvent actionEvent) {
    }
}
