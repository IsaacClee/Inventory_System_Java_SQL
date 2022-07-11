package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Interface class with lambda expressions used by MainForm and UpdateAppointmentForm
 */
public interface HoursInterface {
    /**
     * Lambda Expression
     * Used to populate combo boxes used by MainForm and UpdateAppointmentForm
     * LAMBDA Justification: Each form listed above requires single-instant non-dynamic population required to setup GUI interface
     * Used to isolate code function of a observable list which increases readability and supports DRY principles
     * Used to eliminate a static input list, best use case for an anonymous variable
     */

    void hoursListPopulateInterface();

}
