package com.gilljanssen.RogueColony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class MapView implements Screen {

    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = player.getX();
        camera.position.y = player.getY();
        camera.update();

        renderer.setView(camera);
        renderer.render();

        renderer.getSpriteBatch().begin();
        player.draw(renderer.getSpriteBatch());
        renderer.getSpriteBatch().end();
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("pond.tmx");
        renderer = new IsometricTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Player(new Sprite(new TextureRegion(new Texture("neworc.png"), 32, 32, 64, 64)),
                (TiledMapTileLayer)map.getLayers().get(0));
        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), 20 * player.getCollisionLayer().getTileHeight());

        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    @Override
    public void pause() {
    }
}
