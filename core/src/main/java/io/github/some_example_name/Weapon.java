package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Weapon {
    private float rotation;
    private Texture texture;
    private float x, y;

    public Weapon(){
        this.texture = new Texture("bow.png");
        this.x = 60;
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
        this.rotation = 0;
    }

    public void rotateToEnemy(float enemyX, float enemyY){ //function untuk rotate kepada enemy
        float deltaX = enemyX - x;
        float deltaY = enemyY - y;
        rotation = MathUtils.radiansToDegrees * MathUtils.atan2(deltaY, deltaX); //Menghitung rotasi kepada target
    }

    public void render(SpriteBatch batch) {
        batch.draw(
            texture,
            x, y,
            texture.getWidth() / 2f, texture.getHeight() / 2f,
            texture.getWidth(), texture.getHeight(),
            1, 1, //Scale dari sprite (1,1 berarti tidak berganti)
            rotation, //sudut rotasi
            0, 0, //koordinat titik ketika berputar (0,0 berarti center)
            texture.getWidth(), texture.getHeight(),
            false, false //Berarti gambar/sprite tidak dibalik
        );
    }

    public void dispose() {
        texture.dispose();
    }
}
