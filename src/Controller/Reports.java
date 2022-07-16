package Controller;

import DAO.DBAppointments;
import DAO.DBContacts;
import DAO.DBCustomers;
import DAO.DBFirstLevelDivisions;
import Model.*;
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


    /**
     * initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //!!!!!!!!! LAMBDA EXPRESSION CASE 2 !!!!!!!!!

        /**
         * Lambda Expression 2
         * Used to populate combo boxes used by Form
         * LAMBDA Justification: This population requires single-instant non-dynamic population required to setup GUI interface
         * Used to isolate code function of a observable list which increases readability and supports DRY principles
         * Used to eliminate a static input list, best use case for an anonymous variable
         */
        MonthsInterface monthsUpdate = () -> {
            ObservableList<String> monthsList = FXCollections.observableArrayList(
                    "January",
                    "February",
                    "March",
                    "April",
                    "May",
                    "June",
                    "July",
                    "August",
                    "September",
                    "October",
                    "November",
                    "December"
            );
            monthSelectBox.setItems(monthsList);

        };
        // execute lambda expression
        monthsUpdate.monthsListPopulateInterface();


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
        String selectedType = (String) typeSelectBox.getValue();
        appointmentsCountByType = DBAppointments.filteredAppointmentsByType(selectedType);
        totalByType.setText(String.valueOf(appointmentsCountByType));
        countOfAppointments = appointmentsCountByMonth + appointmentsCountByType;
        totalAppointments.setText(String.valueOf(countOfAppointments));
    }


    /**
     * Action Event - upon Contact selection - updates schedule for Contact
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void onActionSelectContact(ActionEvent actionEvent) {
        Contacts selectedContact = (Contacts) scheduleContactBox.getValue();

        if(ScheduleTable != null){
            ScheduleTable.getItems().clear();
        }


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

    /**
     * Action Event - Displays Name and Count of Customers by Division Selection
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void onActionSelectDivision(ActionEvent actionEvent) {

        FirstLevelDivisions selectedDivision = (FirstLevelDivisions) firstLevelDivisionSelectBox.getValue();

        divisionID.setText(String.valueOf(selectedDivision.getId()));

        totalCustomers.setText(String.valueOf(DBCustomers.searchForCustomersByID(selectedDivision.getId())));

    }

    /**
     * Action Event - Selects Month
     * @param actionEvent
     * @throws SQLException
     */
    @javafx.fxml.FXML
    public void onActionSelectMonth(ActionEvent actionEvent) throws SQLException {
        int selectedMonth = getMonthNumber((String) monthSelectBox.getValue());
        appointmentsCountByMonth = DBAppointments.getAppointmentsByMonth(selectedMonth);
        totalByMonth.setText(String.valueOf(appointmentsCountByMonth));
        countOfAppointments = appointmentsCountByMonth + appointmentsCountByType;
        totalAppointments.setText(String.valueOf(countOfAppointments));
    }


    /**
     * Action Event - Return to Main Form
     * @param actionEvent
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void onActionReturn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
