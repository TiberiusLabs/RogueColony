package com.gilljanssen.RogueColony.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gilljanssen.RogueColony.Components.Player;
import com.gilljanssen.RogueColony.Components.Position;
import com.gilljanssen.RogueColony.Components.Velocity;

/**
 */
public class PlayerInputSystem extends EntityProcessingSystem implements InputProcessor {
    @Mapper
    ComponentMapper<Velocity> vm;
    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Player> plm;

    private OrthographicCamera camera;
    private Velocity velocity;
    private float speed;
    private boolean upDown;
    private boolean leftDown;
    private boolean downDown;
    private boolean rightDown;
    private boolean keyChanged;
    private int lastDirection;

    public PlayerInputSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Velocity.class, Player.class));
        this.camera = camera;
        this.velocity = new Velocity();
        this.speed = 500;
        this.keyChanged = false;
    }

    @Override
    protected void initialize() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void process(Entity e) {
        Velocity vel = vm.get(e);
        Position pos = pm.get(e);
        Player player = plm.get(e);

        vel.vx = velocity.vx;
        vel.vy = velocity.vy;

        player.lastDirection = lastDirection;
        player.animationChanged = keyChanged;
        player.isRunning = vel.vx != 0 || vel.vy != 0;

        camera.position.set(pos.x, pos.y, 0);
        camera.update();

        if (keyChanged) keyChanged = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                if (!keyChanged) keyChanged = true;
                upDown = true;
                velocity.vy = speed;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                if (!keyChanged) keyChanged = true;
                leftDown = true;
                velocity.vx = -speed;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                if (!keyChanged) keyChanged = true;
                downDown = true;
                velocity.vy = -speed;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                if (!keyChanged) keyChanged = true;
                rightDown = true;
                velocity.vx = speed;
                break;
        }
        updateDirection();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                upDown = false;
                if (!keyChanged) keyChanged = true;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                leftDown = false;
                if (!keyChanged) keyChanged = true;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                downDown = false;
                if (!keyChanged) keyChanged = true;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                rightDown = false;
                if (!keyChanged) keyChanged = true;
                break;
        }

        if (!upDown && !downDown && !leftDown && !rightDown) {
            velocity.vx = 0;
            velocity.vy = 0;
        }

        updateDirection();

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

    private void updateDirection() {
        if (upDown) {
            if (leftDown) {
                velocity.vx = -speed * 0.894f;
                velocity.vy = speed * 0.447f;
                lastDirection = 1;
            } else if (rightDown) {
                velocity.vx = speed * 0.894f;
                velocity.vy = speed * 0.447f;
                lastDirection = 3;
            } else {
                velocity.vx = 0;
                velocity.vy = speed;
                lastDirection = 2;
            }
        } else if (downDown) {
            if (leftDown) {
                velocity.vx = -speed * 0.894f;
                velocity.vy = -speed * 0.447f;
                lastDirection = 7;
            } else if (rightDown) {
                velocity.vx = speed * 0.894f;
                velocity.vy = -speed * 0.447f;
                lastDirection = 5;
            } else {
                velocity.vx = 0;
                velocity.vy = -speed;
                lastDirection = 6;
            }
        } else if (leftDown) {
            velocity.vx = -speed;
            velocity.vy = 0;
            lastDirection = 0;
        } else if (rightDown) {
            velocity.vx = speed;
            velocity.vy = 0;
            lastDirection = 4;
        }
    }
}
