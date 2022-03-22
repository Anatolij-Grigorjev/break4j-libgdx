package com.tiem625.break4j.model.bricks;

import com.badlogic.gdx.math.Vector2;

public enum BrickSide {

    TOP(new Vector2(0, 1)),
    BOTTOM(new Vector2(0, -1)),
    LEFT(new Vector2(-1, 0)),
    RIGHT(new Vector2(1, 0))
    ;

    private final Vector2 surfaceNormal;

    BrickSide(Vector2 surfaceNormal) {
        this.surfaceNormal = surfaceNormal;
    }

    public Vector2 surfaceNormal() {
        return surfaceNormal;
    }
}
