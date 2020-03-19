import java.awt.Color;

public class ColorRGB

{

    private final int red;
    private final int green;
    private final int blue;

    ColorRGB(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }

    public Color getColor()
    {
        return new Color(red, green, blue);
    }

    public ColorHSV getHSV()
    {
        double h = -1;
        double s;
        double v;
        double r = this.red / 255.0;
        double g = this.green / 255.0;
        double b = this.blue / 255.0;
        double cMax = Math.max(r, Math.max(g, b));
        double cMin = Math.min(r, Math.min(g, b));
        double diff = cMax - cMin;

        if (cMax == cMin) {
            h = 0;
        }
        else if (cMax == r) {
            h = (60 * ((g - b) / diff) + 360) % 360;
        }
        else if (cMax == g) {
            h = (60 * ((b - r) / diff) + 120) % 360;
        }
        else if (cMax == b) {
            h = (60 * ((r - g) / diff) + 240) % 360;
        }

        if (cMax == 0) {
            s = 0;
        }
        else {
            s = (diff / cMax);
        }
        v = cMax;
        return new ColorHSV((int) h, s, v);
    }
}
