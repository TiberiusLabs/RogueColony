package com.gilljanssen.RogueColony.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.gilljanssen.RogueColony.Components.Position;
import com.gilljanssen.RogueColony.Components.Velocity;

/**
 */
public class MovementSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Velocity> vm;

    TiledMapTileLayer collisionLayer;

    public MovementSystem(TiledMapTileLayer collisionLayer) {
        super(Aspect.getAspectForAll(Position.class, Velocity.class));
        this.collisionLayer = collisionLayer;
    }

    @Override
    protected void process(Entity e) {
        Position pos = pm.get(e);
        Velocity vel = vm.get(e);

        float oldX = pos.x;
        float oldY = pos.y;

        // Move
        pos.x += vel.vx * world.delta;
        pos.y += vel.vy * world.delta;
        float isoX = toIsoX(pos.x + 32, pos.y + 16);
        float isoY = toIsoY(pos.x + 32, pos.y + 16);
        // System.out.println("(" + isoX + ", " + isoY + ")");
        boolean collision = false;

        try {
            if (vel.vx != 0 || vel.vy != 0) {
                // left
                collision = collisionLayer.getCell((int) isoX - 1, (int) isoY - 1).getTile().getProperties().containsKey("blocked");

                // top left
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX - 1, (int) isoY).getTile().getProperties().containsKey("blocked");

                // top
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX, (int) isoY + 1).getTile().getProperties().containsKey("blocked");


                // top right
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX + 1, (int) isoY + 1).getTile().getProperties().containsKey("blocked");


                // right
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX + 1, (int) isoY).getTile().getProperties().containsKey("blocked");


                // bottom right
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX + 1, (int) isoY - 1).getTile().getProperties().containsKey("blocked");


                // bottom
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX, (int) isoY - 1).getTile().getProperties().containsKey("blocked");


                // bottom left
                if (!collision)
                    collision = collisionLayer.getCell((int) isoX - 1, (int) isoY - 1).getTile().getProperties().containsKey("blocked");
            }
        } catch (NullPointerException nullPointerException) {
            collision = true;
        }

        if (collision) {
            System.out.println("Collision!");
            System.out.println("(" + isoX + ", " + isoY + ")");
            pos.x = oldX;
            pos.y = oldY;
            vel.vx = 0;
            vel.vy = 0;
        }
    }

    public int toIsoX (float cartX, float cartY) {
        return (int)Math.floor((double)(cartX / 64.0f)) - (int)Math.floor((double)((cartY - 16) / 32.0f));
    }

    public int toIsoY (float cartX, float cartY) {
        if (Math.floor((cartY - 16) / 16.0f) % 2 == 0) {
            return (int)Math.floor((double)((cartY - 16) / 32.0f)) + (int)Math.floor((double)(cartX / 64.0f));
        } else {
            return (int)Math.floor((double)((cartY) / 32.0f)) + (int)Math.floor((double)((cartX + 32) / 64.0f));
        }
        //return (int)Math.floor((double)((cartY - 16) / 16.0f)) + (int)Math.floor((double)(cartX / 64.0f));
    }
}
