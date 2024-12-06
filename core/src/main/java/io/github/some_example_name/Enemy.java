package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    private String textToType;
    private float x, y;
    private float speed;
    private Texture texture;

    public Enemy(String textToType, float x, float y, float speed) {
        this.textToType = textToType;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture("enemy.png"); // Ganti dengan path gambar musuh Anda
    }

    public void move(float deltaTime) {
        x -= speed * deltaTime; // Gerak ke kiri
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public boolean isOffScreen() {
        return x + texture.getWidth() < 0;
    }

    public boolean isDefeated(String input) {
        return textToType.equalsIgnoreCase(input);
    }

    public String getTextToType() {
        return textToType;
    }

    // Getter untuk posisi X dan Y
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void dispose() {
        texture.dispose();
    }
}
