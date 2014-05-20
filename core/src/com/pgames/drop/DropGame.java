package com.pgames.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class DropGame extends ApplicationAdapter {
    SpriteBatch batch;
    OrthographicCamera camera;

    Texture dropSprite;
    Texture bucketSprite;

    Sound dropSound;
    Music rainMusic;


    Bucket bucket;
    Rain rain;
    Score score;

    /*
    TODO:
    [ ] Add score system
    [ ] Add more randomness into the raindrop generation and speed
    [ ] Add accelarating raindrops
    [ ] Better hit detection (though it's not that necessary...)

     */
    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        dropSprite = new Texture(Gdx.files.internal("droplet.png"));
        bucketSprite = new Texture(Gdx.files.internal("bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        score = new Score();

        bucket = new Bucket(bucketSprite);
        rain = new Rain(dropSprite, bucket, dropSound,score);

        rainMusic.setLooping(true);
        rainMusic.play();
    }

    @Override
    public void render() {
        update();
        draw();
    }

    @Override
    public void dispose() {
        dropSound.dispose();
        dropSprite.dispose();
        rainMusic.dispose();
        bucketSprite.dispose();
        batch.dispose();
    }


    private void update() {
        score.update(camera);
        rain.update(camera);
        bucket.update(camera);
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bucket.draw(batch);
        rain.draw(batch);
        score.draw(batch);

        batch.end();
    }
}
