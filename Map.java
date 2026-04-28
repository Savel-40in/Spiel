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

	    List<int[]> directions = neighboringCellSearch(i, j, 2);
	    Collections.shuffle(directions);

	    for (int[] d : directions) {
	        if (map[d[0]][d[1]].isWall()) {   
	            map[(i + d[0]) / 2][(j + d[1]) / 2].setWall(false);
	            cellRouting(d[0], d[1]);
	        }
	    }

		if (endRoad(i, j)){
			map[i][j].setRoad(true);
		}
	}

	private boolean inMatrix(int x, int  y){
		return x >= 0 && x < size && y >= 0 && y < size;
	}

	private boolean endRoad(int i, int  j){
		List<int[]> neighbor = neighboringCellSearch(i, j, 1);

		int count = 0;

		for (int[] n : neighbor) {
	        if (!map[n[0]][n[1]].isWall()) {   
	            count++;
	        }
	    }

		if (count >= 2){
			return false;
		} else {
		return true;
		}
	}

	private List<int[]> neighboringCellSearch(int i, int j,  int  step){
		int[][] neighbor = {
	        {i, j+step},
	        {i+step, j},
	        {i, j-step},
	        {i-step, j}
	    };

		List<int[]> validneighbor = new ArrayList<>();
		
		for (int[] n : neighbor) {
	        if (inMatrix(n[0], n[1])) {   
	            validneighbor.add(n);
	        }
	    }

		return validneighbor;
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
