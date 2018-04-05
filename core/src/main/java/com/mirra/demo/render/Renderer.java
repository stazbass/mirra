package com.mirra.demo.render;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mirra.demo.entities.RenderItem;

import java.util.List;

public class Renderer {
    private TextureManager textureManager;

    public Renderer(TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    public void render(SpriteBatch batchSupplier, List<RenderItem> spriteSupplier) {
        SpriteBatch spriteBatch = batchSupplier;
        spriteBatch.begin();
        spriteBatch.enableBlending();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        spriteSupplier.stream().forEach(renderAtom -> {
            Texture texture = textureManager.loadTexture(renderAtom.getTexture());
            spriteBatch.draw(texture, renderAtom.getPosition().x - texture.getWidth() / 2.0f * renderAtom.getScale().x, renderAtom.getPosition().y - texture.getHeight() / 2.0f * renderAtom.getScale().y,
                    texture.getWidth() * renderAtom.getScale().x, texture.getHeight() * renderAtom.getScale().y);
        });
        spriteBatch.end();
    }

    public void render(SpriteBatch batchSupplier, RenderItem renderItem) {
        SpriteBatch spriteBatch = batchSupplier;
        spriteBatch.begin();
        spriteBatch.enableBlending();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
            Texture texture = textureManager.loadTexture(renderItem.getTexture());
            spriteBatch.draw(texture, renderItem.getPosition().x - texture.getWidth() / 2.0f * renderItem.getScale().x, renderItem.getPosition().y - texture.getHeight() / 2.0f * renderItem.getScale().y,
                    texture.getWidth() * renderItem.getScale().x, texture.getHeight() * renderItem.getScale().y);
        spriteBatch.end();
    }
}
