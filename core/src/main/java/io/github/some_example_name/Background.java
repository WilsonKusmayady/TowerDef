package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture texture;
    private float x, y;

    public Background() {
        this.texture = new Texture("background1.png");
        this.x = (Gdx.graphics.getWidth() - texture.getWidth()) / 2f; // Letakkan di kiri layar
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
