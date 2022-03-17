package com.tiem625.break4j.tools;

import java.util.Optional;

public class Verifiers {

    private Verifiers() {
        throw new IllegalStateException("static helper");
    }

    public static <T> T verifiedNotNull(T arg) {
        return Optional.ofNullable(arg).orElseThrow(IllegalArgumentException::new);
    }

    public static int verifyNotNegative(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("number was below zero: " + num);
        }
        return num;
    }

    public static float verifyNotNegative(float num) {
        if (num < 0.0f) {
            throw new IllegalArgumentException("number was below zero: " + num);
        }
        return num;
    }
}
