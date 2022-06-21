package DAO;

import DAO.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {

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

}
