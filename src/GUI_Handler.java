import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import static java.lang.Math.pow;

class ShapeDrawing extends JComponent {
    private final Color fillColor;
    private int rectX = 10;
    private int rectY = 10;
    private int numOfRects = 1;

    private float red = 1.0f;
    private float blue = 0.0f;
    private float green = 0.0f;

    public ShapeDrawing(Color fillColor, int rectX, int rectY, int num) {
        this.fillColor = fillColor;
        this.rectX = rectX;
        this.rectY = rectY;
        this.numOfRects = num;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int scale = 600 / numOfRects;
        double gridSize = Math.pow(numOfRects, 2);

        boolean maxGreen = false;

        for (int i = 0; i < numOfRects; i++) {
            for (int j = 0; j < numOfRects; j++) {

                float red   = (float)i / (numOfRects - 1);
                float green = (float)j / (numOfRects - 1);
                float blue  = 1f - red;

                Color gridColor = new Color(red, green, blue);
                g2.setColor(gridColor);
                g2.fillRect(scale * i, scale * j, scale, scale);
            }
        }

        //g2.setColor(fillColor);
        //g2.fillRect(rectX, rectY, 10, 10);
        //g2.setColor(fillColor);
        //g2.fillRect(101, 151, 59, 199);
    }
}

public class GUI_Handler {
    public static void GUICreator() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color myColor = new Color(10, 255, 150);

        frame.getContentPane().add(new ShapeDrawing(myColor, 10, 10, 10));
        frame.setVisible(true);
    }
}
