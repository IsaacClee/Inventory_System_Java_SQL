package Model;

import DAO.DBCountries;
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

        // Pull Countries to list
        ObservableList<Countries> countryList = DBCountries.getAllCountries();
        for(Countries C : countryList){
            System.out.println("Country ID : " + C.getId() + " Name : " + C.getName());
        }

        // Pull Divisions to list
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllDivisions();
        for(FirstLevelDivisions d : divisionsList){
            System.out.println("Division ID : " + d.getId() + " Name : " + d.getName());
        }

        // Test Date Conversion
        DBCountries.checkDateConversion();

        // Launch Args
        launch(args);


        JDBC.closeConnection();

    }
}
