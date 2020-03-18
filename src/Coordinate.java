public class Coordinate {
    private int row;

    private int column;
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void addToRow(int row) {
        this.row += row;
    }

    public void addToColumn(int column) {
        this.column += column;
    }
}
