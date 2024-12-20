package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class NormalEnemy extends Enemy {
    public NormalEnemy(String textToType, float x, float y, float speed, Texture spriteSheet) {
        super(textToType, x, y, speed, spriteSheet);
    }

    @Override
    public void move(float deltaTime) {
        x -= speed * deltaTime; // Kecepatan normal
    }

    public static String generateLongText(String[] sentences) {
        ArrayList<String> longSentences = new ArrayList<>();
        for (String sentence : sentences) {
            if (sentence.length() >= 6 && sentence.length() <= 20) {
                longSentences.add(sentence);
            }
        }
        return longSentences.isEmpty() ? "" : longSentences.get((int) (Math.random() * longSentences.size()));
    }
}

