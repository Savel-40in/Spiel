public class ContinueButton extends Button {
    public ContinueButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "Continue Game";
    }

    public void onClick() {
        if (!ScreenManager.isEmpty()) {
			ScreenManager.popScreen();
        }
    }
    
}
