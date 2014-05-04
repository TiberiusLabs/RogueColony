package com.gilljanssen.RogueColony.Systems;

import com.artemis.annotations.Mapper;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.gilljanssen.RogueColony.Components.*;

/**
 */
public class PlayerAnimationSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<Animations> am;
    @Mapper
    ComponentMapper<Player> plm;
    @Mapper
    ComponentMapper<Sprite> sm;

    public PlayerAnimationSystem() {
        super(Aspect.getAspectForAll(Player.class, Animations.class, Sprite.class));
    }

    @Override
    protected void process(Entity e) {
        Animations animations = am.get(e);
        Player player = plm.get(e);
        Sprite sprite = sm.get(e);

        if (player.animationChanged) {
            animations.currentAnimation = player.isRunning ?
                    animations.running[player.lastDirection] : animations.stills[player.lastDirection];
            animations.animationTime = 0;
            player.animationChanged = false;
        } else {
            animations.animationTime += world.delta;
        }

        sprite.sprite.setRegion(animations.currentAnimation.getKeyFrame(animations.animationTime));
    }

}
