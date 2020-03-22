/**
 * This class represents a Hue-Saturation-Value color.
 * It includes a method to convert to a RGB color.
 *
 * @author Marcos Traverso
 */
public class ColorHSV
{
    private final int hue;
    private final double saturation;
    private final double value;

    ColorHSV(int hue, double saturation, double value)
    {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public int getHue()
    {
        return hue;
    }

    public double getSaturation()
    {
        return saturation;
    }

    public double getValue()
    {
        return value;
    }

    public ColorRGB getRGB()
    {
        double v = this.value;
        double r = 0;
        double g = 0;
        double b = 0;

        double chroma = this.saturation * v;
        double hueRange = this.hue / 60.0;
        double x = chroma * (1 - Math.abs((hueRange % 2) - 1));

        switch ((int) hueRange) {
            case 0:
                r = chroma;
                g = x;
                b = 0;
                break;
            case 1:
                r = x;
                g = chroma;
                b = 0;
                break;
            case 2:
                r = 0;
                g = chroma;
                b = x;
                break;
            case 3:
                r = 0;
                g = x;
                b = chroma;
                break;
            case 4:
                r = x;
                g = 0;
                b = chroma;
                break;
            case 5:
                r = chroma;
                g = 0;
                b = x;
                break;
        }

        double m = v - chroma;

        r = Math.round(255 * (r + m));
        g = Math.round(255 * (g + m));
        b = Math.round(255 * (b + m));

        return new ColorRGB((int) r, (int) g, (int) b);
    }
}
