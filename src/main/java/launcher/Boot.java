package launcher;

import application.main.Scene;
import com.badlogic.gdx.Game;

public class Boot extends Game
{
    public void create()
    {
        setScreen(new Scene());
    }
}