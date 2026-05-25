import java.util.function.Consumer;
import java.awt.*;

public class NoEvent extends Event{
    public NoEvent() {
        this.color = new Color(0, 0, 0, 0); // Set the color for the no event
    }
    
    public Consumer<Game> trigger() {
        return (game) -> {};
    }
}
