package com.nrgentoo.wordsapp.view.training;

import android.content.res.Resources;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;
import com.nrgentoo.wordsapp.store.WordTasksStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * {@link TrainingPresenter} implementation
 */
public class TrainingPresenterImpl implements TrainingPresenter {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    TrainingView view;

    @Inject
    EventBus eventBus;

    @Inject
    Actions actions;

    @Inject
    WordTasksStore wordTasksStore;

    @Inject
    Resources resources;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public TrainingPresenterImpl(HasComponent<ActivityComponent> hasComponent) {
        // inject
        hasComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      TRAINING PRESENTER INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        // register event bus
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        // unregister event bus
        eventBus.unregister(this);

        view = null;
    }

    @Override
    public void getWordTasks() {
        if (!wordTasksStore.isTrainingInProgress()) {
            view.showProgress();

            // get meaning ids
            int[] intIds = resources.getIntArray(R.array.meaning_ids);
            List<Long> meaningIds = new ArrayList<>();
            for (int id : intIds) {
                meaningIds.add((long) id);
            }

            // start action
            actions.getWords(meaningIds);
        }
    }

    @Override
    public void finishTraining() {
        // finish training
        actions.finishTraining();
    }

    // --------------------------------------------------------------------------------------------
    //      RX FLUX EVENTS
    // --------------------------------------------------------------------------------------------

    @SuppressWarnings("unused")
    public void onEvent(RxStoreChange change) {
        switch (change.getStoreId()) {
            case WordTasksStore.ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_WORDS:
                        view.hideProgress();

                        // start training
                        actions.startTraining();
                        break;
                    case Actions.START_TRAINING:
                        // show first card
                        view.nextTask();
                        break;
                    case Actions.MOVE_TO_ANSWER:
                        // show answer card
                        view.showAnswerCard();
                        break;
                    case Actions.MOVE_TO_NEXT_WORD:
                        if (wordTasksStore.getNext() != null) {
                            // show next card
                            view.nextTask();
                        } else {
                            // finish training
                            view.showFinishCard();
                        }
                        break;
                }
                break;
        }
    }

    @SuppressWarnings("unused")
    public void onEvent(RxError error) {
        switch (error.getAction().getType()) {
            case Actions.GET_WORDS:
                // show error
                break;
        }
    }
}
