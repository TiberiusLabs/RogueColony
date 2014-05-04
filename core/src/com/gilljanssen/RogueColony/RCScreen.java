package com.gilljanssen.RogueColony;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.gilljanssen.RogueColony.Components.Position;
import com.gilljanssen.RogueColony.Components.SpriteRenderSystem;

public class RCScreen implements Screen {

    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Game game;
    private World world;
    private Player player;

    private SpriteRenderSystem spriteRenderSystem;

    public RCScreen(Game game) {
        this.game = game;

        camera = new OrthographicCamera();
        world = new World();
        spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);

        world.initialize();

        EntityFactory.createPlayer(world, 150, 150).addToWorld();

        /*
        map = new TmxMapLoader().load("pond.tmx");
        renderer = new IsometricTiledMapRenderer(map);
        player = new Player(new Sprite(new TextureRegion(new Texture("neworc.png"), 32, 32, 64, 64)),
                (TiledMapTileLayer)map.getLayers().get(0));
        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), 20 * player.getCollisionLayer().getTileHeight());

        Gdx.input.setInputProcessor(player);
        */
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        world.setDelta(delta);
        world.process();
        spriteRenderSystem.process();

        /*
        camera.position.x = player.getX();
        camera.position.y = player.getY();
        camera.update();

        renderer.setView(camera);
        renderer.render();

        renderer.getSpriteBatch().begin();
        player.draw(renderer.getSpriteBatch());
        renderer.getSpriteBatch().end();
        */
    }

    @Override
    public void show() {
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
        /*
        map.dispose();
        renderer.dispose();
        */
    }

    @Override
    public void pause() {
    }
}
