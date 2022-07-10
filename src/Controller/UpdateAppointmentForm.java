package Controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCountries;
import DAO.DBFirstLevelDivisions;
import Model.Appointments;
import Model.Countries;
import Model.Customers;
import Model.HoursInterface;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @javafx.fxml.FXML
    private ComboBox appStartTimeMinField;
    @javafx.fxml.FXML
    private ComboBox appEndTimeMinField;

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

        appStartTimeField.setItems(HoursInterface.hoursInterface());
        appEndTimeField.setItems(HoursInterface.hoursInterface());
        ObservableList<String> minutesSlotList = FXCollections.observableArrayList(
                "0",
                    "15",
                    "30",
                    "45"
        );
        appStartTimeMinField.setItems(minutesSlotList);
        appEndTimeMinField.setItems(minutesSlotList);

        appContactField.setItems(DBContacts.getAllContacts());
        appContactField.getSelectionModel().select(DBContacts.getContactByID(appointmentToBeUpdated.getContactID()));


        System.out.println(appointmentToBeUpdated.getStart());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instantStart = appointmentToBeUpdated.getStart().toInstant();
        appStartField.setValue(LocalDate.ofInstant(instantStart,ZoneId.systemDefault()));
        appStartTimeField.getSelectionModel().select(instantStart.atZone(ZoneId.systemDefault()).getHour());
        appStartTimeMinField.getSelectionModel().select((instantStart.atZone(ZoneId.systemDefault()).getMinute()/15));
        Instant instantEnd = appointmentToBeUpdated.getEnd().toInstant();
        appEndField.setValue(LocalDate.ofInstant(instantEnd,ZoneId.systemDefault()));
        appEndTimeField.getSelectionModel().select(instantEnd.atZone(ZoneId.systemDefault()).getHour());
        appEndTimeMinField.getSelectionModel().select((instantEnd.atZone(ZoneId.systemDefault()).getMinute()/15));
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
    public void onActionUpdateAppointment(ActionEvent actionEvent) throws SQLException, IOException {
        int id = Integer.parseInt(appIDField.getText());
        String title = appTitleField.getText();
        String description = appDescriptionField.getText();
        String location = appLocationField.getText();
        String type = appTypeField.getText();
        Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
        String lastUpdateBy = "user script";
        int contactId = DBContacts.getContactIDByName(String.valueOf(appContactField.getValue()));
        int startTime = Integer.parseInt((String) appStartTimeField.getSelectionModel().getSelectedItem());
        int startTimeMin = Integer.parseInt((String) appStartTimeMinField.getSelectionModel().getSelectedItem());
        LocalDateTime localDateTimeStart = appStartField.getValue().atTime(startTime,startTimeMin);
        Timestamp start = Timestamp.valueOf(localDateTimeStart);
        int endTime = Integer.parseInt((String) appEndTimeField.getSelectionModel().getSelectedItem());
        int endTimeMin = Integer.parseInt((String) appEndTimeMinField.getSelectionModel().getSelectedItem());
        LocalDateTime localDateTimeEnd = appEndField.getValue().atTime(endTime,endTimeMin);
        Timestamp end = Timestamp.valueOf(localDateTimeEnd);
        int customerID = Integer.parseInt(appCusIDField.getText());
        int userID = Integer.parseInt(appUserIDField.getText());

        boolean overlappingAppointmentFound = false;
        Instant suggestedAppointmentStartTime = start.toInstant();
        Instant suggestedAppointmentEndTime = end.toInstant();
        System.out.println(suggestedAppointmentStartTime);
        System.out.println(suggestedAppointmentEndTime);
        for(Appointments a : DBAppointments.getAllAppointments()){
            Instant existingAppointmentStartInstant = a.getStart().toInstant();
            Instant existingAppointmentEndInstant = a.getEnd().toInstant();
            if(suggestedAppointmentEndTime.isAfter(existingAppointmentStartInstant) && suggestedAppointmentEndTime.isBefore(existingAppointmentEndInstant)){
                overlappingAppointmentFound = true;
            } else if(suggestedAppointmentStartTime.isAfter(existingAppointmentStartInstant) && suggestedAppointmentStartTime.isBefore(existingAppointmentEndInstant)){
                overlappingAppointmentFound = true;
            } else if(suggestedAppointmentStartTime.equals(existingAppointmentStartInstant) || suggestedAppointmentEndTime.equals(existingAppointmentEndInstant)){
                overlappingAppointmentFound = true;
            }
        }
        if(overlappingAppointmentFound == true){
            System.out.println("Issue");
        } else {

            int rowsAffected = DBAppointments.update(id, title, description, location, type, start, end, lastUpdate, lastUpdateBy, customerID, contactId, userID);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Success:" + "ID: " + id + " was updated");
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
                stage.setTitle("Customer Management System");
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                System.out.println("Failed");
            }

        }

    }
}
