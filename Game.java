import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Game {
    private Map m;
    private Player p;
    private FoW f;
    
    public Game() {
        m = new Map();
        f = new FoW();
        p = new Player(1, 1);
        m.getCell(3, 3).setEvent(new PortalEvent());
        updateFoW();
    }
    
    public void draw(Graphics g) {
		m.drawMap(g);
    	p.drawPlayer(g);
		f.drawFogOfWar(g);
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

        updateFoW();
    	
    }
    	
    private void movePlayer(int newX, int newY) {
    	if (!m.getCell(newX, newY).isWall()) {
    		p.move(newX, newY);
    	}
    }
    
    public void newMap() {
    	m = new Map();
    	f = new FoW();
    }

    private void updateFoW() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int checkX = p.x() + i;
                int checkY = p.y() + j;
                f.reveal(checkX, checkY);
            }
        }
    }

    // GETTERS + SETTERS
    public Map getMap() {
        return m;
    }

    public FoW getFoW() {
        return f;
    }

    public void setMap(Map m) {
        this.m = m;
    }

    public void setFoW(FoW f) {
        this.f = f;
    }
}