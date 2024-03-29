package Controller;

import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBFirstLevelDivisions;
import Model.Countries;
import Model.Customers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class UpdateCustomerForm implements Initializable {
    @javafx.fxml.FXML
    private TextField cusNameField;
    @javafx.fxml.FXML
    private TextField cusPhoneField;
    @javafx.fxml.FXML
    private ComboBox cusCountryBox;
    @javafx.fxml.FXML
    private TextField cusPostalField;
    @javafx.fxml.FXML
    private TextField cusIDField;
    @javafx.fxml.FXML
    private TextField cusAddressField;
    @javafx.fxml.FXML
    private ComboBox cusDivisionBox;

    private Customers customerToBeUpdated = null;

    Stage stage;
    Parent scene;

    /**
     * initialize
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Acquire Customer selection form Main Form
        customerToBeUpdated = MainForm.getCustomerToBeUpdated();
        // Populate Customer to fields
        cusIDField.setText(String.valueOf(customerToBeUpdated.getId()));
        cusNameField.setText(String.valueOf(customerToBeUpdated.getName()));
        cusAddressField.setText(String.valueOf(customerToBeUpdated.getAddress()));
        cusPostalField.setText(String.valueOf(customerToBeUpdated.getPostal()));
        cusPhoneField.setText(String.valueOf(customerToBeUpdated.getPhone()));
        // Pull divisions for Add Customer Combo box from database
        cusDivisionBox.setItems(DBFirstLevelDivisions.getAllDivisions());
        // Pull countries for Add Customer Combo box from database
        cusCountryBox.setItems(DBCountries.getAllCountries());
        // Event listener - listens for change to Country Combo box and filters divisions accordingly

        // Pull Division and Country Names using Russian Doll Getters
        int countryID = DBFirstLevelDivisions.getCountryID(customerToBeUpdated.getDivisionID());
        cusDivisionBox.setItems(DBFirstLevelDivisions.getDivisionsById(countryID));
        cusCountryBox.getSelectionModel().select(DBCountries.getCountryByID(countryID));
        cusDivisionBox.getSelectionModel().select(DBFirstLevelDivisions.getDivisionByID(customerToBeUpdated.getDivisionID()));

        cusCountryBox.valueProperty().addListener(new ChangeListener<Countries>() {
            @Override
            public void changed(ObservableValue observableValue, Countries t, Countries t1) {
                cusDivisionBox.getSelectionModel().selectFirst();
                cusDivisionBox.setItems(DBFirstLevelDivisions.getDivisionsById(t1.getId()));

            }
        });

    }

    /**
     * Action Event - Cancel Update and return to main form
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
     * Action Event - Update Customer Record
     * Validates and submits update to Database
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    @javafx.fxml.FXML
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        int id = Integer.parseInt(cusIDField.getText());
        String name = cusNameField.getText();
        String address = cusAddressField.getText();
        String phone = cusPhoneField.getText();
        String postal = cusPostalField.getText();
        Timestamp lastUpdate =  new Timestamp(System.currentTimeMillis());
        String lastUpdatedBy = "user script";
        int divisionID = DBFirstLevelDivisions.getDivisionID(String.valueOf((cusDivisionBox.getValue())));
        int rowsAffected = DBCustomers.update(id,name,address,phone,postal,lastUpdate,lastUpdatedBy,divisionID);


        if(rowsAffected > 0){
            System.out.println("Success:"+ "ID: " + id +" was updated");
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainForm.fxml"));
            stage.setTitle("Customer Management System");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            System.out.println("Update Failed");
        }
    }
}
