package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;
public class NormalEnemy extends Enemy {
    public NormalEnemy(String textToType, float x, float y, float speed, Texture spriteSheet) {
        super(textToType, x, y, speed, spriteSheet);
    }

    @Override
    public void move(float deltaTime) {
        x -= speed * deltaTime; // Kecepatan normal
    }
}

