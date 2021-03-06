package com.nrgentoo.wordsapp.view.task;

import java.util.List;

/**
 * Task view
 */
public interface TaskView {

    void setWord(String word);

    /**
     * @param answers * List of four answers
     */
    void setAnswers(List<String> answers);

    void playSound(String soundUrl, String word);

    void setRightAnswerGreen(String answer);

    void setWrongAnswerRed(String answer);
}
