import java.util.ArrayDeque;
import java.util.Deque;

public class ScreenManager {
	private static Deque<Screen> screens = new ArrayDeque<>();
	
	
	public static Screen getCurrentScreen() {
		return screens.peek();
	}
	
	public static void pushScreen(Screen s) {
		screens.push(s);
	}
	
	public static void popScreen() {
		screens.pop();
	}

}
