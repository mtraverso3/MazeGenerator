import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass
{
    public static void main(String[] args)
            throws IOException
    {
        int width = 20;
        int height = 20;

        if (args.length == 2) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }
        Maze maze = new Maze(width, height, new Coordinate(0, 0));
        maze.buildMaze();

        BufferedImage img = new ImageGenerator(maze, 7).mazeRgbDepth();

        ImageIO.write(img, "PNG", new File("maze.png"));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ScaledImageLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }
}
