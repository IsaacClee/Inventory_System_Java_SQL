package DAO;

import DAO.JDBC;
import Model.Contacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;


/**
 * Used to get Contact Records from Database
 */
public class DBContacts {

    /**
     * Used to get all Contact records from Database
     * @return contactsList
     */
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

    /**
     * Get a Contact by contact name
     * @param contactName
     * @return c (contact)
     */
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

    /**
     * Get Contact ID by contact name
     * @param contactName
     * @return contactID
     */
    public static int getContactIDByName (String contactName){
        int contactID = 0;

        try {
            String sql = "SELECT * FROM contacts WHERE Contact_Name=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, contactName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                contactID = rs.getInt("Contact_ID");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return contactID;
    }

    /**
     * Get a Contact class instance by contact ID
     * @param contactID
     * @return c (contact)
     */
    public static Contacts getContactByID(int contactID){
        Contacts c = new Contacts(0, "");

        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,contactID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                c.setId(id);
                c.setName(name);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


}
