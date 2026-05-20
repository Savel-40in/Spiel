import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Painting extends JPanel implements KeyListener{

    Game game = new Game();

    public Painting(Frame f){
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        game.draw(g);
    }

    public void keyPressed(KeyEvent e) {
        game.input(e);
        
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}