package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * First-level Divisions class
 */
public class FirstLevelDivisions {
    private int id;
    private String name;
    private int countryID;

    /**
     * FirstLevelDivisions Public Constructor
     * @param id
     * @param name
     * @param countryID
     */
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

    /**
     * Observable list functions for filters and testing
     */
    public static class allFirstLevelDivisions {

        /**
         * Declares Observable list
         */
        private static ObservableList<FirstLevelDivisions> listOfDivisions = FXCollections.observableArrayList();

        /**
         * Adds FirstLevelDivision to listOfDivisions
         * @param divisions
         */
        public static void addDivisionToList(FirstLevelDivisions divisions){listOfDivisions.add(divisions);}

        /**
         * Getter for listOfDivisions
         * @return listOfDivisions
         */
        public static ObservableList<FirstLevelDivisions> getListOfDivisions() {return listOfDivisions;}

    }

    /**
     * Overrides default method toString()
     * @return
     */
    @Override
    public String toString(){
        return (name);
    }

}


