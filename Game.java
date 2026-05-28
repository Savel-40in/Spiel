import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Game {
    private Map m;
    private Player p;
    private StarBar s;
    private Animatable[] a;
    private boolean isAnimating = false;
    
    public Game() {
        m = new Map();
        p = new Player(1, 1);
        s = new StarBar();
        a = new Animatable[1];
        m.getCell(3, 3).setEvent(new PortalEvent());
        m.getCell(5, 5).setEvent(new ChestEvent());
        m.getCell(3, 5).setEvent(new ChestEvent());
        m.getCell(5, 3).setEvent(new ChestEvent());

    }
    
    public void draw(Graphics g) {
		m.drawMap(g);
		s.drawStars(g);
    	p.drawPlayer(g);
	} 
    
    public void input (KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
    		movePlayer(p.x(), p.y()-1);
    	}else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
    		movePlayer(p.x(), p.y()+1);
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        	 movePlayer(p.x()-1, p.y());
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	 movePlayer(p.x()+1, p.y());
        }
    	
    	if (key == KeyEvent.VK_R) {
    		newMap();
    	}

        if (key == KeyEvent.VK_E) {
            Cell currentCell = m.getCell(p.x(), p.y()); 
            currentCell.getEvent().trigger().accept(this);
            currentCell.setEvent(new NoEvent()); // Remove the event after triggering it
        }

    	if (key == KeyEvent.VK_ESCAPE) {
    		System.exit(0);
    	}
    	
    }

    public void update() {
        a[0].update();
        isAnimating = a[0].isAnimating();
    }
    	
    private void movePlayer(int newX, int newY) {
    	if (!m.getCell(newX, newY).isWall()) {
    		p.move(newX, newY);
    	}
    }
    
    public void newMap() {
    	m = new Map();
        s = new StarBar();
    }

    public Map getMap() {
        return m;
    }

    public Player getPlayer() {
        return p;
    }
    
    public StarBar getStarBar() {
        return s;
    }

    public Animatable[] getAnimatables() {
        return a;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
    }
}