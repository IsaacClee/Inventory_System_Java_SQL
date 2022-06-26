package DAO;

import DAO.JDBC;
import Model.Contacts;
import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;


public class DBContacts {

    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts>contactsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                Contacts c = new Contacts(id, name);
                contactsList.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return contactsList;
    }

    public static Contacts getContactByName (String contactName){
        Contacts c = new Contacts(0, "");

        try {
            String sql = "SELECT * FROM contacts WHERE Contact_Name=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, contactName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                c.setId(id);
                c.setName(name);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return c;
    }



}
