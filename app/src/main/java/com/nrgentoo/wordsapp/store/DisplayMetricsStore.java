package com.nrgentoo.wordsapp.store;

/**
 * Display metrics store interface
 */
public interface DisplayMetricsStore {

    String ID = "DisplayMetricsStore";

    int getDisplayWidth();

    int getDisplayHeight();
}
