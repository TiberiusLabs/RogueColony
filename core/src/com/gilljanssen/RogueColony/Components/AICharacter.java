package com.gilljanssen.RogueColony.Components;

import com.artemis.Component;

/**
 * @author Amandeep Gill
 */
public class AICharacter extends Component {

    public Faction faction;

    public AICharacter(Faction faction) {
        this.faction = faction;
    }

    public AICharacter() {
        this(Faction.NEUTRAL);
    }

    public enum Faction {
        IMPERIAL, TRIBAL, CULTIST, NEUTRAL
    }
}
