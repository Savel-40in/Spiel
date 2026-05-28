import java.util.function.Consumer;
import java.awt.*;

public class ChestEvent extends Event {
    public ChestEvent() {
        this.color = Color.YELLOW; // Set the color for the chest event
    }

    @Override
    public Consumer<Game> trigger() {
        return (game) -> {
            game.getStarBar().addStar(new Star(game.getPlayer().x() * GameConstants.CELL_SIZE, game.getPlayer().y() * GameConstants.CELL_SIZE));
            game.getAnimatables()[0] = game.getStarBar().getStar();
            game.setAnimating(true);
        };
    }
    
}
