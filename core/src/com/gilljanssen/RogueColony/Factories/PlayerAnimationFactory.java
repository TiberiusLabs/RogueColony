package com.gilljanssen.RogueColony.Factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.gilljanssen.RogueColony.Components.Animations;

/**
 */
public class PlayerAnimationFactory {
    public static Animations createPlayerAnimations(Texture animationTexture) {
        Animation stills[] = new Animation[8];
        Animation running[] = new Animation[8];

        for (int a = 0; a < 8; a++) {
            // Still animations
            Array<TextureRegion> sArray = new Array<TextureRegion>(4);
            for (int i = 0; i < 4; i++)
                sArray.add(new TextureRegion(animationTexture, 32 + 128 * i, 32 + 128 * a, 64, 64));
            stills[a] = new Animation(1/ 8.0f, sArray, Animation.PlayMode.LOOP_PINGPONG);

            // Run animations
            Array<TextureRegion> rULArray = new Array<TextureRegion>(8);
            for (int i = 0; i < 8; i++)
                rULArray.add(new TextureRegion(animationTexture, 544 + 128 * i, 32 + 128 * a, 64, 64));
            running[a] = new Animation(1/ 12.0f, rULArray, Animation.PlayMode.LOOP);
        }

        return new Animations(stills, running);
    }
}
