import java.util.function.Consumer;
import java.awt.*;

public abstract class Event {

    protected Color color;

    private int size = GameConstants.CELL_SIZE;

    public abstract Consumer<Game> trigger();

    public final void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x*size, y*size, size, size);
    }
}
