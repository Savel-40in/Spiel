import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class BattleEntity implements Animatable {
    protected int q;
    protected int r;
    protected int speed;
    protected int stackSize;
    protected int maxHealthPerEntity;
    protected int health;
    protected int maxAttack;
    protected int minAttack;
    protected int side; // 0 - allies, 1 - enemies
    protected List<int[]> path;
    protected int stepCount = 0;

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

    public void setPath(List<int[]> path) {
        this.path = path;
    }

    public void update() {
        if (path.isEmpty() || stepCount > speed) {
            stepCount = 0;
            return;
        }
        stepCount++;
        move(path.get(0)[0], path.get(0)[1]);
        path.remove(0); 
    }

    public boolean isAnimating() {
        return !path.isEmpty() && stepCount != 0;
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
        int damage = 0;
        for(int i = 0; i < stackSize; i++) {
            damage += (int) (Math.random() * (maxAttack - minAttack + 1)) + minAttack;
        }
        return damage;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; 
        }
        stackSize = (int) Math.ceil((double) health / maxHealthPerEntity); // Update stack size based on health
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public int getSide() {
        return side;
    }

}