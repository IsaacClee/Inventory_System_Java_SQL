package Controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCustomers;
import DAO.DBFirstLevelDivisions;
import Model.Appointments;
import Model.Contacts;
import Model.FirstLevelDivisions;
import Model.MonthsInterface;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

public class Reports implements Initializable {

    Stage stage;
    Parent scene;

    @javafx.fxml.FXML
    private Label totalCustomers;
    @javafx.fxml.FXML
    private TableColumn scheduleEndCol;
    @javafx.fxml.FXML
    private TableColumn scheduleCustomerIDCol;
    @javafx.fxml.FXML
    private TableColumn scheduleDescriptionCol;
    @javafx.fxml.FXML
    private ComboBox scheduleContactBox;
    @javafx.fxml.FXML
    private Label divisionID;
    @javafx.fxml.FXML
    private TableColumn scheduleTitleCol;
    @javafx.fxml.FXML
    private Label totalAppointments;
    @javafx.fxml.FXML
    private Label totalByMonth;
    @javafx.fxml.FXML
    private Label totalByType;
    @javafx.fxml.FXML
    private TableColumn scheduleTypeCol;
    @javafx.fxml.FXML
    private ComboBox typeSelectBox;
    @javafx.fxml.FXML
    private TableColumn scheduleIDCol;
    @javafx.fxml.FXML
    private ComboBox monthSelectBox;
    @javafx.fxml.FXML
    private TableColumn scheduleStartCol;
    @javafx.fxml.FXML
    private ComboBox firstLevelDivisionSelectBox;
    @javafx.fxml.FXML
    private Button returnButton;
    @FXML
    private TableView ScheduleTable;

    int appointmentsCountByMonth = 0;
    int appointmentsCountByType = 0;
    int countOfAppointments = 0;

    private int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthSelectBox.setItems(MonthsInterface.monthsInterface());


        try {
            typeSelectBox.setItems(DBAppointments.getAppointmentTypes());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        firstLevelDivisionSelectBox.setItems(DBFirstLevelDivisions.getAllDivisions());

        scheduleContactBox.setItems(DBContacts.getAllContacts());

    }

    @javafx.fxml.FXML
    public void onActionSelectType(ActionEvent actionEvent) {
        String selectedType = (String) typeSelectBox.getSelectionModel().getSelectedItem();
        appointmentsCountByType = DBAppointments.filteredAppointmentsByType(selectedType);
        totalByType.setText(String.valueOf(appointmentsCountByType));
        countOfAppointments = appointmentsCountByMonth + appointmentsCountByType;
        totalAppointments.setText(String.valueOf(countOfAppointments));
    }


    @javafx.fxml.FXML
    public void onActionSelectContact(ActionEvent actionEvent) {
        Contacts selectedContact = (Contacts) scheduleContactBox.getSelectionModel().getSelectedItem();

        ScheduleTable.getItems().clear();

        // Populate Customer Table
        ScheduleTable.setItems(DBAppointments.filteredAppointmentsByID(selectedContact.getId()));

        scheduleIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        scheduleTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        scheduleDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        scheduleTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        scheduleStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        scheduleEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        scheduleCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

    }

    @javafx.fxml.FXML
    public void onActionSelectDivision(ActionEvent actionEvent) {

        FirstLevelDivisions selectedDivision = (FirstLevelDivisions) firstLevelDivisionSelectBox.getSelectionModel().getSelectedItem();

        divisionID.setText(String.valueOf(selectedDivision.getId()));

        totalCustomers.setText(String.valueOf(DBCustomers.searchForCustomersByID(selectedDivision.getId())));

    }

    @javafx.fxml.FXML
    public void onActionSelectMonth(ActionEvent actionEvent) {
        int selectedMonth = getMonthNumber((String) monthSelectBox.getSelectionModel().getSelectedItem());

    }

    @javafx.fxml.FXML
    public void onActionReturn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
