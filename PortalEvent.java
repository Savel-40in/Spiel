import java.util.function.Consumer;
import java.awt.*;

public class PortalEvent extends Event {
    public PortalEvent() {
        this.color = Color.CYAN; // Set the color for the portal event
    }

    @Override
    public Consumer<Game> trigger() {
        return (game) -> {
            game.setMap(new Map()); // Create a new map
            game.setFoW(new FoW()); // Create a new fog of war
        };
    }
    
}
