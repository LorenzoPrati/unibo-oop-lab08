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
    private static final String PATH = System.getProperty("user.home") + SEPARATOR + "output.txt";
    private File currFile;

    public Controller() {
        this.currFile = new File(PATH);
    }

    public void setCurrentFile(final File file) {
        this.currFile = file;
    }

    public File getFile() {
        return this.currFile;
    }

    public String getPath() {
        return this.currFile.getPath();
    }

    public void writeString(final String string) throws IOException{
        try (final PrintStream ps = new PrintStream(currFile, StandardCharsets.UTF_8)) {
            ps.print(string);
        } catch (final IOException e) {
            throw new IOException("Error while writing string", e);
        }
    }
}
