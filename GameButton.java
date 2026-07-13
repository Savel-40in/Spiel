public class GameButton extends Button {
    public GameButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "NewGame";
    }

    public void onClick() {
        ScreenManager.clear();
        ScreenManager.pushScreen(new Game());
    }
    
}
