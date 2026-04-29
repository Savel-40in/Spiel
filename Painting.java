import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Painting extends JPanel implements KeyListener{

    Map m = new Map(21);
    Player p = new Player(1, 1);

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