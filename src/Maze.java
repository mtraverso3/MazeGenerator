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
                this.maze[i][j] = new Cell(Wall.DEFAULT, false);
            }
        }
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public void buildMaze() {
        Coordinate[] stack = new Coordinate[width * height];
        stack[0] = current;
        int currentStack = 0;
        int step = 0;

        do {
            Direction next = findNext();

            if (next == Direction.BACK) {
                stack[currentStack] = null;
                currentStack--;
                current = stack[currentStack];
            } else {
                Cell currentCell = maze[current.getRow()][current.getColumn()];
                currentCell.setVisited(true);
                currentCell.setDepth(currentStack);
                currentCell.setStep(step);


                switch (next) {
                    case NORTH:                                 //removes connecting walls, moves current
                        currentCell.getWall().setNorth(false);
                        maze[current.getRow() - 1][current.getColumn()].getWall().setSouth(false);
                        current.addToRow(-1);
                        break;
                    case SOUTH:
                        currentCell.getWall().setSouth(false);
                        maze[current.getRow() + 1][current.getColumn()].getWall().setNorth(false);
                        current.addToRow(1);
                        break;
                    case WEST:
                        currentCell.getWall().setWest(false);
                        maze[current.getRow()][current.getColumn() - 1].getWall().setEast(false);
                        current.addToColumn(-1);
                        break;
                    case EAST:
                        currentCell.getWall().setEast(false);
                        maze[current.getRow()][current.getColumn() + 1].getWall().setWest(false);
                        current.addToColumn(1);
                        break;
                }
                currentStack++;
                step++;
                stack[currentStack] = current;  //adds the position to the stack
            }
        } while (currentStack != 0);


    }

    private Direction findNext() {
        double[] probabilites = new double[4];
        Arrays.fill(probabilites, 0.25);

        if (current.getRow() == 0 || maze[current.getRow() - 1][current.getColumn()].isVisited()) {  //north checking
            probabilites[0] = 0;
        }
        if (current.getColumn() == 0 || maze[current.getRow()][current.getColumn() - 1].isVisited()) { //west checking
            probabilites[2] = 0;
        }
        if (current.getRow() == height - 1 || maze[current.getRow() + 1][current.getColumn()].isVisited()) { //south checking
            probabilites[1] = 0;
        }
        if (current.getColumn() == width - 1 || maze[current.getRow()][current.getColumn() + 1].isVisited()) { //east checking
            probabilites[3] = 0;
        }

        double sum = 0;
        for (int i = 0; i < 4; i++) {   //finds sum of probabilities
            sum += probabilites[i];
        }

        if (sum == 0) {               //Backtracks if the probability of moving is 0
            return Direction.BACK;
        }

        for (int i = 0; i < 4; i++) {   //normalizes probabilities based on sum
            probabilites[i] /= sum;
        }
        for (int i = 1; i < 4; i++) {       //makes probabilities cumulative
            probabilites[i] = probabilites[i - 1] + probabilites[i];
        }

        double randVal = Math.random();

        if (randVal < probabilites[0]) {
            return Direction.NORTH;
        } else if (randVal < probabilites[1]) {
            return Direction.SOUTH;
        } else if (randVal < probabilites[2]) {
            return Direction.WEST;
        } else {
            return Direction.EAST;
        }
    }
}

enum Direction {
    NORTH, SOUTH, WEST, EAST, BACK
}
