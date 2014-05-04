package com.gilljanssen.RogueColony.Components;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 */
public class PlayerInputSystem extends EntityProcessingSystem implements InputProcessor {
    @Mapper
    ComponentMapper<Velocity> vm;
    @Mapper
    ComponentMapper<Position> pm;

    private OrthographicCamera camera;
    private Velocity velocity;
    private int buttonsDown;
    private int speed;
    private boolean upDown;
    private boolean leftDown;
    private boolean downDown;
    private boolean rightDown;

    public PlayerInputSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Velocity.class, Player.class));
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void process(Entity e) {
        Velocity vel = vm.get(e);
        Position pos = pm.get(e);

        vel.vx = velocity.vx;
        vel.vy = velocity.vy;

        camera.position.set(pos.x, pos.y, 0);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                buttonsDown++;
                upDown = true;
                velocity.vy = speed;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                buttonsDown++;
                leftDown = true;
                velocity.vx = -speed;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                buttonsDown++;
                downDown = true;
                velocity.vy = -speed;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                rightDown = true;
                buttonsDown++;
                velocity.vx = speed;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                buttonsDown--;
                upDown = false;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                buttonsDown--;
                leftDown = false;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                buttonsDown--;
                downDown = false;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                buttonsDown--;
                rightDown = false;
                break;
        }

        if (buttonsDown < 1) {
            velocity.vx = 0;
            velocity.vy = 0;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
