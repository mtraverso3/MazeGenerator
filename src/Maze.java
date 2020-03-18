import java.util.Arrays;

public class Maze {
    private final Cell[][] maze;
    private Coordinate current;
    private final int width;
    private final int height;

    Maze(int width, int height, Coordinate start) {
        this.width = width;
        this.height = height;
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
        double[] probabilites = new double[4];
        Arrays.fill(probabilites, 0.25);

        if (current.getRow() == 0) {
            probabilites[0] = 0;
        }
        if (current.getColumn() == 0) {
            probabilites[2] = 0;
        }
        if (current.getRow() == width - 1) {
            probabilites[1] = 0;
        }
        if (current.getColumn() == width - 1) {
            probabilites[3] = 0;
        }


        return Direction.NORTH;
    }

}

enum Direction {
    NORTH, SOUTH, WEST, EAST, BACK
}
