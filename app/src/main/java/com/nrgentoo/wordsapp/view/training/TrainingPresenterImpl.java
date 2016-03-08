package com.nrgentoo.wordsapp.view.training;

import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;

import javax.inject.Inject;

/**
 * {@link TrainingPresenter} implementation
 */
public class TrainingPresenterImpl implements TrainingPresenter {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    TrainingView view;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public TrainingPresenterImpl(HasComponent<ActivityComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      TRAINING PRESENTER INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void getWordTasks() {
        view.showProgress();
    }
}
