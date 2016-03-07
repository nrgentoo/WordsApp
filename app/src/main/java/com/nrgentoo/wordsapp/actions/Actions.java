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

    // --------------------------------------------------------------------------------------------
    //      METHODS
    // --------------------------------------------------------------------------------------------

    void getWords(List<Long> meaningIds);
}
