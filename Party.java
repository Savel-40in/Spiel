import java.util.*;

public class Party {
    private List<BattleEntity> entities = new ArrayList<>();
    private int side = 0; // 0 - союзники, 1 - враги

    public void setSide(int side) {
        this.side = side;
    }

    public void addEntity(BattleEntity entity) {
        entities.add(entity);
    }

    public List<BattleEntity> getEntities() {
        deployUnits();
        return entities;
    }

    public void removeEntity(BattleEntity entity) {
        entities.remove(entity);
    }

    private void deployUnits() {
        int spacing = (GameConstants.BATTLEFIELD_HEIGHT + 1) / (entities.size() + 1);
        for (int i = 0; i < entities.size(); i++) {
            BattleEntity entity = entities.get(i);
            int col = side * (GameConstants.BATTLEFIELD_WIDTH - 1); // 0 for allies, width-1 for enemies
            int row = (i + 1) * spacing - 1; // Deploy in a vertical line

            int q = col - (row - (row % 2)) / 2;
            int r = row;

            entity.move(q, r);
        }

    } 

}
