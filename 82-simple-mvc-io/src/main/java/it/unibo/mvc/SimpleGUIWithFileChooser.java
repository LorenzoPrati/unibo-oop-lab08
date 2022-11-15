package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 5;

    /**
     * 
     * @param controller is the {@code Controller} to be
     *                   added to the GUI
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
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
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        final JPanel upPan = new JPanel(new BorderLayout());
        pan.add(upPan, BorderLayout.NORTH);
        final JTextField pathText = new JTextField();
        pathText.setEditable(false);
        pathText.setText(controller.getPath());
        upPan.add(pathText, BorderLayout.LINE_START);
        final JButton browse = new JButton("Browse...");
        upPan.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFileChooser chooser = new JFileChooser();
                final int retVal = chooser.showSaveDialog(frame);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(chooser.getSelectedFile());
                    pathText.setText(controller.getPath());
                    // CHECKSTYLE: EmptyBlockCheck OFF
                } else if (retVal == JFileChooser.CANCEL_OPTION) {
                    /*
                     * Nothing to do
                     */
                } else {
                    // CHECKSTYLE: EmptyBlockCheck ON
                    JOptionPane.showMessageDialog(frame, retVal, "Error", JOptionPane.ERROR_MESSAGE);
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
     * @param args unused
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}
