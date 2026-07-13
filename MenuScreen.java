import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuScreen implements Screen{

	private Map m;
	private Background b = new Background(0, 0, 0, 255);
	private Player p;
	private List<Animatable> animatables = new ArrayList<>();
	private int currentAnimatableIndex = 0;
	private Animatable[] animatableArray = new Animatable[3];
	private Random random = new Random();
	private Button[] buttons = new Button[5];


	public MenuScreen() {
		p = new Player(1, 1);
		newMap();
		animatableArray[0] = b;
		animatableArray[1] = p;
		animatableArray[2] = b;
		animatables.add(animatableArray[0]);

		buttons[0] = new GameButton(650, 200, 260, 55);
		buttons[1] = new ContinueButton(650, 290, 120, 50);
		buttons[2] = new TutorialButton(790, 290, 120, 50);
		buttons[3] = new SettingButton(650, 370, 120, 50);
		buttons[4] = new ExitButton(790, 370, 120, 50);
	}

	

	public void update() {
		for (Animatable a : animatables) {
			a.update();
			if (!a.isAnimating()) {
				animatables.remove(a);
				nextAnimatable();
				break; // Exit the loop to avoid ConcurrentModificationException
			}
		}
	}

	private void newMap() {
		m = new Map();
		m.menuMap();
		Cell portalCell = m.getEnds().get(random.nextInt(m.getEnds().size()));
		portalCell.setEvent(new PortalEvent());
		p.setMoveQueue(m.cellsToCoords(m.BFS(p.x(), p.y(), portalCell.getX(), portalCell.getY())));
	}

	private void nextAnimatable() {
		currentAnimatableIndex = (currentAnimatableIndex + 1) % animatableArray.length;
		animatables.add(animatableArray[currentAnimatableIndex]);
		if (currentAnimatableIndex == 0) {
			newMap();
		}
	}
	
	public void input (KeyEvent e) {
    }

	public void input(MouseEvent e) {
		for (Button b : buttons) {
			if (b.isClicked(e.getX(), e.getY())){
				b.onClick();
				break;
			}
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
		
		for (int i = 0; i < GameConstants.MAP_SIZE; i++) {
			for (int j = 0; j < GameConstants.MAP_SIZE; j++) {
				m.getCell(i, j).drawCell(g,75, 250, 20);
			}
		}

		p.drawPlayer(g, 75, 250, 20);

		b.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(72f));
		g.drawString("Dungeon", 160, 100);

		for (Button b : buttons) {
			b.draw(g);
		}
	} 
	
	
	public boolean isAnimating() {
		return true;
	}

}
