package com.nrgentoo.wordsapp.view.task;

import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.FragmentComponent;
import com.nrgentoo.wordsapp.store.WordTasksStore;

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
    public void checkAnswer(String answer) {

    }

    // --------------------------------------------------------------------------------------------
    //      RX FLUX EVENTS
    // --------------------------------------------------------------------------------------------
}
