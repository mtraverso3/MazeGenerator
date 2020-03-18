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

    public Coordinate nextRow()
    {
        return new Coordinate(row + 1, column);
    }

    public Coordinate previousRow()
    {
        return new Coordinate(row - 1, column);
    }

    public Coordinate nextColumn()
    {
        return new Coordinate(row, column + 1);
    }

    public Coordinate previousColumn()
    {
        return new Coordinate(row, column - 1);
    }

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
