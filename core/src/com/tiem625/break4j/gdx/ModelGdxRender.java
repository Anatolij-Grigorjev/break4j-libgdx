package com.tiem625.break4j.gdx;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tiem625.break4j.ObjectSize;
import com.tiem625.break4j.ScreenPosition;
import com.tiem625.break4j.model.ObjectWithId;

import static com.tiem625.break4j.tools.Verifiers.verifiedNotNull;

abstract public class ModelGdxRender<T extends ObjectWithId> extends Actor {

    protected final T model;

    public ModelGdxRender(T model) {
        this.model = verifiedNotNull(model);
    }

    public Rectangle bounds() {
        var position = position();
        var size = size();
        return new Rectangle(position.x(), position().y(), size.width(), size.height());
    }

    public Rectangle globalBounds() {
        Vector2 screenPos = localToStageCoordinates(position().asVector());
        var size = size();
        return new Rectangle(screenPos.x, screenPos.y, size.width(), size.height());
    }

    public ObjectSize size() {
        return ObjectSize.widthAndHeight(getWidth(), getHeight());
    }

    public ScreenPosition position() {
        return ScreenPosition.at(getX(), getY());
    }

    public T getModel() {
        return model;
    }
}
