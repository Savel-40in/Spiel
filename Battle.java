import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Battle implements Screen{
    private Battlefield b = new Battlefield(GameConstants.BATTLEFIELD_WIDTH, GameConstants.BATTLEFIELD_HEIGHT);
    private Party playerParty;
    private Party enemyParty;
    private List<BattleEntity> battleEntities = new ArrayList<>();

    public Battle(Party playerParty, Party enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        battleEntities.addAll(playerParty.getEntities());
        battleEntities.addAll(enemyParty.getEntities());
        battleEntities.sort(Comparator.comparingInt(BattleEntity::speed).reversed());
    }

    public void update() {
        
    }
    
    public boolean isAnimating() {
        return false;
    }
    
    public void draw(Graphics g) {
        b.draw(g);
        for (BattleEntity entity : battleEntities) {
            entity.draw(g);
        }

    }
    public void input(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            ScreenManager.popScreen();
        }
    }
    public void input(MouseEvent e) {
        
    }
}
