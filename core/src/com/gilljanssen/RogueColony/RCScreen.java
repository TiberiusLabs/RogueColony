package com.gilljanssen.RogueColony;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gilljanssen.RogueColony.Factories.EntityFactory;
import com.gilljanssen.RogueColony.Systems.MovementSystem;
import com.gilljanssen.RogueColony.Systems.PlayerAnimationSystem;
import com.gilljanssen.RogueColony.Systems.PlayerInputSystem;
import com.gilljanssen.RogueColony.Systems.SpriteRenderSystem;

public class RCScreen implements Screen {

    private OrthographicCamera camera;
    private World world;

    private SpriteRenderSystem spriteRenderSystem;
    private PlayerInputSystem playerInputSystem;
    private MovementSystem movementSystem;
    private PlayerAnimationSystem playerAnimationSystem;

    public RCScreen(Game game) {

        camera = new OrthographicCamera();
        world = new World();
        spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);
        playerInputSystem = world.setSystem(new PlayerInputSystem(camera), true);
        playerAnimationSystem = world.setSystem(new PlayerAnimationSystem(), true);
        movementSystem = world.setSystem(new MovementSystem(), true);

        world.initialize();

        EntityFactory.createPlayer(world, 100, 0).addToWorld();
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
