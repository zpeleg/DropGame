package com.pgames.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Rain implements IDrawable, IUpdatable {

    Array<Rectangle> visibleRaindrops;
    private Texture raindropTexture;
    private Bucket bucket;
    private Sound dropSound;
    private Score score;
    long lastDropTime;

    public Rain(Texture raindropTexture, Bucket bucket, Sound dropSound,Score score) {
        this.raindropTexture = raindropTexture;
        this.bucket = bucket;
        this.dropSound = dropSound;
        this.score = score;
        visibleRaindrops = new Array<Rectangle>();
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (Rectangle drop : visibleRaindrops) {
            batch.draw(raindropTexture, drop.x, drop.y);
        }
    }

    @Override
    public void update(OrthographicCamera camera) {
        AdvanceRaindrops();

        spawnRainDropIfTimeMatch();
    }


    private void spawnRainDropIfTimeMatch() {
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            Rectangle drop = new Rectangle(MathUtils.random(0, 800 - 64), 480, 64, 64);
            visibleRaindrops.add(drop);
            lastDropTime = TimeUtils.nanoTime();
        }
    }

    private void AdvanceRaindrops() {
        Iterator<Rectangle> iterator = visibleRaindrops.iterator();
        while (iterator.hasNext()) {
            Rectangle drop = iterator.next();
            drop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (drop.y + 64 < 0) {
                iterator.remove();
                score.IncreaseScore(-1);
            }
            if (drop.overlaps(bucket.getRectangle())) {
                dropSound.play();
                iterator.remove();
                score.IncreaseScore(2);
            }
        }
    }
}
