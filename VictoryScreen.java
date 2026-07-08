import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class VictoryScreen implements Screen {
    @Override
    public void input(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ScreenManager.popScreen();
        }
        
    }

    @Override
    public void input(MouseEvent e) {
        // No mouse input for win screen
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent overlay
        g.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("YOU WIN!", GameConstants.WINDOW_WIDTH / 2 - 100, GameConstants.WINDOW_HEIGHT / 2);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ENTER to return to game", GameConstants.WINDOW_WIDTH / 2 - 150, GameConstants.WINDOW_HEIGHT / 2 + 50);
    }

    @Override
    public void update() {
        // No updates needed for win screen
    }

    @Override
    public boolean isAnimating() {
        return false; // Win screen does not animate
    }

    
}
