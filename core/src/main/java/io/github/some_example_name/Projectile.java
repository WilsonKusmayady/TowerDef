package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public abstract class Projectile {
    protected Texture texture;
    protected float x, y;
    protected float speed;
    protected float rotation; // Rotation in degrees

    public Projectile(float x, float y, float speed, float rotation) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.rotation = rotation;
        this.texture = new Texture("projectile.png");
    }

    public abstract void fire(float deltaTime);

    public void render(SpriteBatch batch) {
        batch.draw(
            texture,
            x, y,
            texture.getWidth() / 2f, texture.getHeight() / 2f, // Origin for rotation (center of texture)
            texture.getWidth(), texture.getHeight(),
            1, 1, // Scale (1, 1 means no scaling)
            rotation, // Apply rotation
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false
        );
    }

    public void dispose() {
        texture.dispose();
    }
}
