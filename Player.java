import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
   private int x;
   private int y;
   private Map m;


   public Player(int x, int y, Map m) {
      this.x = x;
      this.y = y;
      this.m = m;
   }

   public void movement(KeyEvent e) {
    int key = e.getKeyCode();
    if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !m.getCell(x, y-1).isWall()) {
         --this.y;
      } else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !m.getCell(x, y+1).isWall()) {
         ++this.y;
      } else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !m.getCell(x-1, y).isWall()) {
         --this.x;
      } else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !m.getCell(x+1, y).isWall()) {
         ++this.x;
      }

   }
// prints out what the key listner does
//    public void printInfo() {
//       System.out.println("x:" + this.x + " y:" + this.y);
//    }

   public void drawPlayer(Graphics g) {
		g.setColor(Color.GREEN);
    	g.fillRect(x*30, y*30, 30, 30);
	}
}