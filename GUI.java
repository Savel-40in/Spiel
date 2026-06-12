public class GUI {

    private Frame f;
    private Painting painting;

    public GUI() {
        this.f = new Frame("Dangeons", -1, -1, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        this.painting = new Painting(this.f);
        this.f.getContentPane().add(this.painting);
        this.f.setupFrame();
    }
}