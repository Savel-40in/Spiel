import java.awt.*;

public class Star implements Animatable{
    private int cx;
    private int cy;
    private double angle = 0;
    private int size  = 20;
    private Color color = Color.YELLOW;
    private static int count = -1;
    // for animation
    private int stepx;
    private int stepy;
    private double stepa = Math.PI / 60; // Rotate 3 degrees per frame
    private boolean isAnimating = true;

    
    public Star(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        count = (count + 1) % 3; // Increment count and wrap around after 3
        stepx = ((800 + size * count * 2) - this.cx) / 120;
        stepy = ((50 - 10 * (count % 2))- this.cy) / 120;
    }

    public void update() {
        cx += stepx;
        cy += stepy;
        angle += stepa;
        isAnimating = (cx < (800 + size * count * 2));
        if (!isAnimating) { 
            setX();
            setY();
        }
    }

    public boolean isAnimating() {
        return isAnimating;
        
    }

    public void drawStar(Graphics g) {

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
        g.fillPolygon(x, y, 10);
    }

    public void setX() {
        this.cx = (800 + size * count * 2);
    }

    public void setY() {
        this.cy = (50 - 10 * (count % 2));
    }

}
