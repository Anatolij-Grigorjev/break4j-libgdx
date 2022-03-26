package com.tiem625.break4j.gdx.ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.gdx.ModelGdxRender;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.tools.AssetsLoader;

public class BallGdxRender extends ModelGdxRender<Ball> {

    private final static String BALL_TEXTURE_PATH = "ball.png";
    private final static ObjectSize BALL_ONSCREEN_SIZE = ObjectSize.widthAndHeight(24, 24);

    private final Texture ballTexture;

    public BallGdxRender(Ball model) {
        super(model);
        this.ballTexture = AssetsLoader
                .loadInternalDisposable(BALL_TEXTURE_PATH, Texture::new)
                .orElseThrow(IllegalStateException::new);
        setSize(BALL_ONSCREEN_SIZE.width(), BALL_ONSCREEN_SIZE.height());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        var currentPosition = new Vector2(getX(), getY());
        var newPosition = currentPosition.mulAdd(model.velocity().asVector(), delta);
        setPosition(newPosition.x, newPosition.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                ballTexture,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0,
                (int) BALL_ONSCREEN_SIZE.width(), (int) BALL_ONSCREEN_SIZE.height(),
                false, false
        );
    }
}
