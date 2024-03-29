package com.infinitymine.arcade.system.util;

/**
 * @author zBackDo_or_
 */
public final class Preconditions {

    private Preconditions() {}

    public static <T> T nonNull(T reference, String string) {
        if (reference == null) {
            throw new NullPointerException(string);
        }

        return reference;
    }

    public static <T> T nonNull(T reference) {
        return nonNull(reference, "reference cannot be null.");
    }
}