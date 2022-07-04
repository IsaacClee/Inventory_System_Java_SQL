package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HoursInterface {
    public static ObservableList<String> hoursInterface(){
        ObservableList<String> hoursList =
                FXCollections.observableArrayList(
                        "0",
                        "1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        "13",
                        "14",
                        "15",
                        "16",
                        "17",
                        "18",
                        "19",
                        "20",
                        "21",
                        "22",
                        "23"
                );

        return hoursList;

    }

}
