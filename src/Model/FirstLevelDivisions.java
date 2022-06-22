package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Timestamp;

public class FirstLevelDivisions {
    private int id;
    private String name;

    public FirstLevelDivisions(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static class allFirstLevelDivisions {

        private static ObservableList<FirstLevelDivisions> listOfDivisions = FXCollections.observableArrayList();

        public static void addDivisionToList(FirstLevelDivisions divisions){listOfDivisions.add(divisions);}

        public static ObservableList<FirstLevelDivisions> getListOfDivisions() {return listOfDivisions;}

    }

}


