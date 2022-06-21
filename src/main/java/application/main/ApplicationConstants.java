package application.main;

import com.badlogic.gdx.graphics.Color;
import launcher.LaunchConstants;

public class ApplicationConstants
{
    public static final int gridWidth = 128;
    public static final int gridHeight = 64;
    public static final int particleSize = LaunchConstants.width / gridWidth;
    public static final Color emptyColor = Color.BLACK;
    public static final Color sandColor = Color.YELLOW;
    public static final Color waterColor = Color.ROYAL;
    public static final Color smokeColor = Color.LIGHT_GRAY;
    public static final Color wallColor = Color.GRAY;
    public static final int animationSpeed = 60;
}