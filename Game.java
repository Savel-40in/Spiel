import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

public class Game implements Screen{
    private Map m;
    private Player p;
    private StarBar s;
    private List<Animatable> animatables = new ArrayList<>();
    private boolean isAnimating = false;
    private FoW f;

    private Random random = new Random();
    
    public Game() {
        p = new Player(1, 1);
        newGame();
    }

    public void newGame() {
        m = new Map();
        f = new FoW();
        s = new StarBar();
        generateEvents();
        updateFoW();
        animatables.clear();
    }
    
    public void draw(Graphics g) {
		m.drawMap(g);
    	p.drawPlayer(g);
		f.drawFogOfWar(g);
		s.drawStars(g);
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
    		newGame();
    	}

        if (key == KeyEvent.VK_E || key == KeyEvent.VK_F) {
            Cell currentCell = m.getCell(p.x(), p.y()); 
            currentCell.getEvent().trigger().accept(this);
            currentCell.setEvent(new NoEvent()); // Remove the event after triggering it
        }

    	if (key == KeyEvent.VK_ESCAPE) {
    		ScreenManager.pushScreen(new PauseScreen());
    	}

        updateFoW();
    	
    }

    public void input (MouseEvent e) {
        int mx = e.getX() / GameConstants.CELL_SIZE;
        int my = e.getY() / GameConstants.CELL_SIZE;
        if (mx < GameConstants.MAP_SIZE && my < GameConstants.MAP_SIZE && m.getCell(mx, my).isVisited()) {
            p.setMoveQueue(cellsToCoords(m.BFS(p.x(), p.y(), mx, my)));
            if (!p.isAnimating()) {
                animatables.add(p);
                p.setAnimating(true);
            } // Add the player to the list of animatables to update its movement
            isAnimating = true;
        }
        updateFoW();
    }

    public void update() {
        isAnimating = false;
        for (int i = animatables.size() - 1; i >= 0; i--) {

            Animatable a = animatables.get(i);

            a.update();

            if (a.isAnimating()) {
                isAnimating = true;
            } else {
                animatables.remove(i);
            }
        updateFoW();
        }
    }
    	
    private void movePlayer(int newX, int newY) {
    	if (!m.getCell(newX, newY).isWall()) {
    		p.move(newX, newY);
            m.getCell(newX, newY).setVisited(true);
    	}
    }
         
    private List<Cell> coordsToCells(List<int[]> coords) {
        List<Cell> cells = new ArrayList<>();
        for (int[] c : coords) {
            cells.add(m.getCell(c[0], c[1]));
        }
        return cells;
    }

    private List<int[]> cellsToCoords(List<Cell> cells) {
        List<int[]> coords = new ArrayList<>();
        for (Cell c : cells) {
            coords.add(new int[] {c.getX(), c.getY()});
        }
        return coords;
    }

    private void generateEvents() {
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(m.getRooms().size());
            m.getRooms().get(index).setEvent(new ChestEvent());
            m.getRooms().remove(index); // Remove the room from the list to avoid placing multiple events in the same room
        }

        // for (Cell end : m.getEnds()) {
        //     if (Math.random() < 0.3) {
        //         end.setEvent(new ChestEvent());
        //     }
        // }
        
        m.getEnds().get(random.nextInt(m.getEnds().size())).setEvent(new PortalEvent());
    }

    private void updateFoW() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int checkX = p.x() + i;
                int checkY = p.y() + j;
                f.reveal(checkX, checkY);
                if ( !m.getCell(checkX, checkY).isWall()){
                    m.getCell(checkX, checkY).setVisited(true);
                }
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
    
    public StarBar getStarBar() {
        return s;
    }

    public List<Animatable> getAnimatables() {
        return animatables;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
    }

    public Player getPlayer() {
        return p;
    }
}