package com.tiem625.break4j.gdx.ball;

import com.badlogic.gdx.graphics.Texture;
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
}
