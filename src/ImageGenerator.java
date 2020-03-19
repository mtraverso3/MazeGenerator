import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageGenerator
{
    private final Maze maze;

    public ImageGenerator(Maze maze)
    {
        this.maze = maze;
    }

    public BufferedImage mazeBW(int size)
    {
        int cellSize = size * 2 + 1;
        BufferedImage img = new BufferedImage(maze.getWidth() * (cellSize), maze.getHeight() * (cellSize), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = img.createGraphics();

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        graphics.setRenderingHints(hints);

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

//                    System.out.println(String.format("(%s, %s) -> (%s, %s)", x1, y1, x2, y2));
                    graphics.drawLine(x1, y1, x2, y2);
//                    graphics.fillRect(column * (cellSize - 1), row * (cellSize - 1), cellSize, 1);
                }
                if (maze.getCell(current).getWall().getSouth()) {
                    graphics.fillRect(column * (cellSize - 1), row * (cellSize - 1) + (cellSize - 1), cellSize, 1);
                }
                if (maze.getCell(current).getWall().getWest()) {
                    graphics.fillRect(column * (cellSize - 1), row * (cellSize - 1), 1, cellSize);
                }
                if (maze.getCell(current).getWall().getEast()) {
                    graphics.fillRect(column * (cellSize - 1) + (cellSize - 1), row * (cellSize - 1), 1, cellSize);
                }
            }
        }
        return img;
    }
}
