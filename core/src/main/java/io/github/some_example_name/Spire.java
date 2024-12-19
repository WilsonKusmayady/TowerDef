package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spire {
    private Texture texture;
    private float x, y;
    private int health;

    public Spire() {
        this.texture = new Texture("spire.png");
        this.x = 0;
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
        this.health = 100;
    }

    // Getter untuk nyawa
    public int getHealth() {
        return health;
    }

    // Metode untuk mengurangi nyawa
    public void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0; // Pastikan nyawa tidak negatif
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
