import java.util.function.Consumer;

public class Event {
    public Consumer<Game> trigger() {
        // Implement event logic here
        Consumer<Game> eventAction = (game) -> {
            game.newMap();
        };
        return eventAction;
    }
}
