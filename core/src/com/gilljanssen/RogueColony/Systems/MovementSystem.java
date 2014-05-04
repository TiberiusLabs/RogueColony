package com.gilljanssen.RogueColony.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.gilljanssen.RogueColony.Components.Position;
import com.gilljanssen.RogueColony.Components.Velocity;

/**
 */
public class MovementSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Velocity> vm;

    public MovementSystem() {
        super(Aspect.getAspectForAll(Position.class, Velocity.class));
    }

    @Override
    protected void process(Entity e) {
        Position pos = pm.get(e);
        Velocity vel = vm.get(e);

        pos.x += vel.vx * world.delta;
        pos.y += vel.vy * world.delta;
    }
}
