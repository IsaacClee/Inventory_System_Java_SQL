package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;

import java.sql.*;

/**
 * Class used for Customer records from Database
 */
public class DBCustomers {

    /**
     * Gets all Customer records from Datatbase
     * @return customersList
     */
    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers>customersList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");

                Customers c = new Customers(id, name, address, postal, phone,createDate,createdBy,lastUpdate,lastUpdatedBy,divisionID);
                customersList.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return customersList;
    }

    /**
     * Adds a Customer record to Database
     * @param id
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param division
     * @return
     * @throws SQLException
     */
    public static int insert(int id, String name, String address, String postal, String phone, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int division) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setString(3,address);
        ps.setString(4,postal);
        ps.setString(5,phone);
        ps.setTimestamp(6, createDate);
        ps.setString(7,createdBy);
        ps.setTimestamp(8, lastUpdate);
        ps.setString(9,lastUpdatedBy);
        ps.setInt(10, division);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Updates an existing Customer Record in Database
     * @param id
     * @param name
     * @param address
     * @param phone
     * @param postal
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static int update(int id, String name, String address, String phone, String postal, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) throws SQLException {
        String sql = "UPDATE customers SET Customer_ID = ?, Customer_Name = ?, Address = ?, Phone = ?, Postal_Code = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, address);
        ps.setString(4, phone);
        ps.setString(5, postal);
        ps.setTimestamp(6, lastUpdate);
        ps.setString(7, lastUpdatedBy);
        ps.setInt(8,divisionID);
        ps.setInt(9, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }


    /**
     * Deletes a existing Customer Record from Database
     * @param id
     * @return
     * @throws SQLException
     */
    public static int deleteCustomer(int id) throws SQLException {
            String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            int rowAffected = ps.executeUpdate();
            return rowAffected;
    }

    /**
     * Used to search for a list of Customers by name
     * @param name
     * @return Customers.filteredCustomers.getListOfFilteredCustomers()
     */
    public static ObservableList<Customers> searchForCustomer(String name){
        for(Customers customers : DBCustomers.getAllCustomers()) {
            if (Integer.toString(customers.getId()).contains(name))
                Customers.filteredCustomers.addCustomerToFilterList(customers);
        }
        return Customers.filteredCustomers.getListOfFilteredCustomers();
    }

    /**
     * Used to search for the count of a CustomerID by DivisionID
     * @param divisionID
     * @return countOfCustomers
     */
    public static int searchForCustomersByID (int divisionID){
        int countOfCustomers = 0;
        for(Customers customers : DBCustomers.getAllCustomers()) {
            if (Integer.toString(customers.getDivisionID()).equals(String.valueOf(divisionID)))
                countOfCustomers++;
        }
        return countOfCustomers;
    }


    /**
     * Used to check if a Customer Record exists
     * @param id
     * @return boolean
     */
    public static boolean doesCustomerExist(int id) {
        for(Customers customers : DBCustomers.getAllCustomers()) {
            if(customers.getId() == id){
                return true;
            }
        }
        return false;
    }


}
