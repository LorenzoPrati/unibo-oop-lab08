package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    void print();
    public void setString(String string);
    public String getString();
    public List<String> getHistory();

}
