import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ToggleButton {

    private int x, y, width, height;
    private BufferedImage cross, tick;

    public ToggleButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            cross = ImageIO.read(Objects.requireNonNull(getClass().getResource("/cross.png")));
            tick = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tick.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            GameConstants.FoW = !GameConstants.FoW;
        }
    }

    public void draw(Graphics g) {
        if (GameConstants.FoW) {
            g.drawImage(tick, x, y, width, height, null);
        } else {
            g.drawImage(cross, x, y, width, height, null);
        }
    }
}
