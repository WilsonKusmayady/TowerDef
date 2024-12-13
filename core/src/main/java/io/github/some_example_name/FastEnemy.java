package io.github.some_example_name;

public class FastEnemy extends Enemy {
    public FastEnemy(String textToType, float x, float y, float speed) {
        super(textToType, x, y, speed);
    }

    @Override
    public void move(float deltaTime) {
        x -= speed * 1.5f * deltaTime; // Kecepatan lebih tinggi
    }
}

