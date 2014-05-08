package com.gilljanssen.RogueColony.Factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.gilljanssen.RogueColony.Components.Animations;

/**
 */
public class AnimationFactory {
    public static Animations createPlayerAnimations(Texture animationTexture) {
        Animation stills[] = new Animation[8];
        Animation running[] = new Animation[8];

        for (int a = 0; a < 8; a++) {
            // Still animations
            Array<TextureRegion> sArray = new Array<TextureRegion>(4);
            for (int i = 0; i < 4; i++)
                sArray.add(new TextureRegion(animationTexture, 128 * i, 128 * a, 128, 128));
            stills[a] = new Animation(1/ 8.0f, sArray, Animation.PlayMode.LOOP_PINGPONG);

            // Run animations
            Array<TextureRegion> rULArray = new Array<TextureRegion>(8);
            for (int i = 0; i < 8; i++)
                rULArray.add(new TextureRegion(animationTexture, 512 + 128 * i, 128 * a, 128, 128));
            running[a] = new Animation(1/ 12.0f, rULArray, Animation.PlayMode.LOOP);
        }

        return new Animations(stills, running);
    }

    public static Animations createNPCAnimations(Texture animationTexture) {

        Array<TextureRegion> frames = new Array<TextureRegion>(55);
        for (int i = 0; i < 55; i++)
            frames.add(new TextureRegion(animationTexture, 128 * i, 0, 128, 128));
        Animation animation = new Animation(1 / 8.0f, frames, Animation.PlayMode.LOOP);

        return new Animations(animation);
    }
}
