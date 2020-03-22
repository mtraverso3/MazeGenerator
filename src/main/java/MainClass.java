import io.airlift.airline.Command;
import io.airlift.airline.HelpOption;
import io.airlift.airline.Option;
import io.airlift.airline.SingleCommand;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h1>Maze Generator</h1>
 * This maze generation program generates completely unique/random mazes.
 * It uses a depth-first search algorithm to build the maze.
 * The program can display and output the maze as an image.
 *
 * @author Marcos Traverso
 * @since 2020-03-22
 */
public class MainClass
{
    public static void main(String[] args)
            throws IOException
    {
        MazeCommand command = SingleCommand.singleCommand(MazeCommand.class).parse(args);

        if (command.helpOption.showHelpIfRequested()) {
            return;
        }

        long seed = ThreadLocalRandom.current().nextLong();
        Random random;
        if (command.seed != null) {         //Sets seed if provided
            seed = command.seed;
        }

        Maze maze = new Maze(command.width, command.height, new Coordinate(0, 0), new Random(seed));

        BufferedImage img;      //generates an image based on the type picked
        switch (command.type) {
            case "BW":
                img = new ImageGenerator(maze, command.size).mazeBW();
                break;
            case "RgbDepth":
                img = new ImageGenerator(maze, command.size).mazeRgbDepth();
                break;
            case "RgbStep":
                img = new ImageGenerator(maze, command.size).mazeRgbStep();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        if (command.file != null) {
            ImageIO.write(img, "PNG", new File(command.file));
        }

        ShowImage(img);

        System.out.println("Seed: " + seed);
        System.out.println("Width: " + command.width);
        System.out.println("Height: " + command.height);
    }

    /**
     * This method displays a scalable image with JFrame.
     *
     * @param img The image to be displayed
     */
    private static void ShowImage(BufferedImage img)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ScaledImageLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }

    //Command line commands
    @Command(name = "mazegen", description = "Generates a maze")
    public static class MazeCommand
    {
        @Inject
        public HelpOption helpOption;

        @Option(name = {"-w", "--width"}, description = "Sets the width.")
        public int width = 15;

        @Option(name = {"--height"}, description = "Sets the height.")
        public int height = 15;

        @Option(name = {"-f", "--file"}, description = "Outputs the maze to a PNG file.")
        public String file;

        @Option(name = {"-s", "--seed"}, description = "Random number generator seed")
        public Long seed;

        @Option(name = {"-t", "--type"}, description = "Sets the type of image generated", allowedValues = {"BW", "RgbDepth", "RgbStep"})
        public String type = "BW";

        @Option(name = {"-cw, ", "--cellwidth"}, description = "Sets the pixel width of each cell in the image.", hidden = true)
        public int size = 5;
    }
}
