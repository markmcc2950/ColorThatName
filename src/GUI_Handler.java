import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.*;
import java.util.HashMap;

class ShapeDrawing extends JComponent {

    private int numOfRects = 1;

    private int borderSize = 10;

    private float red = 1.0f;
    private float blue = 0.0f;
    private float green = 0.0f;

    public ShapeDrawing(int num) {
        this.numOfRects = num;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int currWidth = getWidth();
        int currHeight = getHeight();

        //System.out.println("Painting..." + currWidth + " || " + currHeight);

        g2.setColor(getBackground());
        g2.fillRect(0, 0, currWidth, currHeight);

        int width = 26;
        int scale = numOfRects / 2;

        for (int x = 0; x < width; x++) {
            float t = (float)x / (width - 1);  // 0 → 1
            float r, gr, b;

            if (t <= 0.5f) {
                // Red → Green
                float u = t / 0.5f;  // 0 → 1
                r = 1f - u;
                gr = u;
                b = 0f;
            } else {
                // Green → Blue
                float u = (t - 0.5f) / 0.5f;  // 0 → 1
                r = 0f;
                gr = 1f - u;
                b = u;
            }

            Character myChar = (char)(x + 65);

            Color gridColor = new Color(r, gr, b);
            g2.setColor(gridColor);
            g2.fillRect(scale * x, scale, scale, scale);

            GUI_Handler.setHash(x, myChar);
            GUI_Handler.setColor(myChar, gridColor);
        }

        for (int i = 0; i < 26; i++) {
            Color temp = GUI_Handler.getColor(GUI_Handler.getHash(i));
            //System.out.println(i + " = " +  GUI_Handler.getHash(i));
            //System.out.println(i + ": red = " + temp.getRed() + ", blue = " + temp.getBlue() + ", green = " + temp.getGreen());
        }
    }
}

public class GUI_Handler {
    private static int windowWidth = 600;
    private static int windowHeight = 600;
    private static int numToDraw = 1;

    private static HashMap<Integer, Character> letterMap = new HashMap<>();
    private static HashMap<Character, Color> colorMap = new HashMap<>();

    public int getWidth() {
        return windowWidth;
    }

    public int getHeight() {
        return windowHeight;
    }

    private static void frameSetup(JFrame frame) {
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numToDraw = windowHeight / 20;

        frame.getContentPane().add(new ShapeDrawing(numToDraw));

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {

                windowHeight = componentEvent.getComponent().getHeight();
                windowWidth = componentEvent.getComponent().getWidth();
                //System.out.println("RESIZE DETECTED! Height = " + windowHeight + ". Width = " + windowWidth);
                numToDraw = windowHeight / 20;
                frame.getContentPane().add(new ShapeDrawing(numToDraw));

                frame.repaint();
            }
        });

        frame.setVisible(true);
    }

    public static Character getHash(Integer key) {
        return letterMap.get(key);
    }

    public static Color getColor(Character key) {
        return colorMap.get(key);
    }

    public static void setHash(Integer key, Character letter) {
        letterMap.put(key, letter);
    }

    public static void setColor(Character key, Color color) {
        colorMap.put(key, color);
    }

    public static void getNameColor(String name) {
        int red = 0;
        int green = 0;
        int blue = 0;

        Color color;

        for (int i = 0; i < name.length(); i++) {
            char capChar = Character.toUpperCase(name.charAt(i));
            color = getColor(Character.toUpperCase(name.charAt(i)));
            red = (red + color.getRed()) / (i + 1);
            blue = (blue + color.getBlue()) / (i + 1);
            green = (green + color.getGreen()) / (i + 1);
        }

        System.out.println(name + "Colors:\nRed = " + red + ", Blue = " + blue + ", Green = " + green);
    }

    public static void GUICreator() {
        JFrame frame = new JFrame();

        frameSetup(frame);

        getNameColor("TEST");
    }
}
