package com.tiem625.break4j.gdx;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.model.ObjectWithId;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

abstract public class ModelGdxRender<T extends ObjectWithId> extends Actor {

    protected final T model;

    public ModelGdxRender(T model) {
        this.model = verifiedNotNull(model);
    }

    public Rectangle bounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
