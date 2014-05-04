package com.gilljanssen.RogueColony.Components;

import com.artemis.Component;

/**
 */
public class Player extends Component {
    public int lastDirection;
    public boolean isRunning;
    public boolean animationChanged;

    public Player() {
        lastDirection = 0;
        isRunning = false;
        animationChanged = false;
    }
}
