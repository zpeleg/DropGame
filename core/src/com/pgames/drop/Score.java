package com.pgames.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by zivpe_000 on 20/05/2014.
 */
public class Score implements IUpdatable, IDrawable {

    Vector2 position;
    int score;
    BitmapFont font;

    public Score() {
        font = new BitmapFont(Gdx.files.internal("BurstMyBubble.fnt"));
    }

    public void IncreaseScore(int count) {
        score += count;
        if (score < 0) score = 0;
    }

    @Override
    public void draw(SpriteBatch batch) {
        font.draw(batch, score + " drops", 10, 470);
    }

    @Override
    public void update(OrthographicCamera camera) {
    }
}
