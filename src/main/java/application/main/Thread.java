package application.main;

import application.particles.Particle;
import application.particles.Type;

public class Thread extends java.lang.Thread
{
    private final Particle[] particles;

    public Thread(Particle... particles)
    {
        this.particles = particles;
    }

    public void run()
    {
        while(true)
        {
            for(int i = 0; i < particles.length; i++)
            {
                Particle p = particles[i];
                if(p.getType() != Type.Empty) p.update();
            }
        }
    }
}