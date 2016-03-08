package com.nrgentoo.wordsapp.store;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.actions.Keys;
import com.nrgentoo.wordsapp.model.WordTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@link WordTasksStore} implementation
 */
public class WordTasksStoreImpl extends RxStore implements WordTasksStore {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    private List<WordTask> wordTasks;
    private List<WordTask> shuffled;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public WordTasksStoreImpl(Dispatcher dispatcher) {
        super(dispatcher);
    }

    // --------------------------------------------------------------------------------------------
    //      WORD TASKS STORE INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public List<WordTask> getWordTasks() {
        return wordTasks;
    }

    @Override
    public WordTask getNext() {
        if (!shuffled.isEmpty()) {
            return shuffled.get(shuffled.size() - 1);
        } else {
            return null;
        }
    }

    // --------------------------------------------------------------------------------------------
    //      RX STORE STORE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.GET_WORDS:
                wordTasks = action.get(Keys.RESULT_GET_WORDS);
                break;
            case Actions.START_TRAINING:
                // shuffle words
                shuffleWords();
                break;
            default:
                return;
        }

        postChange(new RxStoreChange(ID, action));
    }

    // --------------------------------------------------------------------------------------------
    //      PRIVATE METHODS
    // --------------------------------------------------------------------------------------------

    private void shuffleWords() {
        shuffled = new ArrayList<>();
        shuffled.addAll(wordTasks);
        Collections.shuffle(shuffled);

        shuffled = shuffled.subList(0, 10);
    }
}
