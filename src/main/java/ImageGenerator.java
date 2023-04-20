import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageGenerator
{
    private final Maze maze;
    private final int size;

    public ImageGenerator(Maze maze, int size)
    {
        this.maze = maze;
        this.size = size;
    }

    /**
     * @return A black and white BufferedImage of the maze
     */
    public BufferedImage mazeBW()
    {
        int cellSize = size * 2;
        BufferedImage img = new BufferedImage(maze.getWidth() * cellSize+1, maze.getHeight() * cellSize+1, BufferedImage.TYPE_BYTE_BINARY);

        //color the background white
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.dispose();


        drawMazeGrid(img);
        return img;
    }

    /**
     * This method will draw the black maze lines over the given image.
     *
     * @param img Image to use as a base
     * @return A BufferedImage with maze lines drawn on it
     */
    private BufferedImage drawMazeGrid(BufferedImage img)
    {
        int cellSize = size * 2;
        Graphics2D graphics = img.createGraphics();

        graphics.setPaint(Color.BLACK);

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int column = 0; column < maze.getWidth(); column++) {
                Coordinate current = new Coordinate(row, column);

                if (maze.getCell(current).getWall().getNorth()) {
                    int x1 = column * cellSize;
                    int y1 = row * cellSize;
                    int x2 = (column + 1) * cellSize;
                    int y2 = y1;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getSouth()) {
                    int x1 = column * cellSize;
                    int y1 = (row + 1) * cellSize;
                    int x2 = (column + 1) * cellSize;
                    int y2 = y1;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getWest()) {
                    int x1 = column * cellSize;
                    int y1 = row * cellSize;
                    int x2 = x1;
                    int y2 = (row + 1) * cellSize;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getEast()) {
                    int x1 = (column + 1) * cellSize;
                    int y1 = row * cellSize;
                    int x2 = x1;
                    int y2 = (row + 1) * cellSize;
                    graphics.drawLine(x1, y1, x2, y2);
                }
            }
        }
        return img;
    }

    /**
     * This method generates an image with hue based on the distance from the start.
     */
    public BufferedImage mazeRgbDepth()
    {
        return renderMaze((maze, cell) -> Color.getHSBColor((float) cell.getDepth() / maze.getMaxDepth(), 1, 1));
    }

    /**
     * This method generates an image with hue based on the order visited by the generator(step).
     */
    public BufferedImage mazeRgbStep()
    {
        return renderMaze((maze, cell) -> Color.getHSBColor((float) cell.getStep() / maze.getMaxStep(), 1, 1));
    }

    private BufferedImage renderMaze(ColorCalculator colorCalculator)
    {
        int cellSize = size * 2;
        BufferedImage img = new BufferedImage(maze.getWidth() * cellSize + 1, maze.getHeight() * cellSize + 1, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = img.createGraphics();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int column = 0; column < maze.getWidth(); column++) {

                g.setPaint(colorCalculator.computeColor(maze, maze.getCell(new Coordinate(row, column))));
                g.fillRect(column * (size * 2) + 1, row * (size * 2) + 1, size * 2, size * 2);
            }
        }

        //draws the maze lines over the image using drawMazeGrid()
        return drawMazeGrid(img);
    }

    private interface ColorCalculator
    {
        Color computeColor(Maze maze, Cell cell);
    }
}
