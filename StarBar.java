import java.awt.*;

public class StarBar {
    private Star[] stars;
    private int count = 0;

    public void addStar(Star star) {
        stars[count] = star;
        count++;
    }

    public StarBar() {
        stars = new Star[3];
    }

    public void drawStars(Graphics g) {
        for (int i = 0; i < 3; i++) {
            draw(g, 800 + 40 * i, (50 - 10 * (i % 2)), 20, Color.YELLOW);
            if (stars[i] != null) stars[i].drawStar(g);
        }
    }

    public Star getStar() {
        return stars[count - 1]; // Return the last added star
    }

    public void draw(Graphics g, int cx, int cy, int size, Color color) {

        int[] x = {
            cx,
            cx + size / 4,
            cx + size,
            cx + size / 2,
            cx + (3 * size) / 4,
            cx,
            cx - (3 * size) / 4,
            cx - size / 2,
            cx - size,
            cx - size / 4
        };

        int[] y = {
            cy - size,
            cy - size / 4,
            cy - size / 4,
            cy + size / 4,
            cy + size,
            cy + size / 2,
            cy + size,
            cy + size / 4,
            cy - size / 4,
            cy - size / 4
        };
        
        g.setColor(color);
        g.drawPolygon(x, y, 10);
    }
}
