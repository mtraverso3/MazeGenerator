import java.util.Arrays;

public class Maze
{
    private final Cell[][] maze;
    private final Coordinate start;

    private final int width;
    private final int height;

    public Maze(int width, int height, Coordinate start)
    {
        this.start = start;
        this.width = width;
        this.height = height;
        this.maze = new Cell[height][width];

        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                this.maze[row][column] = new Cell(new Wall(), false);
            }
        }
    }

    public void buildMaze()
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
//            System.out.println(this);
            Direction next = findNext(current);
//            System.out.println(next);
//            System.out.println();

            if (next == Direction.BACK) {
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

                switch (next) {
                    case NORTH:                                 //removes connecting walls, moves current
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

    private Direction findNext(Coordinate current)
    {
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
        return maze[coordinate.getRow()][coordinate.getColumn()];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

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

