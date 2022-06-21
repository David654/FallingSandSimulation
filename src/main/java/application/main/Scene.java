package application.main;

import application.InputHandler;
import application.particles.Handler;
import application.particles.Particle;
import application.particles.Type;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import launcher.LaunchConstants;
import org.lwjgl.opengl.GL20;

public class Scene extends ScreenAdapter
{
    private final ShapeRenderer renderer;
    public final Handler handler;
    private final InputHandler inputHandler;

    public Scene()
    {
        renderer = new ShapeRenderer();
        handler = new Handler();

        for(int x = 0; x < ApplicationConstants.gridWidth; x++)
        {
            for(int y = 0; y < ApplicationConstants.gridHeight; y++)
            {
                handler.add(new Particle(new Vector2(x, y), ApplicationConstants.particleSize, Type.Empty, handler));
            }
        }

        handler.setThreads(3);

        inputHandler = new InputHandler(handler);
        Gdx.input.setInputProcessor(inputHandler);

        System.out.println(handler.particles.size());
    }

    private void update()
    {
        Gdx.graphics.setTitle(LaunchConstants.title + " | FPS: " + Gdx.graphics.getFramesPerSecond());
        //handler.update();
    }

    public void render(float delta)
    {
        update();
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        handler.render(renderer);

        renderer.end();
    }
}