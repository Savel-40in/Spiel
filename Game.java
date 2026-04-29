public class Game {
    private Map m;
    private Player p;
    
    public Game() {
        m = new Map(21);
        p = new Player(1, 1, m);
    }

    public Map getMap() {
        return m;
    }

    public Player getPlayer() {
        return p;
    }
}
