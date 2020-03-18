public class MainClass {
    public static void main(String[] args) {
        Maze maze = new Maze(6, 6, new Coordinate(0, 0));
        maze.buildMaze();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (maze.getMaze()[i][j].isVisited()) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }

    }
}