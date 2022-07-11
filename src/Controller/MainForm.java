package Controller;


import DAO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.*;

import javax.swing.*;
import java.sql.*;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainForm implements Initializable{

    Stage stage;
    Parent scene;


    @javafx.fxml.FXML
    private Button exitMenu;
    @FXML
    private TableColumn appEndCol;
    @FXML
    private TableColumn appLocationCol;
    @FXML
    private TableColumn appCusIDCol;
    @FXML
    private TableView CustomerTable;
    @FXML
    private Button AddCustomer;
    @FXML
    private TableColumn cusCreateDateCol;
    @FXML
    private TableColumn appLastUpdatedByCol;
    @FXML
    private TableColumn cusLastUpdateCol;
    @FXML
    private TableColumn cusLastUpdatedByCol;
    @FXML
    private TableColumn appTitleCol;
    @FXML
    private TableColumn cusAddressCol;
    @FXML
    private TableColumn cusPostalCol;
    @FXML
    private TableColumn appDescripCol;
    @FXML
    private Button deleteAppointment;
    @FXML
    private TableColumn cusNameCol;
    @FXML
    private TableColumn cusPhoneCol;
    @FXML
    private TableView AppointmentTable;
    @FXML
    private TableColumn appTypeCol;
    @FXML
    private Button addAppointment;
    @FXML
    private TableColumn appLastUpdateCol;
    @FXML
    private Button deleteCustomer;
    @FXML
    private TableColumn appIDCol;
    @FXML
    private TableColumn cusIDCol;
    @FXML
    private TableColumn appCreateDateCol;
    @FXML
    private TableColumn appContactIDCol;
    @FXML
    private TableColumn appStartCol;
    @FXML
    private TableColumn cusCreatedByCol;
    @FXML
    private Button updateAppointment;
    @FXML
    private TableColumn cusDivisionCol;
    @FXML
    private TableColumn appUserIDCol;
    @FXML
    private Button updateCustomer;
    @FXML
    private TableColumn appCreatedByCol;
    @FXML
    private TextField newCusAddress;
    @FXML
    private ComboBox newCusDivision;
    @FXML
    private TextField newCusPostal;
    @FXML
    private TextField newCusPhone;
    @FXML
    private TextField newCusName;
    @FXML
    private ComboBox newCusCountry;

    public static Customers customerToBeUpdated = null;
    @FXML
    private TextField appUserIDField;
    @FXML
    private TextField appAppIDField;
    @FXML
    private TextField appTitleField;
    @FXML
    private DatePicker appEndField;
    @FXML
    private DatePicker appStartField;
    @FXML
    private TextField appTypeField;
    @FXML
    private TextField appLocationField;
    @FXML
    private TextField appDescriptionField;
    @FXML
    private TextField appCustomerIDField;
    @FXML
    private ComboBox appContactField;
    @FXML
    private ComboBox appEndTimeField;
    @FXML
    private ComboBox appStartTimeField;

    @FXML
    private ComboBox appEndTimeMinField;
    @FXML
    private ComboBox appStartTimeMinField;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @FXML
    private RadioButton currentMonth;
    @FXML
    private RadioButton allAppointments;
    @FXML
    private ToggleGroup appointmentViews;
    @FXML
    private RadioButton currentWeek;
    @FXML
    private RadioButton nextWeek;

    public static Customers getCustomerToBeUpdated() {
        return customerToBeUpdated;
    }

    public static Appointments appointmentToBeUpdated = null;

    public static Appointments getAppointmentToBeUpdated(){
        return appointmentToBeUpdated;
    }

    /**
     * initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populate Customer and Appointment Tables
        refreshTables();


        // Pull divisions for Add Customer Combo box
        newCusDivision.setItems(DBFirstLevelDivisions.getAllDivisions());
        newCusDivision.setPromptText("Please select state/province/division");

        // Pull countries for Add Customer Combo box
        newCusCountry.setItems(DBCountries.getAllCountries());
        newCusCountry.setPromptText("Please select Country");


        // Event listener - listens for change to Country Combo box and filters divisions accordingly
        newCusCountry.valueProperty().addListener(new ChangeListener<Countries>() {
            @Override
            public void changed(ObservableValue observableValue, Countries t, Countries t1) {
                newCusDivision.setItems(DBFirstLevelDivisions.getDivisionsById(t1.getId()));
            }
        });

        appContactField.setItems(DBContacts.getAllContacts());


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

        boolean incomingAppointment15Minutes = false;
        Instant now = Instant.now();
        Instant nowPlus15minutes = now.plusSeconds(900);
        Appointments nextAppointment = null;
        for(Appointments a : DBAppointments.getAllAppointments()){
            Instant appointmentInstantStart = a.getStart().toInstant();
            if(appointmentInstantStart.isAfter(now) && appointmentInstantStart.isBefore(nowPlus15minutes)){
                incomingAppointment15Minutes = true;
                nextAppointment = a;
            }
        }
        if(incomingAppointment15Minutes == true){
            JOptionPane.showMessageDialog(null, "Reminder: You have an incoming appointment. ID: " + nextAppointment.getId() + " Starting at: " + nextAppointment.getStart());
        } else {
            JOptionPane.showMessageDialog(null, "Good news! You do not have any appointments scheduled in the next 15 minutes.");
        }

    }


    /**
     * Action Event - used to exit application
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void onActionExitMenu(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Action Event - used to add customer to database
     * Validates and submits Adding a Customer Record
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void onActionAddCustomer(ActionEvent actionEvent) throws SQLException {

        int numOfCustomers = DBCustomers.getAllCustomers().size();
        while(DBCustomers.doesCustomerExist(numOfCustomers)) {
            numOfCustomers++;
        }
        int id = numOfCustomers;
        String name = newCusName.getText();
        String address = newCusAddress.getText();
        String postal = newCusPostal.getText();
        String phone = newCusPhone.getText();
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        String createdBy = "user script";
        Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
        String lastUpdatedBy = "user script";
        int divisionID = DBFirstLevelDivisions.getDivisionID(String.valueOf((newCusDivision.getValue())));

        int rowsAffected = DBCustomers.insert(id,name,address,postal,phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divisionID);

        if(rowsAffected > 0){
            System.out.println("Success: Added new customer");
        } else {
            System.out.println("Failed");
        }

        refreshTables();

    }

    /**
     * Action Event - used to add a Appointment to the database
     * Validates and submits new Appointment record based on user inputs
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void onActionAddAppointment(ActionEvent actionEvent) throws SQLException {

    int numOfAppointments = DBAppointments.getAllAppointments().size();
    while(DBAppointments.doesAppointmentExist(numOfAppointments)) {
        numOfAppointments++;
    }
    int id = numOfAppointments;
    String title = appTitleField.getText();
    String description = appDescriptionField.getText();
    String location = appLocationField.getText();
    String type = appTypeField.getText();
    Timestamp createDate = new Timestamp(System.currentTimeMillis());
    String createdBy = "user script";
    Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
    String lastUpdateBy = "user script";
    int userID = Integer.valueOf(appUserIDField.getText());
    int customerID = Integer.valueOf(appCustomerIDField.getText());
    int contactID = DBContacts.getContactByName(String.valueOf(appContactField.getValue())).getId();

    // Get Start Time // Start Time Conversions
    int startTime = Integer.parseInt((String) appStartTimeField.getSelectionModel().getSelectedItem());
    int startTimeMin = Integer.parseInt((String) appStartTimeMinField.getSelectionModel().getSelectedItem());
    LocalDateTime localDateTimeStart = appStartField.getValue().atTime(startTime, startTimeMin);
    ZonedDateTime zonedLocalDateTimeStart = localDateTimeStart.atZone(ZoneId.systemDefault());
    Timestamp start = Timestamp.from(zonedLocalDateTimeStart.toInstant());

    if(TimeZoneConversion.checkESTSchedule(localDateTimeStart) == false){
        JOptionPane.showMessageDialog(null,
                "Please select another Start Date & Time");
        return;
    };

    // Get End Time // End Time Conversions
    int endTime = Integer.parseInt((String) appEndTimeField.getSelectionModel().getSelectedItem());
    int endTimeMin = Integer.parseInt((String) appEndTimeMinField.getSelectionModel().getSelectedItem());
    LocalDateTime localDateTimeEnd = appEndField.getValue().atTime(endTime, endTimeMin);
    ZonedDateTime zonedLocalDateTimeEnd = localDateTimeEnd.atZone(ZoneId.systemDefault());
    Timestamp end = Timestamp.from(zonedLocalDateTimeEnd.toInstant());

    if(TimeZoneConversion.checkESTSchedule(localDateTimeEnd) == false){
        JOptionPane.showMessageDialog(null,
                "Please select another End Date & Time");
        return;
    };

        boolean overlappingAppointmentFound = false;
        Instant suggestedAppointmentStartTime = start.toInstant();
        Instant suggestedAppointmentEndTime = end.toInstant();
        for(Appointments a : DBAppointments.getAllAppointments()){
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

            int rowsAffected = DBAppointments.insert(id, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdateBy, customerID, userID, contactID);

            if (rowsAffected > 0) {
                System.out.println("Success: Added new appointment");
            } else {
                System.out.println("Failed");
            }
        }
    refreshTables();


    }

    /**
     * Action Event - Used to Delete a appointment from database
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void onActionDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove this appointment from the Database. Do you want to proceed?");
        Optional<ButtonType> result = deleteAlert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Appointments selectedItem = (Appointments) AppointmentTable.getSelectionModel().getSelectedItem();
            DBAppointments.deleteAppointment(selectedItem.getId());
            JOptionPane.showMessageDialog(null, "Delete successful - ID: " + selectedItem.getId() + ", Name: " + selectedItem.getTitle());
            refreshTables();
        }

    }

    /**
     * Action Event - used to Delete a customer from database
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    public void onActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        Customers selectedItem = (Customers) CustomerTable.getSelectionModel().getSelectedItem();
        if(DBAppointments.doesAppointmentCustomerIDExist(selectedItem.getId())) {
            JOptionPane.showMessageDialog(null, "System cannot delete Customer - ID: "
                    + selectedItem.getId() + ", Name: " + selectedItem.getName() + ". Delete all appointments related to this customer before proceeding");
        } else {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove this customer from the Database. Do you want to proceed?");
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedItem = (Customers) CustomerTable.getSelectionModel().getSelectedItem();
                DBCustomers.deleteCustomer(selectedItem.getId());
                JOptionPane.showMessageDialog(null, "Delete successful - ID: " + selectedItem.getId() + ", Name: " + selectedItem.getName());
                refreshTables();
            }
        }
    }

    /**
     * Action Event - used to launch Update Appointment Form
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionUpdateAppointment(ActionEvent actionEvent) throws IOException {
        Appointments selectedItem = (Appointments) AppointmentTable.getSelectionModel().getSelectedItem();
        appointmentToBeUpdated = selectedItem;
        if(selectedItem == null){
            JOptionPane.showMessageDialog(null, "Please select a Customer from the Customer Table");
        } else {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/UpdateAppointmentForm.fxml"));
            stage.setTitle("Update Appointment");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Action Event - used to launch Update Customer Form
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException  {
        Customers selectedItem = (Customers) CustomerTable.getSelectionModel().getSelectedItem();
        customerToBeUpdated = selectedItem;
        if(selectedItem == null){
            JOptionPane.showMessageDialog(null, "Please select a Customer from the Customer Table");
        } else {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/UpdateCustomerForm.fxml"));
            stage.setTitle("Update Customer");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Used to re-populate tables from database
     */
    public void refreshTables(){
        CustomerTable.getItems().clear();

        // Populate Customer Table
        CustomerTable.setItems(DBCustomers.getAllCustomers());

        cusIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cusNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cusAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        cusPostalCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        cusPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cusCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        cusCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        cusLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        cusLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        cusDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

        AppointmentTable.getItems().clear();

        // Populate Appointments Table
        AppointmentTable.setItems(DBAppointments.getAllAppointments());

        appIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescripCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        appCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        appLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        appLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appCusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appContactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }


    public void onActionCurrentWeek(ActionEvent actionEvent) throws IOException  {
        AppointmentTable.getItems().clear();
        AppointmentTable.setItems(DBAppointments.getAppointmentsByCurrentWeek());

        appIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescripCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        appCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        appLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        appLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appCusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appContactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }

    public void onActionNextWeek(ActionEvent actionEvent) throws IOException  {
        AppointmentTable.getItems().clear();
        AppointmentTable.setItems(DBAppointments.getAppointmentsByNext7Days());

        appIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescripCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        appCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        appLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        appLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appCusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appContactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }

    public void onActionAllAppointment(ActionEvent actionEvent) throws IOException  {
        refreshTables();
    }

    public void onActionCurrentMonth(ActionEvent actionEvent)  throws IOException {
        AppointmentTable.getItems().clear();
        AppointmentTable.setItems(DBAppointments.getAppointmentsByCurrentMonth());

        appIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescripCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        appCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        appLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        appLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        appCusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appContactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }

    /**
     * Used to open Reports GUI
     * @param actionEvent
     * @throws IOException
     */
    public void onActionReports(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
