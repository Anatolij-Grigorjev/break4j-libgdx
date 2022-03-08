package com.tiem625.break4j.gdx.bricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.tools.AssetsLoader;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

public class BrickGdxRender extends Actor {

    public static final ObjectSize BRICK_ONSCREEN_SIZE = ObjectSize.widthAndHeight(90, 90);
    private static final String BRICK_TEXTURE_PATH = "brick.png";

    private final Texture brickTexture;
    private final SimpleBrick model;

    public BrickGdxRender(SimpleBrick model, Color brickColor) {
        setColor(verifiedNotNull(brickColor));
        setSize(BRICK_ONSCREEN_SIZE.getWidth(), BRICK_ONSCREEN_SIZE.getHeight());
        brickTexture = AssetsLoader
                .loadInternalDisposable(BRICK_TEXTURE_PATH, Texture::new)
                .orElseThrow(IllegalStateException::new);
        this.model = verifiedNotNull(model);
    }

    public Texture brickTexture() {
        return brickTexture;
    }

    public ObjectSize brickSize() {
        return BRICK_ONSCREEN_SIZE;
    }
}
