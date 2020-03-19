import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ImageGenerator
{
    private final Maze maze;
    private final int size;

    public ImageGenerator(Maze maze, int size)
    {
        this.maze = maze;
        this.size = size;
    }

    public BufferedImage mazeBW()
    {
        int cellSize = size * 2 + 1;
        BufferedImage img = new BufferedImage(maze.getWidth() * (cellSize - 1) + 1, maze.getHeight() * (cellSize - 1) + 1, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = img.createGraphics();

//        RenderingHints hints = new RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//        graphics.setRenderingHints(hints);

        graphics.setPaint(Color.WHITE);
        graphics.fillRect(0, 0, img.getWidth(), img.getHeight());

        graphics.setPaint(Color.BLACK);

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int column = 0; column < maze.getWidth(); column++) {
                Coordinate current = new Coordinate(row, column);

                if (maze.getCell(current).getWall().getNorth()) {
                    int x1 = column * (cellSize - 1);
                    int y1 = row * (cellSize - 1);
                    int x2 = (column + 1) * (cellSize - 1);
                    int y2 = y1;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getSouth()) {
                    int x1 = column * (cellSize - 1);
                    int y1 = (row + 1) * (cellSize - 1);
                    int x2 = (column + 1) * (cellSize - 1);
                    int y2 = y1;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getWest()) {
                    int x1 = column * (cellSize - 1);
                    int y1 = row * (cellSize - 1);
                    int x2 = x1;
                    int y2 = (row + 1) * (cellSize - 1);
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getEast()) {
                    int x1 = (column + 1) * (cellSize - 1);
                    int y1 = row * (cellSize - 1);
                    int x2 = x1;
                    int y2 = (row + 1) * (cellSize - 1);
                    graphics.drawLine(x1, y1, x2, y2);
                }
            }
        }
        return img;
    }

    private BufferedImage mazeBW(BufferedImage img)
    {
        int cellSize = size * 2 + 1;
        Graphics2D graphics = img.createGraphics();

//        RenderingHints hints = new RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//        graphics.setRenderingHints(hints);

        graphics.setPaint(Color.BLACK);

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int column = 0; column < maze.getWidth(); column++) {
                Coordinate current = new Coordinate(row, column);

                if (maze.getCell(current).getWall().getNorth()) {
                    int x1 = column * (cellSize - 1);
                    int y1 = row * (cellSize - 1);
                    int x2 = (column + 1) * (cellSize - 1);
                    int y2 = y1;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getSouth()) {
                    int x1 = column * (cellSize - 1);
                    int y1 = (row + 1) * (cellSize - 1);
                    int x2 = (column + 1) * (cellSize - 1);
                    int y2 = y1;
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getWest()) {
                    int x1 = column * (cellSize - 1);
                    int y1 = row * (cellSize - 1);
                    int x2 = x1;
                    int y2 = (row + 1) * (cellSize - 1);
                    graphics.drawLine(x1, y1, x2, y2);
                }
                if (maze.getCell(current).getWall().getEast()) {
                    int x1 = (column + 1) * (cellSize - 1);
                    int y1 = row * (cellSize - 1);
                    int x2 = x1;
                    int y2 = (row + 1) * (cellSize - 1);
                    graphics.drawLine(x1, y1, x2, y2);
                }
            }
        }
        return img;
    }

    public BufferedImage mazeRgbDepth()
    {
        int cellSize = size * 2 + 1;
        BufferedImage img = new BufferedImage(maze.getWidth() * (cellSize - 1) + 1, maze.getHeight() * (cellSize - 1) + 1, BufferedImage.TYPE_INT_RGB);
        //gets a maze image from mazeBW() and converts it to RGB

        Graphics2D g = img.createGraphics();
        double maxDepth = maze.getMaxDepth();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int column = 0; column < maze.getWidth(); column++) {
                ColorRGB color = new ColorHSV((int) Math.round(maze.getCell(new Coordinate(row, column)).getDepth() / maxDepth * 300), 1, 1).getRGB();
                g.setPaint(color);
                g.fillRect(column * (size * 2) + 1, row * (size * 2) + 1, size * 2, size * 2);
            }
        }

        img = mazeBW(img);
        return img;
    }
}
