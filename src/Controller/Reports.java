package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private Label divisionName;
    @javafx.fxml.FXML
    private TableColumn scheduleTitleCol;
    @javafx.fxml.FXML
    private Label totalAppointments;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void onActionSelectType(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionSelectContact(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionSelectDivision(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionSelectMonth(ActionEvent actionEvent) {
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
