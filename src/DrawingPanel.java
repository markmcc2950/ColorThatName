import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private boolean drawColor = false;
    private Color nameColor = new Color(255, 255, 255);

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
        int width = GUI_Handler.getWindowWidth();
        int height = GUI_Handler.getWindowHeight();

        float red = 1.0f;
        float green = 0.0f;
        float blue = 0.0f;

        float max = 26f;
        int j = 0;

        for (float i = 0f; i < 1f; i += (1f / max)) {
            j++;
            if (i < 0.25f) {
                if (red - (4f * (1 / max)) >= 0f) {
                    red = red - (4f * (1 / max));
                } else {
                    red = 0f;
                }
            }
            else if (i >= 0.25f && i <= 0.75f) {
                red = 0.0f;
            }
            else if (i > 0.75f) {
                if (red + (4f * (1 / max)) <= 1f) {
                    red = red + (4f * (1 / max));
                }
                else {
                    red = 1f;
                }

                if (blue - (4f * (1 / max)) >= 0f) {
                    blue = blue - (4f * (1 / max));
                }
                else {
                    blue = 0f;
                }
            }
            if (i >= 0.125f && i <= 0.375f) {
                if (green + (4f * (1 / max)) <= 1f) {
                    green = green + (4f * (1 / max));
                }
                else {
                    green = 1f;
                }
            }
            else if (i > 0.375f && i <= 0.625f) {
                if (green - (4f * (1 / max)) >= 0f) {
                    green = green - (4f * (1 / max));
                }
                else {
                    green = 0f;
                }
            }
            if (i > 0.5f && i <= 0.75f) {
                if (blue + (4f * (1 / max)) <= 1f) {
                    blue = blue + (4f * (1 / max));
                }
                else {
                    blue = 1f;
                }
            }

            Color rgb = new Color(red, green, blue);

            GUI_Handler.setColorMap(j, rgb);

            g2.setColor(rgb);
            g2.fillRect(
                    (int)((0.7 * (j * ((width) / max)))),
                    (int)((float)(height / 10) + height / max),
                    (int)(width / max),
                    (height / 10)
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