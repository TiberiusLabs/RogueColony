package com.gilljanssen.RogueColony.Factories;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.gilljanssen.RogueColony.Components.*;

public class NPCFactory {

    public static Entity createNPC(World world, float x, float y, String file) {
        Entity e = world.createEntity();

        e.addComponent(new Position(x,y))
                .addComponent(new Velocity())
                .addComponent(new Sprite(file))
                .addComponent(AnimationFactory.createPlayerAnimations(new Texture(file)))
                .addComponent(new Layer(Layer.ObjectLayer.CHARACTER))
                .addComponent(new AICharacter(AICharacter.Faction.TRIBAL));

        return e;
    }
}
