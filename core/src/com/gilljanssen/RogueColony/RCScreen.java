package com.gilljanssen.RogueColony;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.gilljanssen.RogueColony.Factories.EntityFactory;
import com.gilljanssen.RogueColony.Systems.MovementSystem;
import com.gilljanssen.RogueColony.Systems.PlayerAnimationSystem;
import com.gilljanssen.RogueColony.Systems.PlayerInputSystem;
import com.gilljanssen.RogueColony.Systems.SpriteRenderSystem;

public class RCScreen implements Screen {

    private OrthographicCamera camera;
    private World world;
    private TiledMap map;

    private SpriteRenderSystem spriteRenderSystem;
    private PlayerInputSystem playerInputSystem;
    private MovementSystem movementSystem;
    private PlayerAnimationSystem playerAnimationSystem;

    public RCScreen(Game game) {

        camera = new OrthographicCamera();
        world = new World();
        map = new TmxMapLoader().load("map.tmx");
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer)(map.getLayers().get(0));
        //TiledMapTile tile = collisionLayer.getCell(0, 0).getTile();
        //System.out.println(tile.getProperties().toString());
        //for (int i = 0; i < 10; i++)
            //for (int j = 0; j < 10; j++)
                //collisionLayer.getCell(1 + i, 1 + j).setTile(tile);
        spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera, map), true);
        playerInputSystem = world.setSystem(new PlayerInputSystem(camera), true);
        playerAnimationSystem = world.setSystem(new PlayerAnimationSystem(), true);
        movementSystem = world.setSystem(new MovementSystem((TiledMapTileLayer)map.getLayers().get(0)), true);

        world.initialize();

        EntityFactory.createPlayer(world, 1000, 0).addToWorld();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.setDelta(delta);
        world.process();

        playerInputSystem.process();
        movementSystem.process();
        playerAnimationSystem.process();
        spriteRenderSystem.process();

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
    }

    @Override
    public void pause() {
    }
}
