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

   public void movement(KeyEvent e) {
    int key =e.getKeyCode();
    if (key == KeyEvent.VK_UP) {
         ++this.x;
      } else if (key == KeyEvent.VK_DOWN) {
         --this.x;
      } else if (key == KeyEvent.VK_LEFT) {
         --this.x;
      } else if (key == KeyEvent.VK_RIGHT) {
         ++this.y;
      }

   }
// prints out what the key listner does
   public void printInfo() {
      System.out.println("x:" + this.x + " y:" + this.y);
   }
}
