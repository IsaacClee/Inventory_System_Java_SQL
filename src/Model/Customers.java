package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class Customers {
    private int id;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;

    /**
     * Customer Constructor
     * @param id
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customers (int id, String name, String address, String postal, String phone, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setPostal(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the createBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the phone
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setPhone(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the lastUpdatedBt
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy the last to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID the divisionID to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Observable List of Customers
     * Used by methods to filter customers
     */
    public static class filteredCustomers {

        /**
         * Declares Observable LIst
         */
        private static ObservableList<Customers> filteredListOfCustomers = FXCollections.observableArrayList();

        /**
         * Adds a Customer to filteredListOfCustomers
         * @param customers
         */
        public static void addCustomerToFilterList(Customers customers){filteredListOfCustomers.add(customers);}

        /**
         * Getter for filteredListOfCustomers
         * @return filteredListOfCustomers
         */
        public static ObservableList<Customers> getListOfFilteredCustomers() {return filteredListOfCustomers;}

    }


}
