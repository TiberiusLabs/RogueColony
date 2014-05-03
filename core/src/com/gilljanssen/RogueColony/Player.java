package com.gilljanssen.RogueColony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity;
    private float speed;
    private float gravity;
    private TiledMapTileLayer collisionLayer;

    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        velocity = new Vector2();
        speed = 60.0f * 2;
        gravity = 60.0f;
        this.collisionLayer = collisionLayer;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {

        float oldX = getX();
        float oldY = getY();
        float tileWidth = collisionLayer.getTileWidth();
        float tileHeight = collisionLayer.getTileHeight();
        boolean collisionX = false;
        boolean collisionY = false;

        float isoX = (oldX - oldY) / 2;
        float isoY = (oldY + oldX) / 2;
        double isoTileWidth = Math.sqrt((double)((32 * 32) + (16 * 16)));

        if (velocity.x != 0 || velocity.y != 0) {
            System.out.println("Cartesian x,y: " + oldX + "," + oldY);
            System.out.println("Isometric x,y: " + (int)isoX + ", " + (int)isoY);
            System.out.println("Tile width, hight: " + tileWidth + ", " + tileHeight);
            System.out.println("Cell coordinates: " + (int)(oldX / tileWidth) + ", " + (int)(oldY / tileHeight));
            System.out.println("Iso Cell coordinates: " + (int)(isoX / isoTileWidth) + ", " + (int)(isoY / isoTileWidth));
        }

        // move on x
        setX(getX() + velocity.x * delta);

        if(velocity.x < 0) {
            System.out.println("TL");
            // Top left
            collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()) / tileHeight))
                    .getTile().getProperties().containsKey("blocked");

            System.out.println("ML");
            // Middle left
            if (!collisionX)
                collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight))
                        .getTile().getProperties().containsKey("blocked");

            System.out.println("BL");
            // Bottom left
            if (!collisionX)
                collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight))
                        .getTile().getProperties().containsKey("blocked");
        } else if (velocity.x > 0) {
            System.out.println("TR");
            // Top right
            collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight()) / tileHeight))
                    .getTile().getProperties().containsKey("blocked");

            System.out.println("MR");
            // Middle right
            if (!collisionX)
                collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight))
                        .getTile().getProperties().containsKey("blocked");

            System.out.println("BR");
            // Bottom right
            if (!collisionX)
                collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight))
                        .getTile().getProperties().containsKey("blocked");
        }

        // react to x collision
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        // move on y
        setY(getY() + velocity.y * delta);

        if(velocity.y < 0) {
            System.out.println("BL");
            // Bottom left
            collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight))
                    .getTile().getProperties().containsKey("blocked");

            System.out.println("BM");
            // Bottom middle
            if (!collisionY)
                collisionY = collisionLayer.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)(getY() / tileHeight))
                        .getTile().getProperties().containsKey("blocked");

            System.out.println("BR");
            // Bottom right
            if (!collisionY)
                collisionY = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight))
                        .getTile().getProperties().containsKey("blocked");
        } else if (velocity.y > 0) {
            System.out.println("TL");
            // Top left
            collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()) / tileHeight))
                    .getTile().getProperties().containsKey("blocked");

            System.out.println("TM");
            // Top middle
            if (!collisionY)
                collisionY = collisionLayer.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)((getY() + getHeight()) / tileHeight))
                        .getTile().getProperties().containsKey("blocked");

            System.out.println("TR");
            // Top right
            if (!collisionY)
                collisionY = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight))
                        .getTile().getProperties().containsKey("blocked");
        }

        // react to y collision
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        }
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                velocity.y = speed;
                break;
            case Input.Keys.A:
                velocity.x = -speed;
                break;
            case Input.Keys.S:
                velocity.y = -speed;
                break;
            case Input.Keys.D:
                velocity.x = speed;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.A:
            case Input.Keys.S:
            case Input.Keys.D:
                velocity.x = 0;
                velocity.y = 0;
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

    public float toIsoX (float cartX, float cartY) {
        return (cartX - cartY) / 2;
    }

    public float toIsoY (float cartX, float cartY) {
        return (cartX + cartY) / 2;
    }

    public float toCartX (float isoX, float isoY) {
        return 0;
    }

    public float toCartY (float isoX, float isoY) {
        return 0;
    }
}
