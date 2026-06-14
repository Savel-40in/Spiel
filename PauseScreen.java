import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PauseScreen implements Screen {
    
    
    @Override
    public void input(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ScreenManager.popScreen();
        }

        else if (e.getKeyCode() == KeyEvent.VK_Q) {
            ScreenManager.replaceScreen(new MenuScreen());
        }
    }

    @Override
    public void input(MouseEvent e) {
        // No mouse input for pause screen
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent overlay
        g.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("PAUSED", GameConstants.WINDOW_WIDTH / 2 - 100, GameConstants.WINDOW_HEIGHT / 2);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Press ESC to resume", GameConstants.WINDOW_WIDTH / 2 - 100, GameConstants.WINDOW_HEIGHT / 2 + 50);
        g.drawString("Press Q to quit to menu", GameConstants.WINDOW_WIDTH / 2 - 100, GameConstants.WINDOW_HEIGHT / 2 + 80);
    }

    @Override
    public void update() {
        // No updates needed for pause screen
    }

    @Override
    public boolean isAnimating() {
        return false; // Pause screen does not animate
    }
    
}
