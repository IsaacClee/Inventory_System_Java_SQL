package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.Timestamp;

import java.sql.*;

public class DBCustomers {

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
                Date createDate = rs.getDate("Create_Date");
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

    public static int insert(int id, String name, String address, String postal, String phone, Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int division) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setString(3,address);
        ps.setString(4,postal);
        ps.setString(5,phone);
        ps.setDate(6, createDate);
        ps.setString(7,createdBy);
        ps.setTimestamp(8, lastUpdate);
        ps.setString(9,lastUpdatedBy);
        ps.setInt(10, division);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

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


    public static int deleteCustomer(int id) throws SQLException {
            String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            int rowAffected = ps.executeUpdate();
            return rowAffected;
    }

    public static ObservableList<Customers> searchForCustomer(String name){
        for(Customers customers : DBCustomers.getAllCustomers()) {
            if (Integer.toString(customers.getId()).contains(name))
                Customers.filteredCustomers.addCustomerToFilterList(customers);
        }
        return Customers.filteredCustomers.getListOfFilteredCustomers();
    }

    public static boolean doesCustomerExist(int id) {
        for(Customers customers : DBCustomers.getAllCustomers()) {
            if(customers.getId() == id){
                return true;
            }
        }
        return false;
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
