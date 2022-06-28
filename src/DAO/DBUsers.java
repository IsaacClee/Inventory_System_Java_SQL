package DAO;

import DAO.JDBC;
import Model.Contacts;
import Model.Countries;
import Model.FirstLevelDivisions;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class DBUsers {
    public static ObservableList<Users> getAllUser(){
        ObservableList<Users>contactsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users u = new Users(id, name, password);
                contactsList.add(u);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return contactsList;
    }
}
