import java.util.function.Consumer;
import java.awt.*;

public abstract class Event {

    protected Color color;

    private int size = GameConstants.CELL_SIZE;

    public abstract Consumer<Game> trigger();

    public final void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x*GameConstants.CELL_SIZE, y*GameConstants.CELL_SIZE, GameConstants.CELL_SIZE, GameConstants.CELL_SIZE);
    }
}
