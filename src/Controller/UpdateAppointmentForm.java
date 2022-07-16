package Controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCountries;
import DAO.DBFirstLevelDivisions;
import Model.*;
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
import java.time.*;
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

    /**
     * initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Updates Form with Appointment selected in MainForm
        appointmentToBeUpdated = MainForm.getAppointmentToBeUpdated();
        appIDField.setText(String.valueOf(appointmentToBeUpdated.getId()));
        appTitleField.setText(String.valueOf(appointmentToBeUpdated.getTitle()));
        appDescriptionField.setText(String.valueOf(appointmentToBeUpdated.getDescription()));
        appLocationField.setText(String.valueOf(appointmentToBeUpdated.getLocation()));
        appTypeField.setText(String.valueOf(appointmentToBeUpdated.getType()));


        //!!!!!!!!! LAMBDA EXPRESSION CASE 1 !!!!!!!!!

        /**
         * Lambda Expression 1
         * Used to populate combo boxes used by Form
         * LAMBDA Justification: This population requires single-instant non-dynamic population required to setup GUI interface
         * Used to isolate code function of a observable list which increases readability and supports DRY principles
         * Used to eliminate a static input list, best use case for an anonymous variable
         */
        HoursInterface hoursUpdate = () -> {
            ObservableList<String> timeSlotsList = FXCollections.observableArrayList(
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

            ObservableList<String> minutesSlotList = FXCollections.observableArrayList(
                    "0",
                    "15",
                    "30",
                    "45"
            );
            appStartTimeMinField.setItems(minutesSlotList);
            appEndTimeMinField.setItems(minutesSlotList);

        };
        // execute lambda expression
        hoursUpdate.hoursListPopulateInterface();

        //Setup textFields
        appContactField.setItems(DBContacts.getAllContacts());
        appContactField.getSelectionModel().select(DBContacts.getContactByID(appointmentToBeUpdated.getContactID()));
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

    /**
     * Action Event - Used to cancel update and return to Main Form
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Action Event - Validates and submits update
     * Used to Update Appointment to database
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
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

        // Get Start Time
        int startTime = Integer.parseInt((String) appStartTimeField.getValue());
        int startTimeMin = Integer.parseInt((String) appStartTimeMinField.getValue());
        LocalDateTime localDateTimeStart = appStartField.getValue().atTime(startTime,startTimeMin);
        Timestamp start = Timestamp.valueOf(localDateTimeStart);

        if(TimeZoneConversion.checkESTSchedule(localDateTimeStart) == false){
            JOptionPane.showMessageDialog(null,
                    "Please select another Start Date & Time");
            return;
        };

        // Get End Time
        int endTime = Integer.parseInt((String) appEndTimeField.getSelectionModel().getSelectedItem());
        int endTimeMin = Integer.parseInt((String) appEndTimeMinField.getSelectionModel().getSelectedItem());
        LocalDateTime localDateTimeEnd = appEndField.getValue().atTime(endTime,endTimeMin);
        Timestamp end = Timestamp.valueOf(localDateTimeEnd);

        if(TimeZoneConversion.checkESTSchedule(localDateTimeEnd) == false){
            JOptionPane.showMessageDialog(null,
                    "Please select another End Date & Time");
            return;
        };

        if(!localDateTimeEnd.isAfter(localDateTimeStart)){
            JOptionPane.showMessageDialog(null,
                    "We cannot schedule this appointment. " +
                            "The appointment start is during or before the appointment end."
            );
            return;
        }


        int customerID = Integer.parseInt(appCusIDField.getText());
        int userID = Integer.parseInt(appUserIDField.getText());
        boolean overlappingAppointmentFound = false;

        Instant suggestedAppointmentStartTime = start.toInstant();
        Instant suggestedAppointmentEndTime = end.toInstant();
        ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
        ObservableList<Appointments> otherExistingAppointments = FXCollections.observableArrayList();
        for (Appointments a : allAppointments){
            if(a.getId() != id){
                otherExistingAppointments.add(a);
            }
        }
        // checks for scheduling errors
        for(Appointments a : otherExistingAppointments){
            Instant existingAppointmentStartInstant = a.getStart().toInstant();
            Instant existingAppointmentEndInstant = a.getEnd().toInstant();
            if(suggestedAppointmentEndTime.isAfter(existingAppointmentStartInstant) && suggestedAppointmentEndTime.isBefore(existingAppointmentEndInstant)){
                JOptionPane.showMessageDialog(null,
                        "We cannot schedule this appointment. " +
                                "The appointment timeslot you selected would not finish before an existing appointment.");
                overlappingAppointmentFound = true;
            } else if(suggestedAppointmentStartTime.isAfter(existingAppointmentStartInstant) && suggestedAppointmentStartTime.isBefore(existingAppointmentEndInstant)){
                JOptionPane.showMessageDialog(null,
                        "We cannot schedule this appointment. " +
                                "The appointment timeslot you selected would start during an existing appointment");
                overlappingAppointmentFound = true;
            } else if(suggestedAppointmentStartTime.equals(existingAppointmentStartInstant) || suggestedAppointmentEndTime.equals(existingAppointmentEndInstant)){
                JOptionPane.showMessageDialog(null,
                        "We cannot schedule this appointment. " +
                                "The appointment timeslot you selected would start or end during an existing appointment ");
                overlappingAppointmentFound = true;
            }
        }
        if(overlappingAppointmentFound == true){
            System.out.println("Appointment overlaps with an existing Appointment ");
        } else {
            // finally updates appointment
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
