package com.nrgentoo.wordsapp.view.finishcard;

import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.FragmentComponent;
import com.nrgentoo.wordsapp.store.WordTasksStore;

import javax.inject.Inject;

/**
 * {@link FinishCardPresenter} implementation
 */
public class FinishCardPresenterImpl implements FinishCardPresenter {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    FinishCardView view;

    @Inject
    WordTasksStore wordTasksStore;

    @Inject
    Actions actions;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public FinishCardPresenterImpl(HasComponent<FragmentComponent> hasComponent) {
        // inject
        hasComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      FINISH CARD PRESENTER INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        // set score
        view.setScore(wordTasksStore.getRightAnswersCount(), wordTasksStore.getTotalWordsCount());
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void runAgain() {
        // run again action
    }
}
