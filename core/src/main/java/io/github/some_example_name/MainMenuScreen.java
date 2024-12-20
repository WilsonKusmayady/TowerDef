package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture archerTexture;
    private Texture cannonTexture;
    private int playerType; // 1 for Archer, 2 for Cannon

    private TowerDefenseGame game;

    public MainMenuScreen(TowerDefenseGame game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        playerType = 1; // Default player type
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Select Player Type:", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() - 50);
        font.draw(batch, "Press 1 for Archer", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Press 2 for Cannon", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() - 150);
        font.draw(batch, "Press ENTER to Start", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() - 200);

        // Display selected player type
        if (playerType == 1) {
            font.draw(batch, "Selected: Archer", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() - 250);
        } else if (playerType == 2) {
            font.draw(batch, "Selected: Cannon", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() - 250);
        }

        batch.end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            playerType = 1;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            playerType = 2;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(playerType));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        archerTexture.dispose();
        cannonTexture.dispose();
    }
}
