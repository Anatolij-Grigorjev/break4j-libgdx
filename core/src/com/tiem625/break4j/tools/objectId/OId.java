package com.tiem625.break4j.tools.objectId;

import java.util.Objects;

public class OId {

    private final long numericId;

    public static OId of(long numericId) {
        return new OId(numericId);
    }

    private OId(long numericId) {
        this.numericId = numericId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OId oId = (OId) o;
        return numericId == oId.numericId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numericId);
    }

    @Override
    public String toString() {
        return String.valueOf(numericId);
    }
}
