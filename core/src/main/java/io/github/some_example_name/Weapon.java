package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Weapon {
    private Texture texture;
    private float x, y;

    public Weapon(){
        this.texture = new Texture("bow.png");
        this.x = 60;
        this.y = (Gdx.graphics.getHeight() - texture.getHeight()) / 2;
    }

    public void rotateToEnemy(){

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
