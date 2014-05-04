package com.gilljanssen.RogueColony.Systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gilljanssen.RogueColony.Components.Animations;
import com.gilljanssen.RogueColony.Components.Position;

/**
 */
public class AnimationRenderingSystem extends EntitySystem {

    private final OrthographicCamera camera;

    public AnimationRenderingSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Animations.class, Position.class));
        this.camera = camera;
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {

        }
    }

    @Override
    protected boolean checkProcessing() {
        return false;
    }
}
