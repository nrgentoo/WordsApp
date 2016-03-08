package com.nrgentoo.wordsapp.view.task;

/**
 * Task presenter
 */
public interface TaskPresenter {

    void onCreate();

    void onDestroy();

    void checkAnswer(String answer);

    void dontRemember();
}
