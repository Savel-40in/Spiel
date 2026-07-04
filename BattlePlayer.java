import java.awt.*;

public class BattlePlayer extends BattleEntity {
    
    public BattlePlayer() {
        this.color = Color.BLUE;
        this.q = 0;
        this.r = 0;
        this.speed = 1;
        this.stackSize = 10;
        this.maxHealthPerEntity = 10; // Fixed health for player
        this.health = maxHealthPerEntity * stackSize;
        this.maxAttack = 5; // Fixed attack power for player
        this.minAttack = 5; // Fixed attack power for player
    }
    
}
