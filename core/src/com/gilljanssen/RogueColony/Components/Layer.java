package com.gilljanssen.RogueColony.Components;

import com.artemis.Component;

/**
 * @author Amandeep Gill
 */
public class Layer extends Component {

    public ObjectLayer objectLayer;

    public Layer(ObjectLayer objectLayer) {
        this.objectLayer = objectLayer;
    }

    public Layer() {
        this(ObjectLayer.PASSABLE);
    }

    public static enum ObjectLayer {
        BUILDING, IMPASSABLE, PASSABLE, CHARACTER
    }
}
