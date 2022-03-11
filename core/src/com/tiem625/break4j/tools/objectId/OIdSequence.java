package com.tiem625.break4j.tools.objectId;


import java.util.concurrent.atomic.AtomicLong;

public class OIdSequence {

    private OIdSequence() {
        throw new IllegalStateException("static common sequence");
    }

    private static final AtomicLong sequenceNumbers = new AtomicLong(37L);


    public static OId nextId() {
        return OId.of(sequenceNumbers.getAndIncrement());
    }
}
