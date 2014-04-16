package com.tiberiuslabs.RogueColony.GameState;

import com.badlogic.gdx.utils.Array;

/**
 * interface to allow the entities to discover information about their surroundings
 */
public interface GameState {
    // return the tile at position (x,y) in the gameworld
    Tile getTile(int x, int y);

    // return a collection of all tiles within range of a given position
    Array<Tile> getTilesInRange(int x, int y, int range);
}
