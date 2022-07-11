package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Interface class with lambda expressions used by Reports
 */
public interface MonthsInterface {
    /**
     * Lambda Expression
     * Used to populate combo boxes used by Reports
     * LAMBDA Justification: Combo box population requires single-instant non-dynamic population required to setup GUI interface
     * Used to isolate code function of a observable list which increases readability and supports DRY principles
     * Used to eliminate a static input list, best use case for an anonymous variable
     */

    void monthsListPopulateInterface();

}
