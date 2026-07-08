import java.awt.*;

public class Hex {
    private int q; // axial coordinate
    private int r; // axial coordinate
    // private int s; // derived coordinate (s = -q - r)
    private BattleEntity entity = null; // The entity occupying this hex, if any
    private Polygon hexagon; // The polygon representing the hex shape
    

    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        // this.s = -q - r;

        int size = GameConstants.HEX_SIZE;
        int x = (int) (size * Math.sqrt(3) * (this.q + this.r / 2.0)) + size;
        int y = (int) (size * 3 / 2.0 * this.r) + size;
        this.hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60.0 * i - 30);
            int dx = (int) ((size * Math.cos(angle)) + 0.5); // 1: Round to nearest integer
            int dy = (int) ((size * Math.sin(angle)) + 0.5); // 1: Round to nearest integer
            this.hexagon.addPoint(x + dx, y + dy);
        }
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return hexagon.contains(mouseX, mouseY);
    }

    public void drawHex(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillPolygon(hexagon);
        g.setColor(Color.BLACK);
        g.drawPolygon(hexagon);
    }

    public void drawHexPlayerTurn(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(hexagon);
        
    }

    public void drawFramePlayerTurn(Graphics g) {
        g.setColor(Color.BLACK);
        if (entity != null) { 
            if (entity.getSide() == 1){
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
            
        }
        g.drawPolygon(hexagon);
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public BattleEntity getEntity() {
        return entity;
    }

    public void setEntity(BattleEntity entity) {
        this.entity = entity;
    }

    public boolean isOccupied() {
        return entity != null;
    }

    public void removeEntity() {
        this.entity = null;
    }


    public int distanceTo(Hex other) {
        return (Math.abs(this.q - other.q) + Math.abs(this.q + this.r - other.q - other.r) + Math.abs(this.r - other.r)) / 2;
    }

}
