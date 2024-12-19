package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private Texture texture;
    private float x, y;

    public Player() {
        this.texture = new Texture("player.png");
        this.x = 50; // Letakkan di kiri layar
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
