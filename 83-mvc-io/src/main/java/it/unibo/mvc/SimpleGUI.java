package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

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
     * @param controller
     */
    public SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel(new BorderLayout());
        this.frame.getContentPane().add(panel);
        final JTextField tf = new JTextField();
        tf.setText("stringa da stampare con print");
        panel.add(tf, BorderLayout.NORTH);
        final JTextArea ta = new JTextArea();
        panel.add(ta, BorderLayout.CENTER);
        final JPanel botPan = new JPanel(new FlowLayout());
        panel.add(botPan, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");
        botPan.add(print);
        botPan.add(history);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                controller.setString(tf.getText());
                controller.print();
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final var list = controller.getHistory();
                ta.setText(list.toString());
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Displays the GUi with chosen size.
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
        new SimpleGUI(new SimpleController()).display();
    }
}
