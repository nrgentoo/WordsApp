package com.nrgentoo.wordsapp.view.task;

import com.nrgentoo.wordsapp.common.Utils;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.FragmentComponent;
import com.nrgentoo.wordsapp.model.WordAlternative;
import com.nrgentoo.wordsapp.model.WordTask;
import com.nrgentoo.wordsapp.store.WordTasksStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link TaskPresenter} implementation
 */
public class TaskPresenterImpl implements TaskPresenter {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    TaskView view;

    @Inject
    WordTasksStore wordTasksStore;

    WordTask wordTask;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public TaskPresenterImpl(HasComponent<FragmentComponent> hasComponent) {
        // inject
        hasComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      TASK PRESENTER INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        // get word task
        wordTask = wordTasksStore.getNext();

        // set view
        view.setWord(wordTask.text);

        // set answers
        List<WordAlternative> randomAlternatives = Utils.pickNRandom(wordTask.alternatives, 3);

        List<String> answers = new ArrayList<>();
        answers.add(wordTask.translation);
        for (int i = 0; i < 3; i++) {
            answers.add(randomAlternatives.get(i).translation);
        }

        // shuffle answers
        Collections.shuffle(answers);

        view.setAnswers(answers);

        // play sound
        view.playSound(wordTask.soundUrl);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void checkAnswer(String answer) {

    }

    // --------------------------------------------------------------------------------------------
    //      RX FLUX EVENTS
    // --------------------------------------------------------------------------------------------
}
