package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;

public class FastEnemy extends Enemy {
    public FastEnemy(String textToType, float x, float y, float speed, Texture spriteSheet) {
        super(textToType, x, y, speed, spriteSheet);
    }

    @Override
    public void move(float deltaTime) {
        x -= speed * 1.5f * deltaTime; // Kecepatan lebih tinggi
    }
}

