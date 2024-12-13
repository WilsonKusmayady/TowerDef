package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Enemy {
    protected String textToType;
    protected float x, y;
    protected float speed;
    protected Texture texture;

    public Enemy(String textToType, float x, float y, float speed) {
        this.textToType = textToType;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture("enemy.png"); // Ganti dengan path gambar musuh Anda
    }

    // Metode abstract yang akan diimplementasikan di subclass
    public abstract void move(float deltaTime);

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
