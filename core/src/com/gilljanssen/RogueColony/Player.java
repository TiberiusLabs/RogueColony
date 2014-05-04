package com.gilljanssen.RogueColony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity;
    private float speed;
    private float gravity;
    private float animationTime;
    private TiledMapTileLayer collisionLayer;
    private int buttonsDown;
    private boolean upDown, rightDown, downDown, leftDown;
    private Animation stillLeft, stillUpLeft, stillUp, stillUpRight, stillRight, stillDownRight, stillDown, stillDownLeft;
    private Animation runLeft, runUpLeft, runUp, runUpRight, runRight, runDownRight, runDown, runDownLeft;

    public Player(TiledMapTileLayer collisionLayer) {
        super(new Sprite(new TextureRegion(new Texture("neworc.png"), 32, 32, 64, 64)));
        makeAnimations();
        velocity = new Vector2();
        speed = 60.0f * 3;
        this.collisionLayer = collisionLayer;
        animationTime = 0;
        buttonsDown = 0;
        upDown = false;
        rightDown = false;
        downDown = false;
        leftDown = false;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {

        updateDirection();

        float oldX = getX();
        float oldY = getY();
        float tileWidth = 32.0f;
        float tileHeight = 32.0f;
        boolean collisionX = false;
        boolean collisionY = false;

        float isoX = toIsoX(oldX, oldY);
        float isoY = toIsoY(oldX, oldY);

        if (velocity.x != 0 || velocity.y != 0) {
            System.out.println();
            System.out.println("iso coordinates (96, 48): " + toIsoX(96, 48) + ", " + toIsoY(96, 48));
            System.out.println("iso coordinates (" + oldX + ", " + oldY + "): " + toIsoX(oldX, oldY) + ", " + toIsoY(oldX, oldY));
            System.out.println("Cartesian x,y: " + oldX + "," + oldY);
            System.out.println("Isometric cell x,y: " + (int)isoX + ", " + (int)isoY);
            System.out.println("Tile width, hight: " + tileWidth + ", " + tileHeight);
            System.out.println("Cell coordinates: " + (int)(oldX / tileWidth) + ", " + (int)(oldY / tileHeight));
        }

        // move on x
        setX(getX() + velocity.x * delta);
//
//        if(velocity.x < 0) {
//            System.out.println("TL");
//            // Top left
//            collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()) / tileHeight))
//                    .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("ML");
//            // Middle left
//            if (!collisionX)
//                collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("BL");
//            // Bottom left
//            if (!collisionX)
//                collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//        } else if (velocity.x > 0) {
//            System.out.println("TR");
//            // Top right
//            collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight()) / tileHeight))
//                    .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("MR");
//            // Middle right
//            if (!collisionX)
//                collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("BR");
//            // Bottom right
//            if (!collisionX)
//                collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//        }
//
//        // react to x collision
//        if (collisionX) {
//            setX(oldX);
//            velocity.x = 0;
//        }
//
        // move on y
        setY(getY() + velocity.y * delta);
//
//        if(velocity.y < 0) {
//            System.out.println("BL");
//            // Bottom left
//            collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight))
//                    .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("BM");
//            // Bottom middle
//            if (!collisionY)
//                collisionY = collisionLayer.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)(getY() / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("BR");
//            // Bottom right
//            if (!collisionY)
//                collisionY = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//        } else if (velocity.y > 0) {
//            System.out.println("TL");
//            // Top left
//            collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()) / tileHeight))
//                    .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("TM");
//            // Top middle
//            if (!collisionY)
//                collisionY = collisionLayer.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)((getY() + getHeight()) / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//
//            System.out.println("TR");
//            // Top right
//            if (!collisionY)
//                collisionY = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight))
//                        .getTile().getProperties().containsKey("blocked");
//        }
//
//        // react to y collision
//        if (collisionY) {
//            setY(oldY);
//            velocity.y = 0;
//        }

        animationTime += delta;
        setRegion(stillRight.getKeyFrame(animationTime));

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                buttonsDown++;
                upDown = true;
                velocity.y = speed;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                buttonsDown++;
                leftDown = true;
                velocity.x = -speed;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                buttonsDown++;
                downDown = true;
                velocity.y = -speed;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                rightDown = true;
                buttonsDown++;
                velocity.x = speed;
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

    public int toIsoX (float cartX, float cartY) {
        return (int)Math.floor((double)(cartX / 64.0f)) - (int)Math.floor((double)((cartY - 16) / 32.0f));
    }

    public int toIsoY (float cartX, float cartY) {
        if (Math.floor((cartY - 16) / 16.0f) % 2 == 0) {
            if (velocity.x != 0 || velocity.y != 0) System.out.println(1);
            return (int)Math.floor((double)((cartY - 16) / 32.0f)) + (int)Math.floor((double)(cartX / 64.0f));
        } else {
            if (velocity.x != 0 || velocity.y != 0) System.out.println(2);
            return (int)Math.floor((double)((cartY) / 32.0f)) + (int)Math.floor((double)((cartX + 32) / 64.0f));
        }
        //return (int)Math.floor((double)((cartY - 16) / 16.0f)) + (int)Math.floor((double)(cartX / 64.0f));
    }

    public float toCartX (float isoX, float isoY) {

        return (isoX + isoY) * 32.0f;
    }

    public float toCartY (float isoX, float isoY) {
        return (isoY - isoX) * 16.0f + 16;
    }

    private void makeAnimations() {
        Texture animationTexture = new Texture("neworc.png");

        // Still animations

        Array<TextureRegion> sLArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sLArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32, 64, 64));
        stillLeft = new Animation(1/ 8.0f, sLArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sULArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sULArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128, 64, 64));
        stillUpLeft = new Animation(1/ 8.0f, sULArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sUArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sUArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * 2, 64, 64));
        stillUp = new Animation(1/ 8.0f, sUArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sURArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sURArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * 3, 64, 64));
        stillUpRight = new Animation(1/ 8.0f, sURArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sRArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sRArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * 4, 64, 64));
        stillRight = new Animation(1/ 8.0f, sRArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sDRArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sDRArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * 5, 64, 64));
        stillDownRight = new Animation(1/ 8.0f, sDRArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sDArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sDArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * 6, 64, 64));
        stillDown = new Animation(1/ 8.0f, sDArray, Animation.PlayMode.LOOP_PINGPONG);

        Array<TextureRegion> sDLArray = new Array<TextureRegion>(4);
        for (int i = 0; i < 4; i++)
            sDLArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * 7, 64, 64));
        stillDownLeft = new Animation(1/ 8.0f, sDLArray, Animation.PlayMode.LOOP_PINGPONG);

        // Run animations

        Array<TextureRegion> rLArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rLArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32, 64, 64));
        runLeft = new Animation(1/ 8.0f, rLArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rULArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rULArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128, 64, 64));
        runUpLeft = new Animation(1/ 8.0f, rULArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rUArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rUArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * 2, 64, 64));
        runUp = new Animation(1/ 8.0f, rUArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rURArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rURArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * 3, 64, 64));
        runUpRight = new Animation(1/ 8.0f, rURArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rRArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rRArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * 4, 64, 64));
        runRight = new Animation(1/ 8.0f, rRArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rDRArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rDRArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * 5, 64, 64));
        runDownRight = new Animation(1/ 8.0f, rDRArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rDArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rDArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * 6, 64, 64));
        runDown = new Animation(1/ 8.0f, rDArray, Animation.PlayMode.LOOP);

        Array<TextureRegion> rDLArray = new Array<TextureRegion>(8);
        for (int i = 0; i < 8; i++)
            rDLArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * 7, 64, 64));
        runDownLeft = new Animation(1/ 8.0f, rDLArray, Animation.PlayMode.LOOP);
    }

    private void updateDirection() {
        if (upDown) {
            if (leftDown) {
                velocity.x = -speed * 0.866f;
                velocity.y = speed * 0.5f;
            } else if (rightDown) {
                velocity.x = speed * 0.866f;
                velocity.y = speed * 0.5f;
            } else {
                velocity.x = 0;
                velocity.y = speed;
            }
        } else if (downDown) {
            if (leftDown) {
                velocity.x = -speed * 0.866f;
                velocity.y = -speed * 0.5f;
            } else if (rightDown) {
                velocity.x = speed * 0.866f;
                velocity.y = -speed * 0.5f;
            } else {
                velocity.x = 0;
                velocity.y = -speed;
            }
        } else if (leftDown) {
            velocity.x = -speed;
            velocity.y = 0;
        } else if (rightDown) {
            velocity.x = speed;
            velocity.y = 0;
        }
    }
}
