package com.tiem625.break4j.bricks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SimpleBrick extends Actor {

    private final BrickPosition brickPosition;

    public SimpleBrick(BrickPosition brickPosition, Color brickColor) {
        this.brickPosition = brickPosition;
        setColor(brickColor);
    }
}
