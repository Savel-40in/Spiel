import java.util.function.Consumer;
import java.awt.*;

public abstract class Event {

    protected Color color;

    public abstract Consumer<Game> trigger();

    public final void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x*30, y*30, 30, 30);
    }
}
