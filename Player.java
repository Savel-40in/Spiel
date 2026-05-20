import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    	g.fillRect(x*30, y*30, 30, 30);
	}
   
   public int x() {
	   return x;
   }
   
   public int y() {
	   return y;
   }
}