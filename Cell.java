import java.awt.*;

public class Cell {
	private int x;
	private int y;
	private boolean isWall;
	
	public Cell(int x,  int y){
		isWall = true;
		this.x = x;
		this.y = y;
	}
	
	public void drawCell(Graphics g) {
		if (isWall) {
			g.setColor(Color.BLACK);
		}else {
			g.setColor(Color.LIGHT_GRAY);
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

}
