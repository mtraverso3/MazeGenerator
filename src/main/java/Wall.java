/**
 * This class represents a cell's walls.
 * It stores booleans representing whether a wall exists or not in each direction.
 *
 * @author Marcos Traverso
 */
public class Wall
{
    private boolean north = true;

    private boolean south = true;
    private boolean west = true;
    private boolean east = true;

    public Wall(boolean north, boolean south, boolean west, boolean east)
    {
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
    }

    public Wall()
    {
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

    public void setNorth(boolean north) {
        this.north = north;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public void setEast(boolean east) {
        this.east = east;
    }
}
