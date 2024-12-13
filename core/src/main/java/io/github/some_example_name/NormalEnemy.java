package io.github.some_example_name;
public class NormalEnemy extends Enemy {
    public NormalEnemy(String textToType, float x, float y, float speed) {
        super(textToType, x, y, speed);
    }

    @Override
    public void move(float deltaTime) {
        x -= speed * deltaTime; // Kecepatan normal
    }
}

