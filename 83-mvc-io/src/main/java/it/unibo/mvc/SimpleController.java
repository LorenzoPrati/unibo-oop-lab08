package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currString;
    private List<String> history;

    public SimpleController() {
        this.currString = null;
        this.history = new LinkedList<>();
    }

    @Override
    public void setString(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException("Null strings are not allowed");
        }
        this.currString = string;
    }

    @Override
    public String getString() {
        return this.currString;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void print() throws IllegalStateException {
        if (this.currString == null) {
            throw new IllegalStateException("The string to be printed is unset");
        }
        System.out.println(this.currString);
        this.history.add(currString);
    }
}
