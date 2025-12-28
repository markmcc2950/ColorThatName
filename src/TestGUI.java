import javax.swing.*;
import java.awt.*;

public class TestGUI{

    public static void createAndShowGUI(){

        // Create the basic frame
        JFrame frame = new JFrame("Layout Demo");

        // Create a top panel
        JPanel top = new JPanel();
        top.setBackground(Color.blue);
        frame.getContentPane().add(top, BorderLayout.PAGE_START);

        // Create a middle-left panel
        JPanel left = new JPanel();
        left.setBackground(Color.yellow);
        frame.getContentPane().add(left, BorderLayout.LINE_START);

        // Create a center panel
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.cyan);
        frame.getContentPane().add(center, BorderLayout.CENTER);

        // Create a middle-right panel
        JPanel right = new JPanel();
        right.setBackground(Color.green);
        frame.getContentPane().add(right, BorderLayout.LINE_END);

        // Create a bottom panel
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.red);
        frame.getContentPane().add(bottom, BorderLayout.PAGE_END);

        // Show the Frame
        frame.pack();
        frame.setSize(new Dimension(300, 300));
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}