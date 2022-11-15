package it.unibo.mvc;

import java.util.List;

/**
 * Simple controller responsible of I/O access.
 * It prints only on the standsard output.
 */
public interface Controller {
    /**
     * prints a String on stdout.
     */
    void print();
    /**
     * 
     * @param string is the next String to print
     */
    void setString(String string);
    /**
     * 
     * @return the current String
     */
    String getString();
    /**
     * 
     * @return a list representing the history
     */
    List<String> getHistory();
}
