import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Battle implements Screen{
    public void update() {
        
    }
    
    public boolean isAnimating() {
        return false;
    }
    
    public void draw(Graphics g) {
        new Grid(15, 11).drawGrid(g);
    }
    public void input(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ScreenManager.popScreen();
        }
    }
    public void input(MouseEvent e) {
        
    }
}
