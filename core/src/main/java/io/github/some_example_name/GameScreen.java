package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Background background;
    private Player player;
    private Weapon weapon;
    private Spire spire;
    private ArrayList<Enemy> enemies;
    private BitmapFont font;
    private String typedText = "";

    private Texture enemySpriteSheet;

    private int wave = 1;
    String[] sentences = {
        // 2-5 karakter
        "hi", "go", "yes", "no", "cat", "dog", "run", "code", "java", "life", "fast",
        "slow", "game", "play", "hero", "bird", "jump", "type", "text", "dark",

        // 6-10 karakter
        "example", "libgdx", "monster", "tower", "cannon", "defend", "attack", "player",
        "victory", "knight", "shield", "battle", "castle", "dragon", "forest", "health",
        "weapon", "energy", "castle", "faster",

        // 11-15 karakter
        "tower defense", "java coding", "monster wave", "archer attack", "heroic deeds",
        "typing skill", "defender game", "fast and fun", "protect spire", "player choice",
        "victory road", "dragon slayer", "shield maiden", "knight armor", "defense force",

        // 16-20 karakter
        "typing tower defense", "hero saves the day", "monster invasion plan",
        "defense against evil", "victory against all", "player defeats enemy",
        "archer with bow and arrow", "cannon to destroy foes", "spire under attack",
        "dragon burns the land"
    };


    private int playerType = 1; //Jika player type 1 terpilih archer atau 2 terpilih Cannon

    public GameScreen(int playerType){
        this.playerType = playerType;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Background();
        spire = new Spire();
        enemies = new ArrayList<>();
        font = new BitmapFont();

        if (playerType == 1){ //Akan set texture jadi archer
            weapon = new Weapon("bow.png");
            player = new Player("player.png");
        } else if (playerType == 2){ //Set texture Cannon
            weapon = new Weapon("CannonBarrel.png");
            player = new Player("cannon.png");
        }

        // Load sprite sheet untuk musuh
        enemySpriteSheet = new Texture("alien.png"); // Pastikan path sesuai
        generateWave();
    }

    private Enemy createEnemy(String type) {
        String randomSentence = sentences[(int) (Math.random() * sentences.length)];
        float x = 800 + (float) (Math.random() * 200);
        float y = 100 + (float) (Math.random() * 300);
        float speed = 100;

        if (type.equals("fast")) {
            String shortText = FastEnemy.generateShortText(sentences);
            return new FastEnemy(shortText, x, y, speed, enemySpriteSheet);
        } else { // "normal"
            String longText = NormalEnemy.generateLongText(sentences);
            return new NormalEnemy(longText, x, y, speed, enemySpriteSheet);
        }
    }

    private void generateWave() {
        int enemyCount = wave == 1 ? 5 : (wave * 2 - 2);
        enemies.clear();

        for (int i = 0; i < enemyCount; i++) {
            String type = i % 2 == 0 ? "fast" : "normal";
            enemies.add(createEnemy(type));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleTypingInput();

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(delta);

            if (enemy.isDefeated(typedText)) {
                weapon.rotateToEnemy(enemy.getX(), enemy.getY());
                enemy.die(); // Set musuh menjadi mati
                typedText = "";
            }

            if (enemy.isOffScreen()) {
                iterator.remove();
                spire.reduceHealth(10);
            }

            // Hapus musuh setelah animasi mati selesai
            if (enemy.isDead && enemy.deathAnimation.isAnimationFinished(enemy.stateTime)) {
                iterator.remove();
            }
        }

        if (enemies.isEmpty() && spire.getHealth() > 0) {
            wave++;
            generateWave();
        }

        batch.begin();
        background.render(batch);
        player.render(batch);
        weapon.render(batch);
        spire.render(batch);

        for (Enemy enemy : enemies) {
            enemy.render(batch);
            if (!enemy.isDead) {
                font.draw(batch, enemy.getTextToType(), enemy.getX(), enemy.getY() + 50);
            }
        }

        //Display Wave, Spire HP, Typed
        font.draw(batch, "Wave: " + wave, 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Health: " + spire.getHealth(), 10, Gdx.graphics.getHeight() - 30);
        font.draw(batch, "Typed: " + typedText, 50, 50);

        if (spire.getHealth() <= 0) {
            font.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        }

        batch.end();
    }

    private void handleTypingInput() {
        for (int i = 0; i < 255; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (i == Input.Keys.BACKSPACE && typedText.length() > 0) {
                    typedText = typedText.substring(0, typedText.length() - 1);
                } else if (i == Input.Keys.ENTER) {
                    typedText = "";
                }   else if (i == Input.Keys.SPACE) {
                    typedText += " ";
                }
                else {
                    try {
                        char c = Input.Keys.toString(i).toLowerCase().charAt(0);
                        typedText += c;
                    } catch (Exception e) {
                        // Tangani input yang tidak valid
                    }
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        weapon.dispose();
        font.dispose();
        spire.dispose();
        background.dispose();
        weapon.dispose();
        enemySpriteSheet.dispose(); // Dispose sprite sheet

        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
