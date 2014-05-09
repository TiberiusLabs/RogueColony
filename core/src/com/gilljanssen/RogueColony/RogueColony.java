package com.gilljanssen.RogueColony;

import com.badlogic.gdx.Game;


public class RogueColony extends Game {

    @Override
    public void create () {
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }
}

