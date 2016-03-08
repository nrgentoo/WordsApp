package com.nrgentoo.wordsapp.view.answercard;

import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.FragmentComponent;
import com.nrgentoo.wordsapp.model.WordTask;
import com.nrgentoo.wordsapp.store.WordTasksStore;

import javax.inject.Inject;

/**
 * {@link AnswerCardPresenter} implementation
 */
public class AnswerCardPresenterImpl implements AnswerCardPresenter {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    AnswerCardView view;

    @Inject
    WordTasksStore wordTasksStore;

    @Inject
    Actions actions;

    WordTask wordTask;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public AnswerCardPresenterImpl(HasComponent<FragmentComponent> hasComponent) {
        // inject
        hasComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      ANSWER CARD PRESENTER INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        // get word task
        wordTask = wordTasksStore.getNext();

        // set view
        view.setWord(wordTask.text);
        view.setPicture(wordTask.images.get(0));
        view.setTranslation(wordTask.translation);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void moveToNextTask() {
        // move to next task action
    }
}
