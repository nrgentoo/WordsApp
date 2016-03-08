package com.nrgentoo.wordsapp.actions;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ApplicationComponent;
import com.nrgentoo.wordsapp.network.SkyengAPI;
import com.nrgentoo.wordsapp.store.DisplayMetricsStore;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Actions creator
 */
public class ActionsCreator extends RxActionCreator implements Actions {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    SkyengAPI skyengAPI;

    @Inject
    DisplayMetricsStore displayMetricsStore;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public ActionsCreator(HasComponent<ApplicationComponent> hasApplicationComponent,
                          Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);

        // inject
        hasApplicationComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      METHODS
    // --------------------------------------------------------------------------------------------

    @Override
    public void getWords(List<Long> meaningIds) {
        RxAction action = newRxAction(GET_WORDS,
                Keys.PARAM_MEANING_IDS, meaningIds);
        if (hasRxAction(action)) return;

        int width = displayMetricsStore.getDisplayWidth();

        // convert list to comma separated string
        String meaningIdsStr = "";
        for (long id : meaningIds) {
            meaningIdsStr += id + ",";
        }
        // remove last comma
        meaningIdsStr = meaningIdsStr.replaceFirst(",$", "");

        addRxAction(action, skyengAPI.getWords(meaningIdsStr, width)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordTasks -> {
                    // set result
                    action.getData().put(Keys.RESULT_GET_WORDS, wordTasks);
                    postRxAction(action);
                    removeRxAction(action);
                }, throwable -> {
                    // post error
                    postError(action, throwable);
                    removeRxAction(action);
                }));
    }

    @Override
    public void saveDisplayMetrics(int width, int height) {
        RxAction action = newRxAction(SAVE_DISPLAY_METRICS,
                Keys.PARAM_DISPLAY_WIDTH, width,
                Keys.PARAM_DISPLAY_HEIGHT, height);

        // post action
        postRxAction(action);
    }

    @Override
    public void startTraining() {
        RxAction action = newRxAction(START_TRAINING);

        // post action
        postRxAction(action);
    }

    @Override
    public void moveToAnswer(boolean isRightAnswer) {
        RxAction action = newRxAction(MOVE_TO_ANSWER,
                Keys.PARAM_IS_RIGHT_ANSWER, isRightAnswer);

        // post action
        postRxAction(action);
    }

    @Override
    public void moveToNextWord() {
        RxAction action = newRxAction(MOVE_TO_NEXT_WORD);

        // post action
        postRxAction(action);
    }
}
