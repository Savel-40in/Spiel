import java.awt.*;
import java.util.*;
import java.util.List;

public class Map {
	
	private int size;
	private Cell[][] map;
	
	private Random random = new Random();
	
	public Map () {
		this.size = GameConstants.MAP_SIZE;
		map = new Cell[size][size];
		generateMap();
	}

	public Cell getCell(int x, int y) {
		return map[x][y];
	}
	
	public void drawMap(Graphics g) {
		for(int i = 0;  i < size; i++) {
			for(int j = 0;  j < size; j++) {
				map[i][j].drawCell(g);
			}
		}
	}
	
	private void generateMap() {
		initializeMap();

		generateMazeDFS(random.nextInt(size / 2) * 2 + 1, random.nextInt(size / 2) * 2 + 1); // Start at a random odd cell

		for (int i = 1; i < size; i++) {
		    for (int j = 1; j < size; j++) {
				carveRoomAt(i, j);
				map[i][j].setNeighborCount(countOpenNeighbors(i, j));
		    }
		}
	}

	private void carveRoomAt(int i, int j) {
		if (map[i][j].isWall() && countOpenNeighbors(i, j) == 4) {
					map[i][j].setWall(false);
					markRoomArea(i, j);
				}
	}

	private void markRoomArea(int i, int j) {
		for (int n = -1; n <= 1; n++) {
			for (int m = -1; m <= 1; m++) {
				map[i+n][j+m].setRoom(true);
			}
		}
	}

	private void initializeMap() {
		for (int i = 0; i < size; i++) {
		    for (int j = 0; j < size; j++) {
		        map[i][j] = new Cell(i, j); 
		    }
		}
	}

	private void generateMazeDFS(int i, int j) {
	    map[i][j].setWall(false);

	    List<int[]> directions = getNeighbors(i, j, 2);
	    Collections.shuffle(directions);

	    for (int[] d : directions) {
	        if (map[d[0]][d[1]].isWall()) {   
	            map[(i + d[0]) / 2][(j + d[1]) / 2].setWall(false);
	            generateMazeDFS(d[0], d[1]);
	        } else if (random.nextDouble() < GameConstants.LOOP_CHANCE) { // 5% chance to create a loop
	            map[(i + d[0]) / 2][(j + d[1]) / 2].setWall(false);
	        }
	    }
		
	}

	private boolean isInsideBounds(int x, int  y){
		return x >= 0 && x < size && y >= 0 && y < size;
	}

	private int countOpenNeighbors(int i, int  j){
		List<int[]> neighbors = getNeighbors(i, j, 1);

		int count = 0;

		for (int[] n : neighbors) {
	        if (!map[n[0]][n[1]].isWall()) {   
	            count++;
	        }
	    }

		return count;
		
	}

	private List<int[]> getNeighbors(int i, int j,  int  distance){
		int[][] neighbors = {
	        {i, j+distance},
	        {i+distance, j},
	        {i, j-distance},
	        {i-distance, j}
	    };

		List<int[]> validneighbors = new ArrayList<>();
		
		for (int[] n : neighbors) {
	        if (isInsideBounds(n[0], n[1])) {   
	            validneighbors.add(n);
	        }
	    }

		return validneighbors;
	}
	
	
	
	
//	GETTER + SETTER
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Cell[][] getMap() {
		return map;
	}

	public void setMap(Cell[][] map) {
		this.map = map;
	}

}