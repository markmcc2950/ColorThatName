import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI_Handler {
    // Private globals for GUI_Handler
    static Dimension screenSize;
    static int screenWidth;
    static int screenHeight;
    static HashMap<Integer, Color> colorMap = new HashMap<>();
    static boolean frameDrawn = false;

    private static void setWindowSize() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        System.out.println("Screen width = " + screenWidth + ", screen height = " + screenHeight);
    }

    public static int getWindowWidth() {
        return screenWidth;
    }

    public static int getWindowHeight() {
        return screenHeight;
    }

    public static void setColorMap(Integer x, Color color) {
        colorMap.put(x, color);
    }

    public static Color getColorMap(Integer x) {
        return colorMap.get(x);
    }

    public static void setFrameDrawn(boolean b) {
        frameDrawn = b;
    }

    private static void findColor(String str, DrawingPanel panel) {
        String lowered = str.toLowerCase();
        int red = 0;
        int green = 0;
        int blue = 0;
        int len = str.length();

        for (int i = 0; i < len; i++) {
            char c = lowered.charAt(i);
            if (c >= 'a' && c <= 'z') {
                Color charColor = getColorMap((int)(c) - 96);
                red = (red + charColor.getRed());
                green = (green + charColor.getGreen());
                blue = (blue + charColor.getBlue());
            }
        }

        red = (red / len);
        green = (green / len);
        blue = (blue / len);

        // Find the value closest to 255 for our RGB colors
        int max1 = Math.max(red, green);
        int max2 = Math.max(green, blue);
        int max3 = Math.max(max1, max2);

        int diff = (int)((255 - max3) / 2);

        Color color = new Color(red + diff, green + diff, blue + diff);

        panel.drawNameColor(color);

        System.out.println(red + " | " + blue + " | " +  green);
    }

    private static void frameSetup(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel(new BorderLayout());
        frame.setContentPane(content);

        // Drawing components
        DrawingPanel panel = new DrawingPanel();
        frame.add(panel);

        // Text field to enter words
        JTextField field = new JTextField(screenWidth / 100);
        field.setBounds(
                screenWidth / 3,
                screenHeight / 2,
                screenWidth / 3,
                screenHeight / 10
        );
        frame.add(field);

        // Add the button to save the word inputted
        JButton btn1 = new JButton();
        btn1.setText("COLORIFY");
        btn1.setEnabled(false);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findColor(field.getText(), panel);
            }
        });
        frame.add(btn1);

        JPanel controls = new JPanel();
        controls.add(new JLabel("Word to Color:"));
        controls.add(field);
        controls.add(btn1);

        content.add(controls, BorderLayout.NORTH);
        content.add(panel, BorderLayout.CENTER);

        frame.setBackground(Color.DARK_GRAY);

        frame.pack();

        frame.setSize((int)(screenWidth * 0.8), (int)(screenHeight * 0.8));

        frame.setVisible(true);

        // Waits for the frame to finish drawing on this thread
        SwingUtilities.invokeLater(() -> {
            btn1.setEnabled(true);
        });
    }

    public static void GUICreator() {
        // Get current display screen size first to help set up JFrame
        setWindowSize();

        JFrame frame = new JFrame();

        frameSetup(frame);
    }
}
