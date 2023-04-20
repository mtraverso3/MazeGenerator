import java.util.Arrays;
import java.util.Random;

public class Maze
{
    private final Random random;
    private final Cell[][] maze;
    private final Coordinate start;

    private final int width;
    private final int height;

    public Maze(int width, int height, Coordinate start, Random random)
    {
        this.start = start;
        this.width = width;
        this.height = height;
        this.random = random;
        this.maze = new Cell[height][width];

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                this.maze[row][column] = new Cell(new Wall(), false);
            }
        }
        this.buildMaze();
    }

    /**
     * This method builds the maze.
     * It uses a depth-first-search algorithm to build it.
     */
    private void buildMaze()
    {
        Coordinate[] stack = new Coordinate[width * height];
        stack[0] = start;
        int currentStack = 0;
        int step = 0;

        Coordinate current = start;

        Cell cell = getCell(current);
        cell.setVisited(true);
        cell.setDepth(currentStack);
        cell.setStep(step);

        do {
            Direction next = findNext(current);

            if (next == Direction.BACK) {   //Backtracks and sets the stack variables
                stack[currentStack] = null;
                currentStack--;
                current = stack[currentStack];

                cell = getCell(current);
            }
            else {
                current = current.move(next);
                currentStack++;
                step++;
                stack[currentStack] = current;  //adds the position to the stack

                switch (next) {         //removes the connecting walls
                    case NORTH:
                        cell.getWall().setNorth(false);
                        getCell(current).getWall().setSouth(false);
                        break;
                    case SOUTH:
                        cell.getWall().setSouth(false);
                        getCell(current).getWall().setNorth(false);
                        break;
                    case WEST:
                        cell.getWall().setWest(false);
                        getCell(current).getWall().setEast(false);
                        break;
                    case EAST:
                        cell.getWall().setEast(false);
                        getCell(current).getWall().setWest(false);
                        break;
                }

                cell = getCell(current);
                cell.setVisited(true);
                cell.setDepth(currentStack);
                cell.setStep(step);
            }
        }
        while (currentStack != 0);
    }

    /**
     * This method checks the surrounding cells and checks if they have been visited.
     * A random direction is returned out of the unvisited directions.
     *
     * @param current The base coordinate to use for checking
     */
    private Direction findNext(Coordinate current)
    {
        double[] probabilites = new double[4];
        Arrays.fill(probabilites, 0.25);


        if (current.row() == 0 || maze[current.row() - 1][current.column()].isVisited()) {  //north checking
            probabilites[0] = 0;
        }
        if (current.column() == 0 || maze[current.row()][current.column() - 1].isVisited()) { //west checking
            probabilites[2] = 0;
        }
        if (current.row() == height - 1 || maze[current.row() + 1][current.column()].isVisited()) { //south checking
            probabilites[1] = 0;
        }
        if (current.column() == width - 1 || maze[current.row()][current.column() + 1].isVisited()) { //east checking
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

        double randVal = random.nextDouble();
        if (randVal < probabilites[0]) {
            return Direction.NORTH;
        }
        else if (randVal < probabilites[1]) {
            return Direction.SOUTH;
        }
        else if (randVal < probabilites[2]) {
            return Direction.WEST;
        }
        else {
            return Direction.EAST;
        }
    }

    public Cell[][] getMaze()
    {
        return maze;
    }

    public Cell getCell(Coordinate coordinate)
    {
        return maze[coordinate.row()][coordinate.column()];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    /**
     * Gets the maximum distance from the start in the maze
     */
    public int getMaxDepth()
    {
        int max = 0;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                max = Math.max(maze[row][column].getDepth(), max);
            }
        }
        return max;
    }

    /**
     * Gets the highest step value in the maze.
     */
    public int getMaxStep()
    {
        int max = 0;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                max = Math.max(maze[row][column].getStep(), max);
            }
        }
        return max;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                builder.append(String.format("%3d", maze[row][column].getStep()));
                builder.append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}

