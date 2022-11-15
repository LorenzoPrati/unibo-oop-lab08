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
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.setString(tf.getText());
                    controller.print();
                } catch (IllegalStateException e) {
                    System.out.println("Error while printing");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error while setting the string to print");
                }
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                final var list = controller.getHistory();
                ta.setText(list.toString());
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
        new SimpleGUI(new SimpleController()).display();
    }
}
