/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

/**
 * This utility class draws and scales an image to fit canvas of a component.
 * if the image is smaller than the canvas, it is kept as it is.
 *
 * @author www.codejava.net
 */
public class ImageDrawer
{

    public static void drawScaledImage(Image image, Component canvas, Graphics g)
    {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (canvasAspect > imgAspect) {
            y1 = canvasHeight;
            // keep image aspect ratio
            canvasHeight = (int) (canvasWidth * imgAspect);
            y1 = (y1 - canvasHeight) / 2;
        }
        else {
            x1 = canvasWidth;
            // keep image aspect ratio
            canvasWidth = (int) (canvasHeight / imgAspect);
            x1 = (x1 - canvasWidth) / 2;
        }
        x2 = canvasWidth + x1;
        y2 = canvasHeight + y1;

        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }
}
