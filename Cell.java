import java.awt.*;

public class Cell {
	private int x;
	private int y;
	private boolean isWall;
	private int neighborCount;
	private Event event;
	private boolean isRoom;
	private boolean isVisited;
	private int size = GameConstants.CELL_SIZE;
	
	public Cell(int x,  int y){
		isWall = true;
		neighborCount = 2;
		event = new NoEvent();
		this.x = x;
		this.y = y;
		isRoom = false;
		isVisited = false;
	}
	
	public void drawCell(Graphics g) {
		if (isWall) {
			g.setColor(Color.BLACK);
		}else if (isRoom) {
			g.setColor(Color.GRAY);
		}else {
			g.setColor(Color.DARK_GRAY);
		}
		
		g.fillRect(x*size, y*size, size, size);
		event.draw(g, x, y);
		
		g.setColor(Color.BLACK);
		g.drawRect(x*size, y*size, size, size);
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public boolean isRoom() {
		return isRoom;
	}

	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
}

