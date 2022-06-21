package Controller;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Main Form");


    }

    @javafx.fxml.FXML
    public void onActionExitMenu(ActionEvent actionEvent) {
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
    public void onActionDeleteCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionUpdateAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionUpdateCustomer(ActionEvent actionEvent) {
    }
}
