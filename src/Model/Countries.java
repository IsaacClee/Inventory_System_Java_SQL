package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Countries class
 */
public class Countries {
    private int id;
    private String name;


    /**
     *  Countries Constructor
     * @param id
     * @param name
     */
    public Countries(int id, String name) {
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

    /**
     * ObservableList for testing functions
     */
    public static class allCountries {


        private static ObservableList<Countries> listOfCountries = FXCollections.observableArrayList();

        /**
         * Adds a Country to listOfCountries
         * @param countries
         */
        public static void addCountryToList(Countries countries){listOfCountries.add(countries);}

        /**
         * Public Getter method for listOfCountries
         * @return listOfCountries
         */
        public static ObservableList<Countries> getListOfCountries() {return listOfCountries;}
    }

    /**
     * Overrides default toString function
     * @return name
     */
    @Override
    public String toString(){
        return (name);
    }

}

