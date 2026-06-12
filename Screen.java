import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Screen {
	void input(KeyEvent e);
	void input(MouseEvent e);
	void draw(Graphics g);
	void update();
	boolean isAnimating();
}
