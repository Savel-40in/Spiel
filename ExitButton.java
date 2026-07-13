public class ExitButton extends Button {
    public void onClick() {
        System.exit(0);
    }

    public ExitButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "Exit";
    }
    
}
