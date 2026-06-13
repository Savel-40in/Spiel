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

	public static void replaceScreen(Screen s) {
		popScreen();
		pushScreen(s);
	}

	public static boolean isEmpty() {
		Screen s = getCurrentScreen();
		popScreen();
		boolean empty = screens.isEmpty();
		pushScreen(s);
		return empty;
	}

	public static void clear() {
		screens.clear();
	}


}
