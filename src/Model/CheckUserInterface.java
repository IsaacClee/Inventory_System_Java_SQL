package Model;

/**
 * Interface class with lambda expressions used by Login Form
 * Used to verify user id and password
 */

public interface CheckUserInterface {
    /**
     * Lambda Expression
     * Used to reference database and check a user id and password match
     * LAMBDA Justification:
     * 1. Login form requires a single-instant non-dynamic matching of stored user id and password
     * 2. Used only once and does not require another function call
     * 3. Must always be called to validate user credentials
     * 4. Anonymous function (lambda expression) supports security through obscurity best practice
     */

    boolean checkUserLogin();
}
