package com.gilljanssen.RogueColony.Components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 */
public class Animations extends Component {
    public float animationTime;
    public Animation currentAnimation;
    // stillLeft, stillUpLeft, stillUp, stillUpRight, stillRight, stillDownRight, stillDown, stillDownLeft;
    public Animation[] stills;
    // runLeft, runUpLeft, runUp, runUpRight, runRight, runDownRight, runDown, runDownLeft;
    public Animation[] running;

    public Animations(Animation[] stills, Animation[] running) {
        this.stills = stills;
        this.running = running;

        currentAnimation = stills[5];
        animationTime = 0;
    }
}
