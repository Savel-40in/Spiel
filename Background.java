import java.awt.*;

public class Background implements Animatable {
    int r;
    int g;
    int b;
    int a;
    double progress = 1;
    double step = -0.01; // Adjust the speed of the fade-in effect
    public Background(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(r, this.g, b, a));
        g.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
    }

    public void update() {
        a = (int)(255 * progress);
        progress += step;
        if (progress >= 1 || progress <= 0) {
            step = -step;
        }
    }

    public boolean isAnimating() {
        return progress < 1 && progress > 0;
    }
}
