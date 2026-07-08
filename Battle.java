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
    private List<Hex> validHex;


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

                attack();
                
                nextTurn();
            }
        }
    }
    
    public boolean isAnimating() {
        return isAnimating;
    }
    
    public void draw(Graphics g) {
        b.draw(g);
        
        if (isPlayerTurn) {
            validHex = new ArrayList<>();
            BattleEntity currentEntity = battleEntities.get((currentEntityIndex - 1 + battleEntities.size()) % battleEntities.size());
            Hex currentHex = b.getHex(b.axialToOffset(currentEntity.q(), currentEntity.r()), currentEntity.r());
            for (int i = 0; i < b.getWidth(); i++) {
                for (int j = 0; j < b.getHeight(); j++) {
                    Hex hex = b.getHex(i, j); 
                    if (currentHex.distanceTo(hex) <= currentEntity.speed()) {
                        hex.drawHexPlayerTurn(g);
                    } 
                    if (currentHex.distanceTo(hex) <= currentEntity.speed() + 1) {
                        hex.drawFramePlayerTurn(g);
                        validHex.add(hex);
                    }
                }
            }
        }

        for (BattleEntity entity : battleEntities) {
            if (entity.isAlive()) {
                entity.draw(g);
            }
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
            for (Hex hex : validHex) {
                if (hex.isClicked(e.getX(), e.getY())) {
                    BattleEntity currentEntity = battleEntities.get((currentEntityIndex - 1 + battleEntities.size()) % battleEntities.size());
                    
                    List<int[]> path = hexesToCoords(b.findPath(currentEntity.q(), currentEntity.r(), hex.getQ(), hex.getR()));
                    currentEntity.setPath(path);
                    animatables.add(currentEntity);
                    isAnimating = true;
                    b.removeEntity(currentEntity);

                    if (hex.isOccupied() && hex.getEntity().getSide() == 1) {
                        hex.getEntity().takeDamage(currentEntity.getDamage());
                        System.out.println("Entity at (" + hex.getEntity().q() + ", " + hex.getEntity().r() + ") took " + currentEntity.getDamage() + " damage. Remaining health: " + hex.getEntity().getHealth());
                        if (!hex.getEntity().isAlive()) {
                            enemyParty.removeEntity(hex.getEntity());
                            if (enemyParty.isEmpty()) {
                                System.out.println("All enemy entities have been defeated. Victory!");
                                restorePlayerPartyHealth();
                                ScreenManager.replaceScreen(new VictoryScreen());
                            }
                            b.removeEntity(hex.getEntity());
                        }
                    }
                    
                    isPlayerTurn = false;
                }
            }
        }
            
        
    
    }

    private void restorePlayerPartyHealth() {
        for (BattleEntity entity : playerParty.getEntities()) {
            entity.RestoreStackHealth();
        }
    }
    

    private void nextTurn() {
        int previousIndex = (currentEntityIndex - 1 + battleEntities.size()) % battleEntities.size();
        BattleEntity previousEntity = battleEntities.get(previousIndex);
        b.placeEntity(previousEntity);
        BattleEntity currentEntity = battleEntities.get(currentEntityIndex);
        currentEntityIndex = (currentEntityIndex + 1) % battleEntities.size();

        System.out.println("Current Entity Index: " + previousIndex);
        System.out.println(currentEntity.toString());
        System.out.println("----------------------------------------------------");


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

    private void attack() {
        BattleEntity currentEntity = battleEntities.get((currentEntityIndex - 1 + battleEntities.size()) % battleEntities.size());
        if (!currentEntity.isAlive() || currentEntity.getSide() == 0) {
            return; // Skip dead entities and player
        }
        List<Hex> neighbors = b.getNeighbors(b.getHex(b.axialToOffset(currentEntity.q(), currentEntity.r()), currentEntity.r()));
        for (Hex neighbor : neighbors) {
            if (neighbor.isOccupied() && neighbor.getEntity().getSide() != currentEntity.getSide()) {
                BattleEntity target = neighbor.getEntity();

                int damage = currentEntity.getDamage();
                target.takeDamage(damage);
                System.out.println("Entity at (" + target.q() + ", " + target.r() + ") took " + damage + " damage. Remaining health: " + target.getHealth());
                if (!target.isAlive()) {
                    b.getHex(b.axialToOffset(target.q(), target.r()), target.r()).setEntity(null);
                    System.out.println("Entity at (" + target.q() + ", " + target.r() + ") has been defeated.");
                    playerParty.removeEntity(target);
                    if (playerParty.isEmpty()) {
                        System.out.println("All player entities have been defeated. Game Over.");
                        ScreenManager.pushScreen(new DefeatScreen()); 
                    }
                }

                break; // Attack only one enemy per turn
                
            }
            
        }
    }

    private void enemyAction(BattleEntity entity) {
        List<int[]> path = hexesToCoords(b.findPath(entity.q(), entity.r())); 
        entity.setPath(path);
        animatables.add(entity);
        isAnimating = true;
        b.removeEntity(entity);
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
