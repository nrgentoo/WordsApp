package com.nrgentoo.wordsapp.store;

import com.nrgentoo.wordsapp.model.WordTask;

import java.util.List;

/**
 * Word tasks store
 */
public interface WordTasksStore {

    String ID = "WordTasksStore";

    List<WordTask> getWordTasks();

    WordTask getNext();
}
