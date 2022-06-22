package Model;

import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBFirstLevelDivisions;
import DAO.JDBC;
import Model.Countries;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {

        // Open Connection
        JDBC.openConnection();


        // Pull Countries to list from Database
         ObservableList<Countries> countriesList = DBCountries.getAllCountries();
         for(Countries c : countriesList){
         Countries.allCountries.addCountryToList(c);
         }


        // Pull Divisions to list from Database
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllDivisions();
        for(FirstLevelDivisions d : divisionsList){
            FirstLevelDivisions.allFirstLevelDivisions.addDivisionToList(d);
        }


        // Pull Customers to list from Database
        ObservableList<Customers> customersList = DBCustomers.getAllCustomers();
        for(Customers c : customersList){
            Customers.allCustomers.addCustomerToList(c);
        }


        // Launch Args
        launch(args);


        JDBC.closeConnection();

    }
}
