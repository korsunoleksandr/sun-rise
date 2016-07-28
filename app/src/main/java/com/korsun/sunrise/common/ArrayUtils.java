package com.korsun.sunrise.common;

/**
 * Created by okorsun on 28.07.16.
 */
public class ArrayUtils {

    private ArrayUtils() {
        // no instances
    }

    public static <T> int lastIndexOf(T[] array, T item) {
        if (item == null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == item) {
                    return i;
                }
            }
        }

        return -1;
    }

}
