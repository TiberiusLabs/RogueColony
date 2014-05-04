package com.gilljanssen.RogueColony.Components;

import com.artemis.Component;

/**
 * Positional data component
 *
 * @author Amandeep Gill
 */
public class Position extends Component {

    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this(0,0);
    }
}
