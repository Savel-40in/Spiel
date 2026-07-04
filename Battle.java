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
    private boolean isAnimating = false;
    private int currentEntityIndex = 0;
    private boolean isPlayerTurn = false;
    private List<Animatable> animatables = new ArrayList<>();


    public Battle(Party playerParty, Party enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        battleEntities.addAll(playerParty.getEntities());
        battleEntities.addAll(enemyParty.getEntities());
        battleEntities.sort(Comparator.comparingInt(BattleEntity::speed).reversed());
        for (BattleEntity entity : battleEntities) {
            b.placeEntity(entity);
        }
        nextTurn(); // Start the first turn
    }

    public void update() {
        isAnimating = false;
        for (int i = animatables.size() - 1; i >= 0; i--) {

            Animatable a = animatables.get(i);

            a.update();

            if (a.isAnimating()) {
                isAnimating = true;
            } else {
                animatables.remove(i);
                
                nextTurn();
            }
        }
    }
    
    public boolean isAnimating() {
        return isAnimating;
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
        if (isPlayerTurn) {
            isPlayerTurn = false;
            nextTurn();  
        }
    }
    public void input(MouseEvent e) {
        if (isPlayerTurn) {
            isPlayerTurn = false;
            nextTurn();  
        }
    }

    private void nextTurn() {
        int previousIndex = (currentEntityIndex - 1 + battleEntities.size()) % battleEntities.size();
        BattleEntity previousEntity = battleEntities.get(previousIndex);
        b.placeEntity(previousEntity);
        BattleEntity currentEntity = battleEntities.get(currentEntityIndex);
        currentEntityIndex = (currentEntityIndex + 1) % battleEntities.size();
        if (!currentEntity.isAlive()) {
            nextTurn();
            return;
        }
        if (currentEntity.getSide() == 0) {
            isPlayerTurn = true;
            return; // Wait for player input
        } else {
            enemyAction(currentEntity);
        }
        
    }

    private void enemyAction(BattleEntity entity) {
        List<int[]> path = hexesToCoords(b.findPath(entity.q(), entity.r())); 
        entity.setPath(path);
        animatables.add(entity);
        isAnimating = true;
    }

    private List<int[]> hexesToCoords(List<Hex> hexes) {
        List<int[]> coords = new ArrayList<>();
        for (Hex hex : hexes) {
            int q = hex.getQ();
            int r = hex.getR();
            coords.add(new int[]{q, r});
        }
        return coords;
    }

    private List<Hex> coordsToHexes(List<int[]> coords) {
        List<Hex> hexes = new ArrayList<>();
        for (int[] coord : coords) {
            int q = coord[0];
            int r = coord[1];
            Hex hex = b.getHex(q + (r - (r % 2)) / 2, r);
            if (hex != null) {
                hexes.add(hex);
            }
        }
        return hexes;
    }
    
}   
