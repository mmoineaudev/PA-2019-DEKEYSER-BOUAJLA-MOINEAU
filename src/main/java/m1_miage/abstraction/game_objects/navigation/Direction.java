package m1_miage.abstraction.game_objects.navigation;

/**
 * Représente la direction des intelligentSprites
 */
public enum  Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3) ;

    private int value;

    Direction(int i) {
        this.value = i;
    }

    /**
     * Permet d'instancier une direction aléatoire
     * @return une Direction 
     */
    public static Direction random() {
        return Direction.values()[(int) (Direction.values().length*Math.random())];
    }

    public int getValue() {
        return value;
    }
}
