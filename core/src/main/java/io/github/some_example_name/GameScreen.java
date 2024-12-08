package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Player player;
    private Weapon weapon;
    private ArrayList<Enemy> enemies;
    private BitmapFont font;
    private String typedText = ""; // Untuk menyimpan teks yang sedang diketik

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new Player();
        weapon = new Weapon();
        enemies = new ArrayList<>();
        font = new BitmapFont(); // Font default LibGDX

        // Array berisi kalimat yang akan digunakan musuh
        String[] sentences = {
            "hello", "world", "libgdx", "java", "tower", "defense", "typing", "enemy"
        };

        // Tambahkan musuh dengan berbagai tipe
        enemies.add(createEnemy(sentences, "fast"));
        enemies.add(createEnemy(sentences, "normal"));
        enemies.add(createEnemy(sentences, "normal"));
        enemies.add(createEnemy(sentences, "fast"));
    }

    // Metode untuk membuat musuh berdasarkan tipe
    private Enemy createEnemy(String[] sentences, String type) {
        String randomSentence = sentences[(int) (Math.random() * sentences.length)]; // Pilih kalimat acak
        float x = 800 + (float) (Math.random() * 200); // Posisi X acak
        float y = 100 + (float) (Math.random() * 300); // Posisi Y acak
        float speed;

        // Tetapkan kecepatan berdasarkan tipe musuh
        if (type.equals("fast")) {
            speed = 200; // Kecepatan tinggi
        } else { // normal
            speed = 100; // Kecepatan normal
        }

        return new Enemy(randomSentence, x, y, speed);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Perbarui input teks dari keyboard
        handleTypingInput();

        // Gerakkan musuh
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.move(delta);

            // Jika teks yang diketik cocok, musuh dihapus
            if (enemy.isDefeated(typedText)) {
                weapon.rotateToEnemy(enemy.getX(), enemy.getY());
                iterator.remove();
                typedText = ""; // Reset teks
            }

            // Jika musuh keluar layar, hapus
            if (enemy.isOffScreen()) {
                iterator.remove();
            }
        }

        // Render semua elemen
        batch.begin();
        player.render(batch); // Render player
        weapon.render(batch);

        for (Enemy enemy : enemies) {
            enemy.render(batch); // Render musuh
            font.draw(batch, enemy.getTextToType(), enemy.getX(), enemy.getY() + 50); // Tampilkan teks musuh
        }

        font.draw(batch, "Typed: " + typedText, 50, 50); // Tampilkan teks yang diketik
        batch.end();
    }

    private void handleTypingInput() {
        // Ambil input keyboard
        for (int i = 0; i < 255; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (i == Input.Keys.BACKSPACE && typedText.length() > 0) {
                    typedText = typedText.substring(0, typedText.length() - 1);
                } else if (i == Input.Keys.ENTER) {
                    // Enter untuk validasi, tidak melakukan apa-apa
                } else {
                    char c = Input.Keys.toString(i).toLowerCase().charAt(0);
                    typedText += c;
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
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
