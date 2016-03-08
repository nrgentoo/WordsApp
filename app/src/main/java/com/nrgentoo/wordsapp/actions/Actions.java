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
    String START_TRAINING = "start_training";
    String MOVE_TO_ANSWER = "move_to_answer";
    String MOVE_TO_NEXT_WORD = "move_to_next_word";

    // --------------------------------------------------------------------------------------------
    //      METHODS
    // --------------------------------------------------------------------------------------------

    void getWords(List<Long> meaningIds);

    void saveDisplayMetrics(int width, int height);

    void startTraining();

    void moveToAnswer(boolean isRightAnswer);

    void moveToNextWord();
}
