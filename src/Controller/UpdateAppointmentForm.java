package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UpdateAppointmentForm {
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
    public void onActionCancel(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionUpdateAppointment(ActionEvent actionEvent) {
    }
}
