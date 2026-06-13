import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Painting extends JPanel implements KeyListener, MouseListener {

    ScreenManager screenManager = new ScreenManager();
    Timer timer;

    public Painting(Frame f){
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        ScreenManager.pushScreen(new MenuScreen());
        timer = new Timer(16, e -> {
            screenManager.getCurrentScreen().update();
            repaint();
            if (!screenManager.getCurrentScreen().isAnimating()) {
                timer.stop();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        screenManager.getCurrentScreen().draw(g);
    
    }

    public void keyPressed(KeyEvent e) {
        screenManager.getCurrentScreen().input(e);

        if (screenManager.getCurrentScreen().isAnimating()) {
            timer.start();
        }
        
        repaint();
    
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        screenManager.getCurrentScreen().input(e);

        if (screenManager.getCurrentScreen().isAnimating()) {
            timer.start();
        }
        
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}