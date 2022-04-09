package com.tiem625.break4j.gdx.bricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.gdx.ModelGdxRender;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.tools.AssetsLoader;
import com.tiem625.break4j.tools.objectId.OId;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BrickGdxRender extends ModelGdxRender<SimpleBrick> {

    public static final ObjectSize BRICK_ONSCREEN_SIZE = ObjectSize.widthAndHeight(64, 32);
    private static final String BRICK_TEXTURE_PATH = "brick.png";

    private final Texture brickTexture;

    public BrickGdxRender(SimpleBrick model, Color brickColor) {
        super(model);
        setColor(verifiedNotNull(brickColor));
        setSize(BRICK_ONSCREEN_SIZE.width(), BRICK_ONSCREEN_SIZE.height());
        brickTexture = AssetsLoader
                .loadInternalDisposable(BRICK_TEXTURE_PATH, Texture::new)
                .orElseThrow(IllegalStateException::new);
    }

    public static BrickGdxRender renderModel(SimpleBrick model) {
        return new BrickGdxRender(model, Color.WHITE);
    }

    public Texture brickTexture() {
        return brickTexture;
    }

    public OId getModelId() {
        return model.getId();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        var brickColor = getColor();
        batch.setColor(brickColor.r, brickColor.g, brickColor.b, brickColor.a * parentAlpha);
        batch.draw(brickTexture,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0,
                (int) BRICK_ONSCREEN_SIZE.width(), (int) BRICK_ONSCREEN_SIZE.height(),
                false, false
        );
    }

    @Override
    public String toString() {
        return String.format("[%s at %s]", getModel(), localPosition());
    }
}
