package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Used to get Appointment Records from Database
 */
public class DBAppointments {

    /**
     * Used to get all Appointment Records from Database
     * @return appointmentsList
     */
    public static ObservableList<Appointments> getAllAppointments(){
        ObservableList<Appointments>appointmentsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
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

    /**
     * Get Appointment records by current system month
     * @return appointmentsList
     */
    public static ObservableList<Appointments> getAppointmentsByCurrentMonth(){
        ObservableList<Appointments>appointmentsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(CURRENT_DATE()) AND YEAR(Start) = YEAR(CURRENT_DATE())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
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

    /**
     * Used to get appointment records by current system week
     * @return appointmentsList
     */
    public static ObservableList<Appointments> getAppointmentsByCurrentWeek(){
        ObservableList<Appointments>appointmentsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE YEARWEEK(Start, 1) = YEARWEEK(CURDATE(), 1) AND YEAR(Start) = YEAR(CURRENT_DATE())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
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

    /**
     * Used to get Appointment records with a start date within the next 7 days
     * @return
     */
    public static ObservableList<Appointments> getAppointmentsByNext7Days(){
        ObservableList<Appointments>appointmentsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE start BETWEEN NOW() AND (NOW() + INTERVAL 7 day)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
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


    /**
     * Used to add Appointment Record to the database
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdateBy
     * @param customerID
     * @param userID
     * @param contactID
     * @return rowsAffected
     * @throws SQLException
     */
    public static int insert(int id, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy, int customerID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6,start);
        ps.setTimestamp(7,end);
        ps.setTimestamp(8,createDate);
        ps.setString(9, createdBy);
        ps.setTimestamp(10, lastUpdate);
        ps.setString(11, lastUpdateBy);
        ps.setInt(12, customerID);
        ps.setInt(13, userID);
        ps.setInt(14, contactID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Used to update a appointment record
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param lastUpdate
     * @param lastUpdateBy
     * @param customerID
     * @param contactID
     * @param userID
     * @return rowsAffected
     * @throws SQLException
     */
    public static int update(int id, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp lastUpdate, String lastUpdateBy, int customerID, int contactID, int userID) throws SQLException {
        String sql = "UPDATE appointments Set Appointment_ID = ?, Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update =?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6, start);
        ps.setTimestamp(7, end);
        ps.setTimestamp(8, lastUpdate);
        ps.setString(9, lastUpdateBy);
        ps.setInt(10, customerID);
        ps.setInt(11, userID);
        ps.setInt(12, contactID);
        ps.setInt(13, id);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Used to delete a appointment record
     * @param id
     * @return rowsAffected
     * @throws SQLException
     */
    public  static int deleteAppointment(int id) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Used to check is appointment record exists
     * @param id
     * @return boolean
     */

    public static boolean doesAppointmentExist(int id) {
        for(Appointments appointments : DBAppointments.getAllAppointments()) {
            if(appointments.getId() == id){
                return true;
            }
        }
        return false;
    }

    /**
     * Used to check if Appointment Customer ID exists
     * @param id
     * @return boolean
     */
    public static boolean doesAppointmentCustomerIDExist(int id) {
        for(Appointments appointments : DBAppointments.getAllAppointments()) {
            if(appointments.getCustomerID() == id){
                return true;
            }
        }
        return false;
    }

    /**
     * Used to filter a list of Appointments by ID
     * @param contactId
     * @return Appointments.filteredAppointments.getListOfFilteredAppointments();
     */
    public static ObservableList<Appointments> filteredAppointmentsByID (int contactId){
        Appointments.filteredAppointments.getListOfFilteredAppointments().clear();
        for(Appointments appointments : DBAppointments.getAllAppointments()) {
            if (Integer.toString(appointments.getContactID()).equals(String.valueOf(contactId)))
                Appointments.filteredAppointments.addAppointmentToFilteredList(appointments);
        }
        return Appointments.filteredAppointments.getListOfFilteredAppointments();
    }

    /**
     * Used to filter a list of Appointments by Type
     * @param type
     * @return countOfType
     */
    public static int filteredAppointmentsByType (String type){
        int countOfType = 0;
        Appointments.filteredAppointments2.getListOfFilteredAppointments().clear();
        for(Appointments appointments : DBAppointments.getAllAppointments()) {
            if (appointments.getType().equals(type))
                countOfType++;
        }
        return countOfType;
    }

    /**
     * Used to get all DISTINCT types from Appointments
     * @return appointmentTypes
     * @throws SQLException
     */
    public static ObservableList<String> getAppointmentTypes() throws SQLException {
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        try{
            String sql = "SELECT DISTINCT Type FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String type = rs.getString("Type");
                appointmentTypes.add(type);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return appointmentTypes;
    }

    /**
     * Get Appointments by Month input
     * @param month
     * @return countOfMonth.size();
     * @throws SQLException
     */
    public static int getAppointmentsByMonth(int month) throws SQLException {
        ObservableList<String> countOfMonth = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointments WHERE MONTH(start) = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                countOfMonth.add(String.valueOf(id));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return countOfMonth.size();
    }


}
