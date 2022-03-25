package com.tiem625.break4j.gdx.ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.tools.AssetsLoader;

public class BallGdxRender extends Actor {

    private final static String BALL_TEXTURE_PATH = "ball.png";

    private final Ball model;
    private final Texture ballTexture;

    public BallGdxRender(Ball model) {
        this.model = model;
        this.ballTexture = AssetsLoader
                .loadInternalDisposable(BALL_TEXTURE_PATH, Texture::new)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        var currentPosition = new Vector2(getX(), getY());
        var newPosition = currentPosition.mulAdd(model.velocity().asVector(), delta);
        setPosition(newPosition.x, newPosition.y);
    }
}
