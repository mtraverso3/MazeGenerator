import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.AllowedEnumValues;
import com.github.rvesse.airline.annotations.restrictions.ranges.Positive;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.*;
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
public class MainClass {
    public static void main(String[] args) {
        MazeCommand command = SingleCommand.singleCommand(MazeCommand.class).parse(args);

        if (command.helpOption.showHelpIfRequested()) {
            return;
        }

        long seed = ThreadLocalRandom.current().nextLong();
        if (command.seed != null) {         //Sets seed if provided
            seed = command.seed;
        }

        Maze maze = new Maze(command.width, command.height, new Coordinate(0, 0), new Random(seed));

        BufferedImage img = switch (command.type) {
            case BW -> new ImageGenerator(maze, command.size).mazeBW();
            case RGB_DEPTH -> new ImageGenerator(maze, command.size).mazeRgbDepth();
            case RGB_STEP -> new ImageGenerator(maze, command.size).mazeRgbStep();
        };


        //Save image to file asynchronously
        if (command.file != null) {
            Thread.startVirtualThread(
                    () -> {
                        try {
                            ImageIO.write(img, "PNG", new File(command.file));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
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
    private static void ShowImage(BufferedImage img) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ScaledImageLabel(new ImageIcon(img)));
        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    //Command line commands
    @Command(name = "mazegen", description = "Generates a maze")
    public static class MazeCommand {
        @Inject
        public HelpOption helpOption;

        @Option(name = {"-w", "--width"}, description = "Sets the width.")
        @Positive(includesZero = false)
        public int width = 15;

        @Option(name = {"--height"}, description = "Sets the height.")
        @Positive(includesZero = false)
        public int height = 15;

        @Option(name = {"-o", "--output"}, description = "Outputs the maze to a PNG file.")
        @com.github.rvesse.airline.annotations.restrictions.File
        public String file;

        @Option(name = {"-s", "--seed"}, description = "Random number generator seed. NOTE: Same width and height must be used to reproduce a maze.")
        public Long seed;

        @Option(name = {"-t", "--type"}, description = "Sets the type of image generated")
        @AllowedEnumValues(ImageType.class)
        public ImageType type = ImageType.BW;

        @Option(name = {"-cw, ", "--cellwidth"}, description = "Sets the pixel width of each cell in the image.")
        @Positive(includesZero = false)
        public int size = 5;
    }

    //Image types
    public enum ImageType {
        BW, RGB_DEPTH, RGB_STEP
    }
}
