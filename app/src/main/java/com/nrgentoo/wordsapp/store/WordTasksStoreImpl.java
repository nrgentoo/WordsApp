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
import java.util.Stack;

/**
 * {@link WordTasksStore} implementation
 */
public class WordTasksStoreImpl extends RxStore implements WordTasksStore {

    private static final int TOTAL_WORDS = 10;

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    private List<WordTask> wordTasks;
    private Stack<WordTask> shuffled;
    private int rightAnswersCount;

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
            return shuffled.peek();
        } else {
            return null;
        }
    }

    @Override
    public int getRightAnswersCount() {
        return rightAnswersCount;
    }

    @Override
    public int getTotalWordsCount() {
        return TOTAL_WORDS;
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
                // reset state for new training
                resetState();

                // shuffle words
                shuffleWords();
                break;
            case Actions.MOVE_TO_ANSWER:
                // update score
                boolean isRightAnswer = action.get(Keys.PARAM_IS_RIGHT_ANSWER);
                if (isRightAnswer) rightAnswersCount++;
                break;
            case Actions.MOVE_TO_NEXT_WORD:
                // pop recent word
                if (!shuffled.isEmpty()) {
                    shuffled.pop();
                }
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
        List<WordTask> shuffledList = new ArrayList<>();
        shuffledList.addAll(wordTasks);
        Collections.shuffle(shuffledList);

        shuffled = new Stack<>();
        shuffled.addAll(shuffledList.subList(0, TOTAL_WORDS));
    }

    private void resetState() {
        shuffled = null;
        rightAnswersCount = 0;
    }
}
