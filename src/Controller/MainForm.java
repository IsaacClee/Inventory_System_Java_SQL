package Controller;

import DAO.DBAppointments;
import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBFirstLevelDivisions;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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


        // Pull divisions for Add Customer Combo box
        newCusDivision.setItems(DBFirstLevelDivisions.getAllDivisions());
        newCusDivision.setPromptText("Please select state/province/division");

        // Pull countries for Add Customer Combo box
        newCusCountry.setItems(DBCountries.getAllCountries());
        newCusCountry.setPromptText("Please select Country");
    }

    @javafx.fxml.FXML
    public void onActionExitMenu(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void onActionAddCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionAddAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeleteAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {

        // Delete Customer from database
        // DBCustomers.deleteCustomer(1);
    }

    @FXML
    public void onActionUpdateAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionUpdateCustomer(ActionEvent actionEvent) {
    }
}
