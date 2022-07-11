package DAO;

import DAO.JDBC;
import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

/**
 * Used to get Countries records from Database
 */
public class DBCountries {

    /**
     * Gets all Countries records from Database
     * @return
     */
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries>countriesList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
               int id = rs.getInt("Country_ID");
               String name = rs.getString("Country");
               Countries c = new Countries(id, name);
               countriesList.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return countriesList;
    }

    /**
     * Get a Country record by CountryID
     * @param countryID
     * @return c (country)
     */
    public static Countries getCountryByID(int countryID){
        Countries c = new Countries(0, "");

        try {
            String sql = "SELECT * FROM countries WHERE Country_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,countryID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");
                c.setId(id);
                c.setName(name);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Used to get a Country Name by a CountryID
     * @param countryID
     * @return divName
     */
    public static String getCountryName(int countryID){
        ObservableList<Countries> countiesList = DBCountries.getAllCountries();
        String divName = null;
        for(Countries c : countiesList){
            if(c.getId() == countryID){
                divName = c.getName();
            }
        }
        return divName;
    }


}
