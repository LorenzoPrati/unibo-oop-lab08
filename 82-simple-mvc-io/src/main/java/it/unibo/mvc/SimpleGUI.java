package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 5;

    /**
     * Sets up the GUI.
     * 
     * @param controller is the {@code Controller} to be 
     *         added to the GUI
     */
    public SimpleGUI(final Controller controller) {
        final JPanel pan = new JPanel(new BorderLayout());
        this.frame.getContentPane().add(pan);
        final JTextArea textArea = new JTextArea();
        pan.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        pan.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    controller.writeString(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Displays the GUI with chosen size.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Starts the application.
     * 
     * @param args not used
     */ 
    public static void main(final String[] args) {
        new SimpleGUI(new Controller()).display();
    }
}
