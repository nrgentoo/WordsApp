package com.nrgentoo.wordsapp.view.training;

import com.nrgentoo.wordsapp.model.WordTask;

/**
 * Training view
 */
public interface TrainingView {

    void showProgress();

    void hideProgress();

    void nextTask();

    void showAnswerCard();

    void showFinishCard();

    void setTrainingProgress(int progress, int total);
}
