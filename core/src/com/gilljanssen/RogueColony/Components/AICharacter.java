package com.gilljanssen.RogueColony.Components;

/**
 * @author Amandeep Gill
 */
public class AICharacter {

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
