import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Painting extends JPanel implements KeyListener{

    Game game = new Game();
    Map m = game.getMap();
    Player p = game.getPlayer();

    public Painting(Frame f){
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        m.drawMap(g);
        p.drawPlayer(g);
    }

    public void keyPressed(KeyEvent e) {
        p.movement(e);
        // p.printInfo();
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}