package application.particles;

import application.main.Thread;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

public class Handler
{
    public final ArrayList<Particle> particles;

    public Handler()
    {
        particles = new ArrayList<>();
    }

    public Particle get(int x, int y)
    {
        Particle particle = null;
        for(Particle p : particles)
        {
            if(p.getPos().equals(new Vector2(x, y)))
            {
                particle = p;
            }
        }
        return particle;
    }

    public void setThreads(int n)
    {
        int step = particles.size() / n;
        int start = 0;
        int end = step;

        for(int i = 0; i < n; i++)
        {
            Particle[] list = new Particle[end - start];
            for(int j = start; j < end; j++)
            {
                list[j - start] = particles.get(j);
            }

            Thread thread = new Thread(list);
            thread.start();
            start = end;
            end += step;
        }
    }

    public void add(Particle particle)
    {
        particles.add(particle);
    }

    public void update()
    {
        for(int i = 0; i < particles.size(); i++)
        {
            Particle particle = particles.get(i);
            particle.update();
        }
    }

    public void render(ShapeRenderer renderer)
    {
        for(int i = 0; i < particles.size(); i++)
        {
            Particle particle = particles.get(i);
            particle.render(renderer);
        }
    }
}