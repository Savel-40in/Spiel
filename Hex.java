import java.awt.*;

public class Hex {
    private int q; // axial coordinate
    private int r; // axial coordinate
    private int s; // derived coordinate (s = -q - r)

    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }

    public void drawHex(Graphics g) {
        int size = GameConstants.HEX_SIZE;
        int x = (int) (size * Math.sqrt(3) * (this.q + this.r / 2.0)) + size;
        int y = (int) (size * 3 / 2.0 * this.r) + size;
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i - 30);
            int dx = (int) ((size * Math.cos(angle)) + 0.5); // 1: Round to nearest integer
            int dy = (int) ((size * Math.sin(angle)) + 0.5); // 1: Round to nearest integer
            hexagon.addPoint(x + dx, y + dy);
        }
        g.setColor(Color.GRAY);
        g.fillPolygon(hexagon);
        g.setColor(Color.BLACK);
        g.drawPolygon(hexagon);
    }

    public boolean equals(Hex other) {
        return this.q == other.q && this.r == other.r && this.s == other.s;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

}
