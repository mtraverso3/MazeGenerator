public class Maze {
    private final Cell[][] maze;
    private Coordinate current;

    Maze(int width, int height, Coordinate start) {
        this.current = start;
        this.maze = new Cell[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.maze[i][j] = new Cell(Wall.DEFAULT, 0);
            }
        }
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public void buildMaze() {
        Direction next = findNext();
        //TODO build maze
    }

    private Direction findNext() {   //TODO implement findNext


        if (current.getRow() == 0) {

        }
        return Direction.NORTH;
    }

}

enum Direction {
    NORTH, SOUTH, WEST, EAST, BACK
}
