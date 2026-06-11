import java.awt.*;

public class FoW {
    private boolean[][] fogOfWar = new boolean[GameConstants.MAP_SIZE][GameConstants.MAP_SIZE];
    
    public FoW() {
        for (int i = 0; i < GameConstants.MAP_SIZE; i++) {
            for (int j = 0; j < GameConstants.MAP_SIZE; j++) {
                fogOfWar[i][j] = true; // Start with all cells covered
            }
        }
    }

    public boolean isFogged(int x, int y) {
        return fogOfWar[x][y];
    }
    
    public void reveal(int x, int y) {
        fogOfWar[x][y] = false; // Reveal the cell
    }
    
    public void drawFogOfWar(Graphics g) {
        for (int i = 0; i < GameConstants.MAP_SIZE; i++) {
            for (int j = 0; j < GameConstants.MAP_SIZE; j++) {
                if (fogOfWar[i][j]) {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(i * GameConstants.CELL_SIZE, j * GameConstants.CELL_SIZE, GameConstants.CELL_SIZE, GameConstants.CELL_SIZE);
                }
            }
        }
    }
}
