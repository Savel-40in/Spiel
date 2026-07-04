import java.awt.*;
import java.util.*;
import java.util.List;

public class Battlefield {
    private int width;
    private int height;
    private  Hex[][] hexes;
    
    public Battlefield(int width, int height) {
        this.width = width;
        this.height = height;
        this.hexes = new Hex[width][height];
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                int q = offsetToAxial(col, row);
                int r = row;

                hexes[col][row] = new Hex(q, r);
            }
        }
    }

    private int offsetToAxial(int col, int row) {
        int q = col - (row - (row % 2)) / 2;
        // int r = row;
        return q;
    }

    private int axialToOffset(int q, int r) {
        int col = q + (r - (r % 2)) / 2;
        // int row = r;
        return col;
    }

    
    public List<Hex> findPath(int startQ, int startR, int endQ, int endR) {
        List<Hex> path = new ArrayList<>();
        
        Hex[][] parents = new Hex[width][height];
        boolean[][] visited = new boolean[width][height];
        List<Hex> queue = new ArrayList<>();
        
        queue.add(hexes[axialToOffset(startQ, startR)][startR]);
        visited[axialToOffset(startQ, startR)][startR] = true;
        parents[axialToOffset(startQ, startR)][startR] = hexes[axialToOffset(startQ, startR)][startR];

        int i = 0;
        while (i < queue.size()) {
            Hex current = queue.get(i);
            if (current.getQ() == endQ && current.getR() == endR) {
                break;
            }
            for (Hex neighbor : getNeighbors(current)) {
                int col = axialToOffset(neighbor.getQ(), neighbor.getR());
                int row = neighbor.getR();
                if (!visited[col][row] && !neighbor.isOccupied()) {
                    visited[col][row] = true;
                    parents[col][row] = current;
                    queue.add(neighbor);
                }
            }
            i++;
        }

        if (i == queue.size()) {
            return path; // No path found
        } 
        Hex current = queue.get(i);
        while (current != parents[axialToOffset(current.getQ(), current.getR())][current.getR()]) {
            int col = axialToOffset(current.getQ(), current.getR());
            path.add(current);
            current = parents[col][current.getR()];
        }
        Collections.reverse(path);
        
        return path;
    }

    public List<Hex> findPath(int startQ, int startR) {
         List<Hex> path = new ArrayList<>();
        
        Hex[][] parents = new Hex[width][height];
        boolean[][] visited = new boolean[width][height];
        List<Hex> queue = new ArrayList<>();
        
        queue.add(hexes[axialToOffset(startQ, startR)][startR]);
        visited[axialToOffset(startQ, startR)][startR] = true;
        parents[axialToOffset(startQ, startR)][startR] = hexes[axialToOffset(startQ, startR)][startR];

        int i = 0;
        while (i < queue.size()) {
            Hex current = queue.get(i);
            if (current.isOccupied()) { // Check if the hex is occupied by an ally
                if (current.getEntity().getSide() == 0) { // Assuming 0 is the side for allies
                    break;
                }
            }
            
            for (Hex neighbor : getNeighbors(current)) {
                int col = axialToOffset(neighbor.getQ(), neighbor.getR());
                int row = neighbor.getR();
                if (!visited[col][row] && (!neighbor.isOccupied() || neighbor.getEntity().getSide() != 1)) { // Check if the hex is occupied by an enemy
                    visited[col][row] = true;
                    parents[col][row] = current;
                    queue.add(neighbor);
                }
            }
            i++;
        }

        if (i == queue.size()) {
            return path; // No path found
        } 
        Hex current = queue.get(i);
        while (current != parents[axialToOffset(current.getQ(), current.getR())][current.getR()]) {
            int col = axialToOffset(current.getQ(), current.getR());
            path.add(current);
            current = parents[col][current.getR()];
        }
        Collections.reverse(path);
        
        return path;
    
    }

    public void placeEntity(BattleEntity entity) {
        int col = axialToOffset(entity.q(), entity.r());
        int row = entity.r();
        if (col >= 0 && col < width && row >= 0 && row < height) {
            hexes[col][row].setEntity(entity);
        }
    }

    public void draw(Graphics g) {
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
            new Hex(1, 0),
            new Hex(1, -1),
            new Hex(0, -1),
            new Hex(-1, 0),
            new Hex(-1, 1),
            new Hex(0, 1) 
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

