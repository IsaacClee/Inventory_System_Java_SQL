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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainForm {
    @javafx.fxml.FXML
    private TableColumn partPriceCol;
    @javafx.fxml.FXML
    private TextField searchProduct;
    @javafx.fxml.FXML
    private Button addProduct;
    @javafx.fxml.FXML
    private TableView productTable;
    @javafx.fxml.FXML
    private Button modifyProduct;
    @javafx.fxml.FXML
    private Button exitMenu;
    @javafx.fxml.FXML
    private TableColumn partIDCol;
    @javafx.fxml.FXML
    private TableColumn productNameCol;
    @javafx.fxml.FXML
    private TableColumn productInvCol;
    @javafx.fxml.FXML
    private TableView partTable;
    @javafx.fxml.FXML
    private Button AddPart;
    @javafx.fxml.FXML
    private Button deletePart;
    @javafx.fxml.FXML
    private TableColumn productIDCol;
    @javafx.fxml.FXML
    private TableColumn partNameCol;
    @javafx.fxml.FXML
    private TableColumn partInvCol;
    @javafx.fxml.FXML
    private Button deleteProduct;
    @javafx.fxml.FXML
    private TableColumn productPriceCol;
    @javafx.fxml.FXML
    private TextField searchPart;
    @javafx.fxml.FXML
    private Button modifyPart;

    @javafx.fxml.FXML
    public void onActionDeleteProduct(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionSearchPart(Event event) {
    }

    @javafx.fxml.FXML
    public void onActionModifyProduct(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionAddProduct(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionSearchProduct(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionAddPart(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionModifyPart(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onActionExitMenu(ActionEvent actionEvent) {
    }
}
