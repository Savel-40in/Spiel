import java.awt.*;

public class Star implements Animatable{
    private int startX;
    private int startY;
    private double angle = 0;
    private int size  = 20;
    private Color color = Color.YELLOW;
    private static int count = -1;
    // for animation
    private int x;
    private int y;
    private double progress = 0;
    private final int finalX;
    private final int finalY;
    private double stepa = Math.PI / 60; // Rotate 3 degrees per frame
    private boolean isAnimating = true;

    
    public Star(int x, int y) {
        this.startX = x;
        this.startY = y;
        count = (count + 1) % 3; // Increment count and wrap around after 3
        finalX = (800 + size * count * 2);
        finalY = (50 - 10 * (count % 2));
    }

    public void update() {
        x = (int)(startX + (finalX - startX) * progress);
        y = (int)(startY + (finalY - startY) * progress);
        progress += 0.02; // Adjust the speed of animation
        angle += stepa;
        isAnimating = (x < finalX);
    }

    public boolean isAnimating() {
        return isAnimating;
        
    }

    public void drawStar(Graphics g) {

        int[] X = {
            x,
            x + size / 4,
            x + size,
            x + size / 2,
            x + (3 * size) / 4,
            x,
            x - (3 * size) / 4,
            x - size / 2,
            x - size,
            x - size / 4
        };

        int[] Y = {
            y - size,
            y - size / 4,
            y - size / 4,
            y + size / 4,
            y + size,
            y + size / 2,
            y + size,
            y + size / 4,
            y - size / 4,
            y - size / 4
        };
        
        g.setColor(color);
        g.fillPolygon(X, Y, 10);
    }

}
