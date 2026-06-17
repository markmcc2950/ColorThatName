import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private boolean drawColor = false;
    private Color nameColor = new Color(255, 255, 255);
    private float max = 26f;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawGradient(g2);

        if (drawColor) {
            drawCenterRectangle(g2);
        }
    }
    private void drawGradient(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();

        float red = 1.0f;
        float green = 0.0f;
        float blue = 0.0f;

        int j = 0;

        for (float i = 0f; i < max; i += 1f) {
            j++;
            float hue = i / max;   // 0 → 1
            float saturation = 1f;                   // full color
            float brightness = 1f;

            Color rgb = Color.getHSBColor(hue, saturation, brightness);

            GUI_Handler.setColorMap(j, rgb);

            g2.setColor(rgb);
            g2.fillRect(
                    (int)((width * 0.01f) + i * (0.98f * width) / max),
                    (int)(height * 0.01f),
                    (int)(width / max),
                    (int)(height * 0.02f)
            );
        }

        GUI_Handler.setFrameDrawn(true);
    }

    private void drawCenterRectangle(Graphics2D g2) {
        int panelWidth  = getWidth();
        int panelHeight = getHeight();

        int rectWidth  = panelWidth / 5;   // or whatever size you want
        int rectHeight = panelHeight / 5;

        int x = (panelWidth - rectWidth) / 2;   // center horizontally
        int y = (int)(panelHeight * 0.5);      // place below gradient

        g2.setColor(nameColor);               // or whatever color you want
        g2.fillRect(x, y, rectWidth, rectHeight);
    }

    public void drawNameColor(Color color) {
        nameColor = color;
        drawColor = true;
        repaint();
    }
}