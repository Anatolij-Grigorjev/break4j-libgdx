package com.tiem625.break4j.model;

import com.tiem625.break4j.tools.objectId.OId;
import com.tiem625.break4j.tools.objectId.OIdSequence;

import java.util.Objects;

public abstract class ObjectWithId {

    protected final OId id;

    protected ObjectWithId() {
        this.id = OIdSequence.nextId();
    }

    public OId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectWithId that = (ObjectWithId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity of type " + this.getClass().getName() + " with id " + id;
    }
}
