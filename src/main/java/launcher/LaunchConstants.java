package launcher;

import java.awt.*;

public class LaunchConstants
{
    private static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int width = 1024;
    public static final int height = 512;

    public static final int tickRate = 60;
    public static final boolean vsync = false;

    public static final String title = "Particle-Based Cellular Automaton";
}