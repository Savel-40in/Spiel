import java.awt.*;

public class Player {
   private int x;
   private int y;


   public Player(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public void move(int x, int y) {
	   this.x = x;
	   this.y = y;
   }

   public void drawPlayer(Graphics g) {
		g.setColor(Color.GREEN);
    	g.fillOval(x*GameConstants.CELL_SIZE, y*GameConstants.CELL_SIZE, GameConstants.CELL_SIZE, GameConstants.CELL_SIZE);
	}
   
   public int x() {
	   return x;
   }
   
   public int y() {
	   return y;
   }
}