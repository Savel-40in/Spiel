import java.awt.*;
import java.util.*;
import java.util.List;

public class Player implements Animatable{
   private int x;
   private int y;
   private List<int[]> moveQueue;
   private boolean isAnimating = false;


   public Player(int x, int y) {
      this.x = x;
      this.y = y;
      this.moveQueue = new ArrayList<>();
   }

   public void update() {
      if (moveQueue.isEmpty()) {
         isAnimating = false;
         return;
      }

      move(moveQueue.get(0)[0], moveQueue.get(0)[1]);
      moveQueue.remove(0);
      isAnimating = !moveQueue.isEmpty();
   }

   public boolean isAnimating() {
        return isAnimating;
        
   }

   public void setAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
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

   public void setMoveQueue(List<int[]> moveQueue) {
      this.moveQueue = moveQueue;
   }
}