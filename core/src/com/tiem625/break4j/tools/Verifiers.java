package com.tiem625.break4j.tools;

import java.util.Optional;

public class Verifiers {

    private Verifiers() {
        throw new IllegalStateException("static helper");
    }

    public static <T> T verifiedNotNull(T arg) {
        return Optional.ofNullable(arg).orElseThrow(IllegalArgumentException::new);
    }
}
