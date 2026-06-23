public final class GameConstants {

    private GameConstants() {}

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;

    public static final int CELL_SIZE = 30;
    public static final int MAP_SIZE = 25;

    public static final double LOOP_CHANCE = 0.05; // 5% chance to create a loop

    public static final int ROOM_COUNT = 5; // Number of rooms to generate
    public static final int MIN_ROOM_SIZE = 3; // Minimum size of a room
    public static final int MAX_ROOM_SIZE = 7; // Maximum size of a room

    public static final int ENEMY_COUNT = 10; // Number of enemies to generate

    public static final int MIN_ENEMY_HEALTH = 1; // Minimum health of an enemy
    public static final int MAX_ENEMY_HEALTH = 5; // Maximum health of an enemy

    public static final int MIN_ENEMY_DAMAGE = 1; // Minimum damage an enemy can deal
    public static final int MAX_ENEMY_DAMAGE = 3; // Maximum damage an enemy can deal

    public static final int MIN_ENEMY_SPEED = 1; // Minimum speed of an enemy
    public static final int MAX_ENEMY_SPEED = 3; // Maximum speed of an enemy

    public static final int PLAYER_MAX_HEALTH = 10; // Maximum health of the player
    public static final int PLAYER_DAMAGE = 2; // Damage the player can deal
    public static final int PLAYER_SPEED = 2; // Speed of the player

    public static final int FOG_RADIUS = 3; // Radius of the fog of war around the player

    public static  final int HEX_SIZE = 30; // Size of the hexagon for hexagonal grid


}