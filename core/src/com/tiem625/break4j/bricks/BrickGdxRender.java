package com.tiem625.break4j.bricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.ObjectSize;

public class BrickGdxRender extends Actor {

    public static final ObjectSize BRICK_ONSCREEN_SIZE = ObjectSize.widthAndHeight(90, 90);

    public BrickGdxRender(Color brickColor) {
        setColor(brickColor);
        setSize(BRICK_ONSCREEN_SIZE.getWidth(), BRICK_ONSCREEN_SIZE.getHeight());


    }

    public Texture brickTexture() {
        return null;
    }


    public ObjectSize brickSize() {
        return BRICK_ONSCREEN_SIZE;
    }
}
