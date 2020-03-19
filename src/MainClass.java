import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass
{
    public static void main(String[] args)
            throws IOException
    {
        Maze maze = new Maze(20, 20, new Coordinate(0, 0));
        maze.buildMaze();
        System.out.println(maze);

        BufferedImage img = new ImageGenerator(maze).mazeBW(7);

        ImageIO.write(img, "PNG", new File("maze.png"));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }
}
