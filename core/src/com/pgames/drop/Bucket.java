package com.pgames.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bucket implements IUpdatable, IDrawable{

    Rectangle position;
    Texture texture;

    public Bucket(Texture bucketTexture)
    {
        texture = bucketTexture;
        position = new Rectangle(800 / 2 - 64 / 2, 20, 64, 64);
    }

    public Rectangle getRectangle(){
        return position;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    @Override
    public void update(OrthographicCamera camera) {
        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            position.x = touchPosition.x - 64 / 2;
        }
    }
}
