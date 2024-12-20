package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class FastEnemy extends Enemy {
    public FastEnemy(String textToType, float x, float y, float speed, Texture spriteSheet) {
        super(textToType, x, y, speed, spriteSheet);
    }


    @Override
    public void move(float deltaTime) {
        x -= speed * 1.5f * deltaTime; // Kecepatan lebih tinggi
    }

    public static String generateShortText(String[] sentences) {
        ArrayList<String> shortSentences = new ArrayList<>();
        for (String sentence : sentences) {
            if (sentence.length() >= 2 && sentence.length() <= 5) {
                shortSentences.add(sentence);
            }
        }
        return shortSentences.isEmpty() ? "" : shortSentences.get((int) (Math.random() * shortSentences.size()));
    }
}

