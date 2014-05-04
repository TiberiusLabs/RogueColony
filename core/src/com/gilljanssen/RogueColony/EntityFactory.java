package com.gilljanssen.RogueColony;

import com.artemis.Entity;
import com.artemis.World;
import com.gilljanssen.RogueColony.Components.Position;
import com.gilljanssen.RogueColony.Components.Sprite;
import com.gilljanssen.RogueColony.Components.Velocity;
import com.gilljanssen.RogueColony.Components.Player;

/**
 */
public class EntityFactory {

    public static Entity createPlayer(World world, float x, float y) {
        Entity e = world.createEntity();

        e.addComponent(new Position(x,y))
                .addComponent(new Sprite("neworc.png"))
                .addComponent(new Velocity())
                .addComponent(new Player());

        return e;
    }

}
