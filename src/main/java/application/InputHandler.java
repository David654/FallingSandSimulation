package application;

import application.main.ApplicationConstants;
import application.particles.Handler;
import application.particles.Particle;
import application.particles.Type;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor
{
    private final Handler handler;
    private Type type = Type.Sand;

    public InputHandler(Handler handler)
    {
        this.handler = handler;
    }

    public boolean keyDown(int i)
    {
        switch(i)
        {
            case Input.Keys.ESCAPE -> System.exit(0);
            case Input.Keys.NUM_1 -> type = Type.Sand;
            case Input.Keys.NUM_2 -> type = Type.Water;
            case Input.Keys.NUM_3 -> type = Type.Smoke;
            case Input.Keys.NUM_4 -> type = Type.Wall;
            case Input.Keys.NUM_5 -> type = Type.Empty;
        }
        return false;
    }

    public boolean keyUp(int i) {
        return false;
    }

    public boolean keyTyped(char c) {
        return false;
    }

    public boolean touchDown(int i, int i1, int i2, int i3)
    {
        int mouseX = i / ApplicationConstants.particleSize;
        int mouseY = (Gdx.graphics.getHeight() - i1) / ApplicationConstants.particleSize;
        Particle p = handler.get(mouseX, mouseY);
        if(i3 == 0)
        {
            if(p != null && p.getType() == Type.Empty)
            {
                int index = handler.particles.indexOf(p);
                p.setType(type);
                handler.particles.set(index, p);
            }

            if(p != null && type == Type.Empty)
            {
                int index = handler.particles.indexOf(p);
                p.setType(type);
                handler.particles.set(index, p);
            }
        }
        return false;
    }

    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    public boolean mouseMoved(int i, int i1)
    {
        int mouseX = i / ApplicationConstants.particleSize;
        int mouseY = (Gdx.graphics.getHeight() - i1) / ApplicationConstants.particleSize;
        Particle p = handler.get(mouseX, mouseY);
        if(p != null && p.getType() == Type.Empty)
        {
            int index = handler.particles.indexOf(p);
            p.setType(type);
            handler.particles.set(index, p);
        }

        if(p != null && type == Type.Empty)
        {
            int index = handler.particles.indexOf(p);
            p.setType(type);
            handler.particles.set(index, p);
        }

        return false;
    }

    public boolean scrolled(float v, float v1)
    {
        return false;
    }
}