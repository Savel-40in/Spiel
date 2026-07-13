public class SettingButton extends Button {
    
    public SettingButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "Setting";
    }

    public void onClick() {
        ScreenManager.pushScreen(new SettingScreen());        
    }
    
}
