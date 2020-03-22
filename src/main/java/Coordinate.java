/**
 * This class represents a Coordinate point.
 * This is used the maze to keep track of the current/past points traversed during maze generation.
 *
 * @author Marcos Traverso
 */
public class Coordinate
{
    private final int row;
    private final int column;

    public Coordinate(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    /**
     * This method returns a Coordinate given a direction to move.
     * It is used to determine the coordinate of the next position in the maze.
     */
    public Coordinate move(Direction direction)
    {
        switch (direction) {
            case NORTH:
                return new Coordinate(row - 1, column);
            case SOUTH:
                return new Coordinate(row + 1, column);
            case WEST:
                return new Coordinate(row, column - 1);
            case EAST:
                return new Coordinate(row, column + 1);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", row, column);
    }
}
