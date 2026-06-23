import java.awt.*;
import java.util.*;
import java.util.List;

public class Grid {
    private int width;
    private int height;
    private  Hex[][] hexes;
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.hexes = new Hex[width][height];
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                int q = col - (row - (row % 2)) / 2;
                int r = row;

                hexes[col][row] = new Hex(q, r);
            }
        }
    }
    public void drawGrid(Graphics g) {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                hexes[col][row].drawHex(g);
            }
        }
    }

    public Hex getHex(int col, int row) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            return hexes[col][row];
        }
        return null;
    }

    private boolean isInsideBounds(Hex hex) {
        int col = hex.getQ() + (hex.getR() - (hex.getR() % 2)) / 2;
        int row = hex.getR();
        return col >= 0 && col < width && row >= 0 && row < height;
		
	}

    public List<Hex> getNeighbors(Hex hex) {
        List<Hex> neighbors = new ArrayList<>();
        List<Hex> directions = Arrays.asList(
            new Hex(0, -1),  
            new Hex(1, 0),   
            new Hex(1, 1), 
            new Hex(0, 1),  
            new Hex(-1, 0),  
            new Hex(-1, -1) 
        );
        for (Hex direction : directions) {
            int q = hex.getQ() + direction.getQ();
            int r = hex.getR() + direction.getR();
            
            int col = q + (r - (r % 2)) / 2;
            int row = r;

            if (col >= 0 && col < width && row >= 0 && row < height) {
                neighbors.add(hexes[col][row]);
            }
        }
        return neighbors;
    }

    
}
