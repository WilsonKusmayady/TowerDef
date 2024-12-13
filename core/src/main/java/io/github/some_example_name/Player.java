package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private Texture texture;
    private float x, y;
    private int health; // Tambahkan atribut untuk nyawa

    public Player() {
        this.texture = new Texture("player.png");
        this.x = 50; // Letakkan di kiri layar
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
        this.health = 100; // Set nyawa awal
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
