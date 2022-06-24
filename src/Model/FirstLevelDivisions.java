package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Timestamp;

public class FirstLevelDivisions {
    private int id;
    private String name;
    private int countryID;

    public FirstLevelDivisions(int id, String name, int countryID) {
        this.id = id;
        this.name = name;
        this.countryID = countryID;
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


    /**
     * @return the countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID the countryID to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public static class allFirstLevelDivisions {

        private static ObservableList<FirstLevelDivisions> listOfDivisions = FXCollections.observableArrayList();

        public static void addDivisionToList(FirstLevelDivisions divisions){listOfDivisions.add(divisions);}

        public static ObservableList<FirstLevelDivisions> getListOfDivisions() {return listOfDivisions;}

    }

    @Override
    public String toString(){
        return (name);
    }

}


