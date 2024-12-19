package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Weapon {
    private float rotation;
    private Texture texture;
    private float x, y;

    public Weapon(String weaponPath){
        this.texture = new Texture(weaponPath);
        this.x = 60;
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
        this.rotation = 0;
    }

    public void rotateToEnemy(float enemyX, float enemyY){ //function untuk rotate kepada enemy
        // Calculate the center of the weapon
        float weaponCenterX = x + texture.getWidth() / 2f;
        float weaponCenterY = y + texture.getHeight() / 2f;

        // Calculate the difference between the weapon's center and the enemy's position
        float deltaX = enemyX - weaponCenterX;
        float deltaY = enemyY - weaponCenterY;

        // Calculate the rotation angle in degrees
        rotation = MathUtils.radiansToDegrees * MathUtils.atan2(deltaY, deltaX);
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
