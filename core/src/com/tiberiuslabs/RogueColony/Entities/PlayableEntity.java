package com.tiberiuslabs.RogueColony.Entities;

/**
 * Interface for interacting with and updating the NPCs/PlayerChars
 *
 * @author Amandeep Gill
 */
public interface PlayableEntity {
    // let the entity update the information that it knows about the game world
    public void update(long dt);
}
