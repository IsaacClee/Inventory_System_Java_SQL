package Controller;

import DAO.DBCountries;
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
        //  cusCountryBox.setValue(DBCountries.getCountryName(countryID));
         // cusDivisionBox.setValue(DBFirstLevelDivisions.getDivisionName(customerToBeUpdated.getDivisionID()));
        cusDivisionBox.setItems(DBFirstLevelDivisions.getDivisionsById(countryID));
        cusCountryBox.valueProperty().addListener(new ChangeListener<Countries>() {
            @Override
            public void changed(ObservableValue observableValue, Countries t, Countries t1) {
                cusDivisionBox.setItems(DBFirstLevelDivisions.getDivisionsById(t1.getId()));
            }
        });

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
    public void onActionUpdateCustomer(ActionEvent actionEvent) {
    }
}
