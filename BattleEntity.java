import java.awt.*;

public abstract class BattleEntity {
    protected int q;
    protected int r;
    protected int speed;
    protected int stackSize;
    protected int maxHealthPerEntity;
    protected int health;
    protected int attackPowerPerEntity;

    private int size = 30;
    protected Color color;

    public final void draw(Graphics g) {
        g.setColor(color);
        int hexSize = GameConstants.HEX_SIZE;
        int x = (int) (hexSize * Math.sqrt(3) * (this.q + this.r / 2.0)) + hexSize - size / 2;
        int y = (int) (hexSize * 3 / 2.0 * this.r) + hexSize - size / 2;
        g.fillOval(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, size, size);
        g.drawString(""+stackSize, x + size / 2, y + size / 2);

    }


    public int q() {
        return q;
    }

    public int r() {
        return r;
    }

    public int speed() {
        return speed;
    }

    public void move(int newQ, int newR) {
        this.q = newQ;
        this.r = newR;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return attackPowerPerEntity*stackSize;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Ensure health doesn't go below 0
        }
        stackSize = (int) Math.ceil((double) health / maxHealthPerEntity); // Update stack size based on health
    }

    public boolean isAlive() {
        return health > 0;
    }

}