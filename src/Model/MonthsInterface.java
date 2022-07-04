package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MonthsInterface {

    public static ObservableList<String> monthsInterface(){
        ObservableList<String> monthsList =
                FXCollections.observableArrayList(
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

        return monthsList;

    }
}
