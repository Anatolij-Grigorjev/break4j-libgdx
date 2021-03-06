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

    public Rectangle localBounds() {
        return boundsRect(localPosition(), size());
    }

    public Rectangle globalBounds() {
        return boundsRect(globalPosition(), size());
    }

    public ObjectSize size() {
        return ObjectSize.widthAndHeight(getWidth(), getHeight());
    }

    public ScreenPosition localPosition() {
        return ScreenPosition.at(getX(), getY());
    }

    public void setLocalPosition(ScreenPosition position) {
        setPosition(position.x(), position.y());
    }

    public ScreenPosition globalPosition() {
        return ScreenPosition.fromVector(localToStageCoordinates(new Vector2(getX(), getY())));
    }

    public T getModel() {
        return model;
    }


    private Rectangle boundsRect(ScreenPosition position, ObjectSize size) {
        return new Rectangle(position.x(), position.y(), size.width(), size.height());
    }
}
