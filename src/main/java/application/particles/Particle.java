package application.particles;

import application.main.ApplicationConstants;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Particle
{
    private final Vector2 pos;
    private final float size;
    private final Handler handler;
    private Type type;

    public Particle(Vector2 pos, float size, Type type, Handler handler)
    {
        this.pos = pos;
        this.size = size;
        this.type = type;
        this.handler = handler;
    }

    public Vector2 getPos()
    {
        return pos;
    }

    public float getSize()
    {
        return size;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    protected boolean isNeighbourSand(int x, int y)
    {
        return x >= 0 && x < ApplicationConstants.gridWidth && y >= 0 && y < ApplicationConstants.gridHeight && handler.get(x, y).getType() != Type.Sand && handler.get(x, y).getType() != Type.Wall;
    }

    protected boolean isNeighbourWater(int x, int y)
    {
        return x >= 0 && x < ApplicationConstants.gridWidth && y >= 0 && y < ApplicationConstants.gridHeight && handler.get(x, y).getType() == Type.Empty;
    }

    private void changeSand(float x, float y)
    {
        type = Type.Empty;

        if(handler.get((int) x, (int) y).getType() == Type.Water) type = Type.Water;
        handler.get((int) x, (int) y).setType(Type.Sand);
    }

    public void update()
    {
        if(type == Type.Sand)
        {
            boolean b = isNeighbourSand((int) pos.x, (int) (pos.y - 1));
            boolean br = isNeighbourSand((int) pos.x + 1, (int) (pos.y - 1));
            boolean bl = isNeighbourSand((int) pos.x - 1, (int) (pos.y - 1));

            if(b) changeSand(pos.x, pos.y - 1);
            else if(br && bl && handler.get((int) pos.x, (int) (pos.y - 1)).getType() != Type.Wall)
            {
                Random rnd = new Random();
                int n = rnd.nextInt(2);
                int x = (int) (pos.x + (n == 0 ? 1 : -1));
                int y = (int) (pos.y + (n == 0 ? -1 : 1));
                changeSand(x, y);
            }
            else if(br && handler.get((int) pos.x, (int) (pos.y - 1)).getType() != Type.Wall) changeSand(pos.x + 1, pos.y - 1);
            else if(bl && handler.get((int) pos.x, (int) (pos.y - 1)).getType() != Type.Wall) changeSand(pos.x - 1, pos.y - 1);
        }

        if(type == Type.Water)
        {
            boolean b = isNeighbourWater((int) pos.x, (int) (pos.y - 1));
            boolean r = isNeighbourWater((int) pos.x + 1, (int) (pos.y));
            boolean l = isNeighbourWater((int) pos.x - 1, (int) (pos.y));

            if(b)
            {
                type = Type.Empty;
                handler.get((int) (pos.x), (int) (pos.y - 1)).setType(Type.Water);
            }
            else if(r && l)
            {
                Random rnd = new Random();
                int n = rnd.nextInt(2);
                int x = (int) (pos.x + (n == 0 ? 1 : -1));
                int y = (int) pos.y;

                type = Type.Empty;
                handler.get(x, y).setType(Type.Water);
            }
            else if(r)
            {
                type = Type.Empty;
                handler.get((int) (pos.x + 1), (int) (pos.y)).setType(Type.Water);
            }
            else if(l)
            {
                type = Type.Empty;
                handler.get((int) (pos.x - 1), (int) (pos.y)).setType(Type.Water);
            }
        }

        if(type == Type.Smoke)
        {
            boolean t = isNeighbourWater((int) pos.x, (int) (pos.y + 1));
            boolean r = isNeighbourWater((int) pos.x + 1, (int) (pos.y));
            boolean l = isNeighbourWater((int) pos.x - 1, (int) (pos.y));

            if(t)
            {
                type = Type.Empty;
                handler.get((int) (pos.x), (int) (pos.y + 1)).setType(Type.Smoke);
            }
            else if(r && l)
            {
                Random rnd = new Random();
                int n = rnd.nextInt(2);
                int x = (int) (pos.x + (n == 0 ? 1 : -1));
                int y = (int) pos.y;

                type = Type.Empty;
                handler.get(x, y).setType(Type.Smoke);
            }
            else if(r)
            {
                type = Type.Empty;
                handler.get((int) (pos.x + 1), (int) (pos.y)).setType(Type.Smoke);
            }
            else if(l)
            {
                type = Type.Empty;
                handler.get((int) (pos.x - 1), (int) (pos.y)).setType(Type.Smoke);
            }
        }
    }

    public void render(ShapeRenderer renderer)
    {
        switch(type)
        {
            case Empty -> renderer.setColor(ApplicationConstants.emptyColor);
            case Sand -> renderer.setColor(ApplicationConstants.sandColor);
            case Water -> renderer.setColor(ApplicationConstants.waterColor);
            case Smoke -> renderer.setColor(ApplicationConstants.smokeColor);
            case Wall -> renderer.setColor(ApplicationConstants.wallColor);
        }
        renderer.rect(pos.x * size, pos.y * size, size, size);
    }
}