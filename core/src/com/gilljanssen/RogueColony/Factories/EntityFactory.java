package com.gilljanssen.RogueColony.Factories;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.gilljanssen.RogueColony.Components.*;

/**
 */
public class EntityFactory {

    public static Entity createPlayer(World world, float x, float y, String textureFile) {
        Entity e = world.createEntity();

        e.addComponent(new Position(x,y))
                .addComponent(new Velocity())
                .addComponent(new Sprite(textureFile))
                .addComponent(AnimationFactory.createPlayerAnimations(new Texture(textureFile)))
                .addComponent(new Layer(Layer.ObjectLayer.CHARACTER))
                .addComponent(new Player());

        return e;
    }

}
