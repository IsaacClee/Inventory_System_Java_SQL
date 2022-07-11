package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Users CLass
 */
public class Users {
    private int id;
    private String name;
    private String password;

    /**
     * Public Users Constructor
     * @param id
     * @param name
     * @param password
     */
    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Overrides default functions of toString()
     * @return name
     */
    @Override
    public String toString(){
        return (name);
    }
}

