public class MainClass
{
    public static void main(String[] args)
    {
        Maze maze = new Maze(9, 9, new Coordinate(0, 0));
        maze.buildMaze();
        System.out.println(maze);
    }
}
