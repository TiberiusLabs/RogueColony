package com.gilljanssen.RogueColony.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.gilljanssen.RogueColony.Components.AICharacter;
import com.gilljanssen.RogueColony.Components.Animations;
import com.gilljanssen.RogueColony.Components.Sprite;

public class AIAnimationSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<Animations> am;
    @Mapper
    ComponentMapper<AICharacter> aim;
    @Mapper
    ComponentMapper<Sprite> sm;

    public AIAnimationSystem() {
        super(Aspect.getAspectForAll(AICharacter.class, Animations.class, Sprite.class));
    }

    @Override
    protected void process(Entity e) {
        Animations animations = am.get(e);
        Sprite sprite = sm.get(e);

        animations.animationTime += world.delta;
        sprite.sprite.setRegion(animations.currentAnimation.getKeyFrame(animations.animationTime));
    }

}
