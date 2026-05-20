import java.awt.*;

public class Cell {
	private int x;
	private int y;
	private boolean isWall;
	private int neighborCount;
	private boolean isVisited;
	
	public Cell(int x,  int y){
		isWall = true;
		neighborCount = 2;
		this.x = x;
		this.y = y;
	}
	
	public void drawCell(Graphics g) {
		if (isWall) {
			g.setColor(Color.BLACK);
		}else if (neighborCount < 2){
			g.setColor(Color.RED);
		}else if (neighborCount > 2){
			g.setColor(Color.BLUE);
		}else {
			g.setColor(Color.DARK_GRAY);
		}
    	g.fillRect(x*30, y*30, 30, 30);
	}


	
	
//	GETTER + SETTER
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}
	
	public int getNeighborCount() {
		return neighborCount;
	}

	public void setNeighborCount(int neighborCount) {
		this.neighborCount = neighborCount;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

}