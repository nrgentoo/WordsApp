package com.nrgentoo.wordsapp.actions;

import java.util.List;

/**
 * All application actions
 */
public interface Actions {

    // --------------------------------------------------------------------------------------------
    //      ACTION STRINGS
    // --------------------------------------------------------------------------------------------

    String GET_WORDS = "get_words";
    String SAVE_DISPLAY_METRICS = "save_display_metrics";

    // --------------------------------------------------------------------------------------------
    //      METHODS
    // --------------------------------------------------------------------------------------------

    void getWords(List<Long> meaningIds);

    void saveDisplayMetrics(int width, int height);
}
