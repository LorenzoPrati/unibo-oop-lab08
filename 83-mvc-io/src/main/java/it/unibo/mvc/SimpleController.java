package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currString;
    private final List<String> history;

    /**
     * Creates a {@code SimpleController}.
     */
    public SimpleController() {
        this.currString = null;
        this.history = new LinkedList<>();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setString(final String string) {
        if (string == null) {
            throw new IllegalArgumentException("Null strings are not allowed");
        }
        this.currString = string;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString() {
        return this.currString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return this.history;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        if (this.currString == null) {
            throw new IllegalStateException("The string to be printed is unset");
        }
        System.out.println(this.currString); // NOPMD
        this.history.add(currString);
    }
}
