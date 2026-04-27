import java.awt.*;
import java.util.*;
import java.util.List;

public class Map {
	
	private int size;
	private Cell[][] map;
	
	public Map (int size) {
		this.size = size;
		map = new Cell[size][size];
		mapGeneration();
	}
	
	private void mapGeneration() {
		for (int i = 0; i < size; i++) {
		    for (int j = 0; j < size; j++) {
		        map[i][j] = new Cell(i, j); 
		    }
		}
		cellRouting(1, 1);
	}
	
//	private void cellRouting(int i, int j) {
//		map[i][j].setWall(false);
//		if ((j+2)<size && map[i][j+2].isWall()) {
//			map[i][j+1].setWall(false);
//			cellRouting(i, j+2);
//		}
//		if ((i+2)<size && map[i+2][j].isWall()) {
//			map[i+1][j].setWall(false);
//			cellRouting(i+2, j);
//		}
//		if ((j-2)>=0 && map[i][j-2].isWall()) {
//			map[i][j-1].setWall(false);
//			cellRouting(i, j-2);
//		}
//		if ((i-2)>=0 && map[i-2][j].isWall()) {
//			map[i-1][j].setWall(false);
//			cellRouting(i-2, j);
//		}
//	}
	
	private void cellRouting(int i, int j) {
	    map[i][j].setWall(false);

	    int[][] dirs = {
	        {0, 2},
	        {2, 0},
	        {0, -2},
	        {-2, 0}
	    };

	    List<int[]> directions = new ArrayList<>(Arrays.asList(dirs));
	    Collections.shuffle(directions);

	    for (int[] d : directions) {
	        int ni = i + d[0];
	        int nj = j + d[1];

	        if (ni >= 0 && ni < size && nj >= 0 && nj < size && map[ni][nj].isWall()) {   
	            map[i + d[0] / 2][j + d[1] / 2].setWall(false);
	            cellRouting(ni, nj);
	        }
	    }
	}
	
	public void drawMap(Graphics g) {
		for(int i = 0;  i < size; i++) {
			for(int j = 0;  j < size; j++) {
				map[i][j].drawCell(g);
			}
		}
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
