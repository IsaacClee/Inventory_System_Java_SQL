package Controller;

import DAO.DBAppointments;
import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBFirstLevelDivisions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javax.swing.text.Style;
import java.sql.*;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public static Customers getCustomerToBeUpdated() {
        return customerToBeUpdated;
    }

    public static Appointments appointmentToBeUpdated = null;

    public static Appointments getAppointmentToBeUpdated(){
        return appointmentToBeUpdated;
    }

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


    }


    @javafx.fxml.FXML
    public void onActionExitMenu(ActionEvent actionEvent) {
        System.exit(0);
    }

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
        Date createDate = new Date(System.currentTimeMillis());
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

    @FXML
    public void onActionAddAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeleteAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "This will remove this customer from the Database. Do you want to proceed?");
        Optional<ButtonType> result = deleteAlert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Customers selectedItem = (Customers) CustomerTable.getSelectionModel().getSelectedItem();
            DBCustomers.deleteCustomer(selectedItem.getId());
            JOptionPane.showMessageDialog(null, "Delete successful - ID: " + selectedItem.getId() + ", Name: " + selectedItem.getName());
            refreshTables();
        }
    }

    @FXML
    public void onActionUpdateAppointment(ActionEvent actionEvent) {
    }

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
}
