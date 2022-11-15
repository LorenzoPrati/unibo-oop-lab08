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

    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel pan = new JPanel(new BorderLayout());
        this.frame.getContentPane().add(pan);
        final JTextArea textArea = new JTextArea();
        pan.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        pan.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                final String text = textArea.getText();
                try {
                    controller.writeString(text);
                } catch (IOException e) {
                    e.printStackTrace();
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
            public void actionPerformed(ActionEvent arg0) {
                final JFileChooser chooser = new JFileChooser();
                try {
                    final int retVal = chooser.showSaveDialog(browse);
                    if (retVal == JFileChooser.APPROVE_OPTION) {
                        controller.setCurrentFile(chooser.getSelectedFile());
                        pathText.setText(controller.getPath());
                    } 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, e, "An error has occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}

