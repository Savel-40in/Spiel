import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SettingScreen implements Screen {

     private ToggleButton toggleButton = new ToggleButton(GameConstants.WINDOW_WIDTH / 2 + 50, GameConstants.WINDOW_HEIGHT / 2 - 50, 100, 100);
    

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent overlay
        g.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Settings", GameConstants.WINDOW_WIDTH / 2 - 100, GameConstants.WINDOW_HEIGHT / 2 - 50);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press to toggle FoW", GameConstants.WINDOW_WIDTH / 2 - 200, GameConstants.WINDOW_HEIGHT / 2 + 20);
        toggleButton.draw(g);

    }
    
    public void input(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ScreenManager.popScreen();
        } 
    }

    public void input(MouseEvent e) {
        toggleButton.onClick(e.getX(), e.getY());
    }

    public boolean isAnimating() {
        return false;
    }

}
