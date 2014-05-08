package com.gilljanssen.RogueColony.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.gilljanssen.RogueColony.Components.Position;
import com.gilljanssen.RogueColony.Components.Sprite;

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

    /**
     * Creates an entity system that uses the specified aspect as a matcher against entities.
     *
     * @param camera the camera attached to this system
     */
    public SpriteRenderSystem(OrthographicCamera camera, TiledMap map) {
        super(Aspect.getAspectForAll(Position.class, Sprite.class));
        this.camera = camera;
        this.map = map;
    }

    @Override
    protected void initialize() {
        renderer = new IsometricTiledMapRenderer(map);
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            process(entities.get(i));
        }
    }

    protected void process(Entity e) {
        if (pm.has(e)) {
            Position pos = pm.getSafe(e);
            Sprite sprite = sm.get(e);

            renderer.getSpriteBatch().setColor(sprite.r, sprite.g, sprite.b, sprite.a);
            renderer.getSpriteBatch().draw(sprite.sprite, pos.x, pos.y);
        }
    }

    @Override
    protected void begin() {
        renderer.setView(camera);
        renderer.render();

        renderer.getSpriteBatch().setProjectionMatrix(camera.combined);
        renderer.getSpriteBatch().begin();
    }

    @Override
    protected void end() {
        renderer.getSpriteBatch().end();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
