package com.gilljanssen.RogueColony.Components;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

/**
 * System for rendering textures
 *
 * @author Amandeep Gill
 */
public class SpriteRenderSystem extends EntitySystem {
    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Sprite> sm;

    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    /**
     * Creates an entity system that uses the specified aspect as a matcher against entities.
     *
     * @param camera the camera attached to this system
     */
    public SpriteRenderSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Position.class, Sprite.class));
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            process(entities.get(i));
        }
    }

    protected void process(Entity entity) {
        if (pm.has(entity)) {
            Position pos = pm.getSafe(entity);
            Sprite sprite = sm.get(entity);

            batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
            batch.draw(sprite.sprite, pos.x, pos.y);
        }
    }

    protected void begin() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    protected void end() {
        batch.end();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
