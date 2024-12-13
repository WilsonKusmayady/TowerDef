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
    private String typedText = "";
    // Untuk menyimpan teks yang sedang diketik

    private int wave = 1;
    private String[] sentences = { "hello", "world", "libgdx", "java", "tower", "defense", "typing", "enemy" }; // Kalimat untuk musuh

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new Player();
        weapon = new Weapon();
        enemies = new ArrayList<>();
        font = new BitmapFont();

        generateWave(); // Memulai wave pertama
    }

    private Enemy createEnemy(String type) {
        // Pilih kalimat acak
        String randomSentence = sentences[(int) (Math.random() * sentences.length)];
        float x = 800 + (float) (Math.random() * 200); // Posisi X acak
        float y = 100 + (float) (Math.random() * 300); // Posisi Y acak
        float speed = 100; // Kecepatan dasar untuk normal enemy

        // Jika tipe adalah "fast", buat objek FastEnemy, jika tidak buat NormalEnemy
        if (type.equals("fast")) {
            return new FastEnemy(randomSentence, x, y, speed);
        } else { // "normal"
            return new NormalEnemy(randomSentence, x, y, speed);
        }
    }


    // Metode untuk menghasilkan musuh pada wave tertentu
    private void generateWave() {
        int enemyCount = wave == 1 ? 5 : (wave * 2 - 2); // Jumlah musuh berdasarkan wave
        enemies.clear(); // Hapus semua musuh dari wave sebelumnya

        for (int i = 0; i < enemyCount; i++) {
            String type = i % 2 == 0 ? "fast" : "normal"; // Bergantian antara fast dan normal
            enemies.add(createEnemy(type));
        }
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

            // Jika musuh keluar layar, kurangi nyawa pemain
            if (enemy.isOffScreen()) {
                iterator.remove();
                player.reduceHealth(10); // Kurangi nyawa pemain sebesar 10
            }
        }

        // Jika semua musuh habis, naikkan wave
        if (enemies.isEmpty() && player.getHealth() > 0) { // Lanjut wave hanya jika pemain belum kalah
            wave++;
            generateWave();
        }

        // Render semua elemen
        batch.begin();
        player.render(batch); // Render pemain
        weapon.render(batch);

        for (Enemy enemy : enemies) {
            enemy.render(batch); // Render musuh
            font.draw(batch, enemy.getTextToType(), enemy.getX(), enemy.getY() + 50); // Tampilkan teks musuh
        }

        // Tampilkan nyawa dan wave pemain
        font.draw(batch, "Wave: " + wave, 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Health: " + player.getHealth(), 10, Gdx.graphics.getHeight() - 30);
        font.draw(batch, "Typed: " + typedText, 50, 50);

        // Tampilkan Game Over jika nyawa habis
        if (player.getHealth() <= 0) {
            font.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        }

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
