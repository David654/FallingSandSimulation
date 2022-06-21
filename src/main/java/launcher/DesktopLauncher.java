package launcher;

import application.main.ApplicationConstants;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher
{
    public static void main(String[] args)
    {
        Lwjgl3ApplicationConfiguration config = new  Lwjgl3ApplicationConfiguration();

        config.setIdleFPS(ApplicationConstants.animationSpeed);
        config.useVsync(LaunchConstants.vsync);
        config.setWindowedMode(LaunchConstants.width, LaunchConstants.height);
        config.setTitle(LaunchConstants.title);
        config.setBackBufferConfig(1, 1, 1, 1, 0, 0, 8);

        new Lwjgl3Application(new Boot(), config);
    }
}