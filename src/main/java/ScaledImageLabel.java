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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Graphics;

/**
 * This is an extended version of a JLabel which draws its icon image using
 * the ImageDrawer utility.
 *
 * @author www.codejava.net
 *
 */
public class ScaledImageLabel extends JLabel {
    public ScaledImageLabel(Icon image)
    {
        super(image);
    }

    protected void paintComponent(Graphics g) {
        ImageIcon icon = (ImageIcon) getIcon();
        if (icon != null) {
            ImageDrawer.drawScaledImage(icon.getImage(), this, g);
        }
    }
}
