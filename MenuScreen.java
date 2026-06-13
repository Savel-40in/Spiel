import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuScreen implements Screen{
	
	public void input (KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_G) {
			ScreenManager.clear();
    		ScreenManager.pushScreen(new Game());
    	}
		else if (key == KeyEvent.VK_Q && !ScreenManager.isEmpty()) {
			ScreenManager.popScreen();
		}
		else if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

    	
    	
    }

	public void input(MouseEvent e) {
		// No mouse input for the menu
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(36f));
		g.drawString("Dangeons", GameConstants.WINDOW_WIDTH / 2 - 100, GameConstants.WINDOW_HEIGHT / 2 - 50);
		g.setFont(g.getFont().deriveFont(18f));
		g.drawString("Press G to Start", GameConstants.WINDOW_WIDTH / 2 - 80, GameConstants.WINDOW_HEIGHT / 2 + 20);
		g.drawString("Press Q to Go Back", GameConstants.WINDOW_WIDTH / 2 - 80, GameConstants.WINDOW_HEIGHT / 2 + 35);
		g.drawString("Press ESC to Exit", GameConstants.WINDOW_WIDTH / 2 - 80, GameConstants.WINDOW_HEIGHT / 2 + 50);
	} 
	
	public void update() {
		
	}
	
	public boolean isAnimating() {
		return false;
	}

}
