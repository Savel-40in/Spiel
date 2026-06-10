import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface Screen {
	void input(KeyEvent e);
	void draw(Graphics g);
	void update();
	boolean isAnimating();
}
