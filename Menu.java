import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Menu implements Screen{
	
	public void input (KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_G) {
    		ScreenManager.pushScreen(new Game());
    	}

    	if (key == KeyEvent.VK_ESCAPE) {
    		System.exit(0);
    	}
    	
    }
	
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 100, 100);
	} 
	
	public void update() {
		
	}
	
	public boolean isAnimating() {
		return false;
	}

}
