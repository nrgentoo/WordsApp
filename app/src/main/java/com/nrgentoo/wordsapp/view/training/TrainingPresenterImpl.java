package com.nrgentoo.wordsapp.view.training;

import android.content.res.Resources;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;

import java.util.ArrayList;
import java.util.Arrays;
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
    }

    @Override
    public void getWordTasks() {
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

    // --------------------------------------------------------------------------------------------
    //      RX FLUX EVENTS
    // --------------------------------------------------------------------------------------------

    @SuppressWarnings("unused")
    public void onEvent(RxStoreChange change) {

    }

    @SuppressWarnings("unused")
    public void onEvent(RxError error) {

    }
}
