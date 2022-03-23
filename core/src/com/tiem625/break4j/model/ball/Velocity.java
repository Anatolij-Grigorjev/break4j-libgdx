package com.tiem625.break4j.model.ball;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

public class Velocity {

    private final Vector2 vector;

    public static Velocity inert() {
        return new Velocity(new Vector2());
    }

    public static Velocity ofVector(Vector2 vector) {
        if (vector == null) {
            throw new IllegalArgumentException("velocity vector cant be null");
        }
        return new Velocity(vector);
    }

    private Velocity(Vector2 vector) {
        this.vector = vector;
    }

    public void increaseBy(Vector2 impulse) {
        vector.add(impulse);
    }

    public boolean isInert() {
        return Vector2.Zero.equals(vector);
    }

    public Vector2 direction() {
        return new Vector2(Math.signum(vector.x), Math.signum(vector.y));
    }

    public Vector2 asVector() {
        return new Vector2(vector);
    }

    public void bounce(Vector2 surfaceNormal) {
        //   𝑟=𝑑−2(𝑑⋅𝑛)𝑛
        var scalarCoef = 2 * surfaceNormal.dot(vector);
        var substrVector = new Vector2(surfaceNormal.x, surfaceNormal.y).scl(scalarCoef);
        vector.set(vector.sub(substrVector));
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "vector=" + vector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return vector.equals(velocity.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector);
    }
}
