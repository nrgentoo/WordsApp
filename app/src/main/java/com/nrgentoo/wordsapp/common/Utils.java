package com.nrgentoo.wordsapp.common;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Utils class
 */
public class Utils {

    public static <T> List<T> pickNRandom(List<T> lst, int n) {
        List<T> copy = new LinkedList<T>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }
}
