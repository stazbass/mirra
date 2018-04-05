package com.mirra.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

import com.mirra.demo.dagger.DaggerMainComponent;
import com.mirra.demo.dagger.MainComponent;
import com.mirra.demo.render.FramesPerSecond;
import com.mirra.demo.render.Renderable;
import com.mirra.demo.render.Renderer;
import com.mirra.demo.render.TextureManager;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class MirraGame extends ApplicationAdapter {
    public static final int VIEWPORT_WIDTH = 1200;
    public static final int VIEWPORT_HEIGHT = 1028;
    SpriteBatch mainBatch;
    List<Renderable> renderableContent;
    FramesPerSecond fps;
    MainComponent mainComponent;
    OrthographicCamera camera;
    Renderer renderer;
    TextureManager textureManager;

    @Override
    public void create() {
        mainBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        renderableContent = new LinkedList<>();

        mainComponent = DaggerMainComponent.builder().build();
        textureManager = mainComponent.provideTextureManager();
        renderer = mainComponent.provideRenderer();

        renderableContent.add(mainComponent.provideClock());

        fps = mainComponent.provideFramesPerSecond();
    }

    @Override
    public void render() {
        fps.frameTimeStart();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mainBatch.setProjectionMatrix(camera.combined);

        Long itemsRendered = renderableContent.stream()
                .flatMap(items -> items.render().stream())
                .map(renderItems -> {
                    renderer.render(mainBatch, renderItems);
                    return renderItems;
                })
                .count();


        fps.frameTimeEnd(itemsRendered);
        fps.draw(mainBatch);
    }

    @Override
    public void resize(int width, int height) {
        camera.update(true);
    }

    @Override
    public void dispose() {
        mainBatch.dispose();
    }
}
