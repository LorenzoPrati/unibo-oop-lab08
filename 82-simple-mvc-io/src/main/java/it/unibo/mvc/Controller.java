package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String SEPARATOR = File.separator; 
    private static final String DEFAULT_PATH = System.getProperty("user.home") + SEPARATOR + "output.txt";
    private File currFile;

    /**
     * Creates a new {@code Controller}.
     */
    public Controller() {
        this.currFile = new File(DEFAULT_PATH);
    }

    /**
     * 
     * @param file is the File to be set as 
     *          destination
     */
    public void setFile(final File file) {
        this.currFile = file;
    }

    /**
     * 
     * @return the destination File
     */
    public File getFile() {
        return this.currFile;
    }

    /**
     * 
     * @return the destination File's path
     */
    public String getPath() {
        return this.currFile.getPath();
    }

    /**
     * 
     * @param string is the String to write
     * @throws IOException if occurs an error while writing
     */
    public void writeString(final String string) throws IOException {
        try (PrintStream ps = new PrintStream(currFile, StandardCharsets.UTF_8)) {
            ps.print(string);
        }
    }
}
