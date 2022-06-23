package DAO;

import DAO.JDBC;
import Model.Appointments;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class DBAppointments {


    public static ObservableList<Appointments> getAllAppointments(){
        ObservableList<Appointments>appointmentsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Customer_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Date start = rs.getDate("Start");
                Date end = rs.getDate("End");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
                appointmentsList.add(a);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
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
