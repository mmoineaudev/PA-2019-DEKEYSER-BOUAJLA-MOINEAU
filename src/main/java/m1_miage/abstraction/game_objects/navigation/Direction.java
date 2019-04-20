package m1_miage.abstraction.game_objects.navigation;

/**
 * On va donner une direction aux vaisseaux pour les orienter
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

    public static Direction random() {
        return Direction.values()[(int) (Direction.values().length*Math.random())];
    }

    public int getValue() {
        return value;
    }
}
