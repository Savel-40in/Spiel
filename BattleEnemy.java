import java.awt.*;
import java.util.*;

public class BattleEnemy extends BattleEntity {
    private static final Random random = new Random();
    public BattleEnemy() {
        this.color = Color.RED;
        this.q = 0;
        this.r = 0;
        this.speed = 3;
        this.stackSize = 5;
        this.maxHealthPerEntity = random.nextInt(3) + 3; // Random health between 3 and 5
        this.health = maxHealthPerEntity * stackSize;
        this.attackPowerPerEntity = random.nextInt(3) + 1; // Random attack power between 1 and 3
    }
    
}
