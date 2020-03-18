public class Wall {
    public static final Wall DEFAULT = new Wall(true, true, true, true);

    private final boolean north;
    private final boolean south;
    private final boolean west;
    private final boolean east;

    public Wall(boolean north, boolean south, boolean west, boolean east) {
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
    }

    public boolean getNorth() {
        return north;
    }

    public boolean getSouth() {
        return south;
    }

    public boolean getWest() {
        return west;
    }

    public boolean getEast() {
        return east;
    }
}
