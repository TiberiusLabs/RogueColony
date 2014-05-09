package com.gilljanssen.RogueColony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen {

    private RogueColony game;
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle orc, archer, zombie, horse;
    private Vector3 touchPos;

    // setup the dimensions of the menu buttons
    private static final float BUTTON_WIDTH = 300f;
    private static final float BUTTON_HEIGHT = 60f;
    private static final float BUTTON_SPACING = 10f;

    public MenuScreen(RogueColony game )
    {
        this.game = game;
        font = new BitmapFont();
        font.setScale(3);
        batch = new SpriteBatch();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        orc = new Rectangle(200, 300, 400, 100);
        archer = new Rectangle(200, 200, 400, 100);
        zombie = new Rectangle(200, 100, 400, 100);
        horse = new Rectangle(200, 0, 400, 100);
        touchPos = new Vector3();
    }

    @Override
    public void resize(int width, int height )
    {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // orc archer zombie horse
        font.draw(batch, "Orc", 300, 400);
        font.draw(batch, "Archer", 300, 300);
        font.draw(batch, "Zombie", 300, 200);
        font.draw(batch, "Horse", 300, 100);

        batch.end();

        if(Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (touchPos.x < 600 && touchPos.x > 200) {
                System.out.println(touchPos);
                if (touchPos.y > 0 && touchPos.y < 400) {
                    if (touchPos.y < 100) game.setScreen(new RCScreen(game, "horse.png"));
                    else if (touchPos.y < 200) game.setScreen(new RCScreen(game, "zombie.png"));
                    else if (touchPos.y < 300) game.setScreen(new RCScreen(game, "archer.png"));
                    else if (touchPos.y < 400) game.setScreen(new RCScreen(game, "orc.png"));
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
