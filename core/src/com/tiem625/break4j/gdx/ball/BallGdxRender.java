package com.tiem625.break4j.gdx.ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.gdx.ModelGdxRender;
import com.tiem625.break4j.gdx.bricks.BrickGdxRender;
import com.tiem625.break4j.model.ball.Ball;
import com.tiem625.break4j.model.bricks.BrickSide;
import com.tiem625.break4j.tools.AssetsLoader;
import com.tiem625.break4j.tools.Verifiers;

import java.util.Optional;

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


    public Optional<BrickSide> checkCollisionWith(BrickGdxRender brickRender) {
        Verifiers.verifiedNotNull(brickRender);

        var brickBounds = brickRender.bounds();
        if (bounds().overlaps(brickBounds)) {

            return Optional.of(getCollisionSideWith(brickBounds));
        } else {

            return Optional.empty();
        }
    }

    private BrickSide getCollisionSideWith(Rectangle brickBounds) {

        var bottomLeft = position();
        var topRight = position().offsetBy(BALL_ONSCREEN_SIZE);

        var brickBottomLeft = ScreenPosition.at(brickBounds.getX(), brickBounds.getY());
        var brickTopRight = ScreenPosition.at(brickBounds.x + brickBounds.width, brickBounds.y + brickBounds.height);

        throw new UnsupportedOperationException("TODO");
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
