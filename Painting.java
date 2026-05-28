import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Painting extends JPanel implements KeyListener{

    Game game = new Game();
    Timer timer;

    public Painting(Frame f){
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(16, e -> {
            game.update();
            repaint();
            if (!game.isAnimating()) {
                timer.stop();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        game.draw(g);
    
    }

    public void keyPressed(KeyEvent e) {
        game.input(e);

        if (game.isAnimating()) {
            timer.start();
        }
        
        repaint();
    
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}