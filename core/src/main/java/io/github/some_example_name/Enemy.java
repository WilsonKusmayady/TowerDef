package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class Enemy {
    private Texture spriteSheet;
    protected String textToType;
    protected float x, y;
    protected float speed;

    protected Animation<TextureRegion> walkAnimation; // Animasi berjalan
    protected Animation<TextureRegion> deathAnimation; // Animasi mati
    protected float stateTime; // Waktu untuk animasi berjalan

    protected boolean isDead;

    public Enemy(String textToType, float x, float y, float speed, Texture spriteSheet) {
        this.textToType = textToType;
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.isDead = false;
        this.stateTime = 0;

        // Memotong sprite sheet menjadi frame
        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, 56, 38);
        TextureRegion[][] tmpFramesDeath = TextureRegion.split(spriteSheet, 52, 66);
        Array<TextureRegion> walkFrames = new Array<>();
        Array<TextureRegion> deathFrames = new Array<>();

        // Ambil animasi berjalan
        for (int i = 0; i < 5; i++) {
            walkFrames.add(tmpFrames[0][i]);
        }

        // Ambil animasi mati
        for (int i = 0; i < 5; i++) {
            deathFrames.add(tmpFramesDeath[1][i]);
        }

        this.walkAnimation = new Animation<>(0.1f, walkFrames);
        this.deathAnimation = new Animation<>(0.1f, deathFrames);
    }

    // Metode abstract yang akan diimplementasikan di subclass
    public abstract void move(float deltaTime);

    public void render(SpriteBatch batch) {
        TextureRegion currentFrame;

        if (isDead) {
            currentFrame = deathAnimation.getKeyFrame(stateTime, false); // Animasi mati
        } else {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true); // Animasi berjalan
        }
        batch.draw(currentFrame, x, y);
    }

    public void update(float deltaTime) {
        stateTime += deltaTime; // Update waktu untuk animasi
        if (!isDead) {
            move(deltaTime);
        }
    }

    public void die() {
        isDead = true;
        stateTime = 0;
        // Reset waktu animasi mati
    }

    public void dispose() {
        if (spriteSheet != null) {
            spriteSheet.dispose();
            // Bebaskan resource spriteSheet
        }
    }

    public boolean isOffScreen() {
        return x + 64 < 0;
    }

    public boolean isDefeated(String sentence) {
        return textToType.equalsIgnoreCase(sentence);
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
}
