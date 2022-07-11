package DAO;

import DAO.JDBC;
import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

/**
 * First-level Division Class and Database Queries
 */
public class DBFirstLevelDivisions {

    /**
     * Gets all FirstLevelDivisions records from Database
     * @return divisionsList
     */
    public static ObservableList<FirstLevelDivisions> getAllDivisions(){
        ObservableList<FirstLevelDivisions>divisionsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM first_level_divisions";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                FirstLevelDivisions d = new FirstLevelDivisions(id, name, countryID);
                divisionsList.add(d);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return divisionsList;
    }

    /**
     * Gets a single FirstLevelDivision by ID from Database
     * @param divisionID
     * @return d
     */
    public static FirstLevelDivisions getDivisionByID(int divisionID){
        FirstLevelDivisions d = new FirstLevelDivisions(0, null, 0);

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Division_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,divisionID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countyID = rs.getInt("Country_ID");
                d.setId(id);
                d.setName(name);
                d.setCountryID(countyID);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return d;
    }


    /**
     * Gets Divisions list by CountryID from Database
     * @param countryID
     * @return divisionsList
     */
    public static ObservableList<FirstLevelDivisions> getDivisionsById(int countryID){
        ObservableList<FirstLevelDivisions>divisionsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,countryID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int country = rs.getInt("Country_ID");
                FirstLevelDivisions d = new FirstLevelDivisions(id, name, country);
                divisionsList.add(d);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return divisionsList;
    }

    /**
     * Gets name of a Division by the Division ID
     * @param divisionID
     * @return divName
     */
    public static String getDivisionName(int divisionID){
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllDivisions();
        String divName = null;
        for(FirstLevelDivisions d : divisionsList){
            if(d.getId() == divisionID){
                divName = d.getName();
            }
        }
        return divName;
    }

    /**
     * Gets a Division ID by a Division Name Object
     * @param divisionName
     * @return divId
     */
    public static int getDivisionID(Object divisionName){
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllDivisions();
        int divId = 0;
        for(FirstLevelDivisions d : divisionsList){
            if(d.getName().contains((CharSequence) divisionName)){
                divId = d.getId();
            }
        }
        return divId;
    }

    /**
     * Gets a Division ID by a Division Name String
     * @param divisionName
     * @return divId
     */
    public static int getDivisionID(String divisionName){
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllDivisions();
        int divId = 0;
        CharSequence cs = divisionName;
        for(FirstLevelDivisions d : divisionsList){
            if(d.getName().contains(cs)){
                divId = d.getId();
            }
        }
        return divId;
    }


    /**
     * Gets a Country ID by a Division ID
     * @param divisionID
     * @return countryID
     */
    public static int getCountryID(int divisionID){
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllDivisions();
        int countryID = 0;
        for(FirstLevelDivisions d : divisionsList){
            if(d.getId() == divisionID){
                countryID = d.getCountryID();
            }
        }
        return countryID;
    }



}

