package DAO;

import DAO.JDBC;
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
                FirstLevelDivisions d = new FirstLevelDivisions(id, name);
                divisionsList.add(d);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return divisionsList;
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
                FirstLevelDivisions d = new FirstLevelDivisions(id, name);
                divisionsList.add(d);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return divisionsList;

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

