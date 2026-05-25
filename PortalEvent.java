import java.util.function.Consumer;
import java.awt.*;

public class PortalEvent extends Event {
    public PortalEvent() {
        this.color = Color.MAGENTA; // Set the color for the portal event
    }

    @Override
    public Consumer<Game> trigger() {
        return (game) -> {
            game.newMap();
        };
    }
    
}
