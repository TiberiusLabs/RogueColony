package com.gilljanssen.RogueColony.Components;

import com.artemis.Component;

/**
 */
public class Velocity extends Component {
    public float vx;
    public float vy;

    public Velocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public Velocity() {
        this(0,0);
    }
}
