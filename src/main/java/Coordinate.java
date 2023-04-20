/**
 * This class represents a Coordinate point.
 * This is used the maze to keep track of the current/past points traversed during maze generation.
 *
 * @author Marcos Traverso
 */
public record Coordinate(int row, int column)
{

    /**
     * This method returns a Coordinate given a direction to move.
     * It is used to determine the coordinate of the next position in the maze.
     */
    public Coordinate move(Direction direction)
    {
        return switch (direction) {
            case NORTH -> new Coordinate(row - 1, column);
            case SOUTH -> new Coordinate(row + 1, column);
            case WEST -> new Coordinate(row, column - 1);
            case EAST -> new Coordinate(row, column + 1);
            case BACK -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", row, column);
    }
}
