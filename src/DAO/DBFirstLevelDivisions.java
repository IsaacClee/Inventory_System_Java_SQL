package DAO;

import DAO.JDBC;
import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class DBFirstLevelDivisions {

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




    public static void checkDateConversion(){
        System.out.println("CREATE DATE TEST");
        String sql = "Select Create_Date from Countries";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}

