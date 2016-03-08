package com.nrgentoo.wordsapp.common.di.component;

import com.nrgentoo.wordsapp.common.di.PerFragment;
import com.nrgentoo.wordsapp.common.di.module.ActivityModule;
import com.nrgentoo.wordsapp.common.di.module.FragmentModule;
import com.nrgentoo.wordsapp.view.answercard.AnswerCardFragment;
import com.nrgentoo.wordsapp.view.answercard.AnswerCardPresenterImpl;
import com.nrgentoo.wordsapp.view.finishcard.FinishCardFragment;
import com.nrgentoo.wordsapp.view.finishcard.FinishCardPresenterImpl;
import com.nrgentoo.wordsapp.view.task.TaskCardFragment;
import com.nrgentoo.wordsapp.view.task.TaskPresenterImpl;

import dagger.Component;

/**
 * Fragment component
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,
        modules = {FragmentModule.class, ActivityModule.class})
public interface FragmentComponent {

    void inject(TaskPresenterImpl taskPresenter);

    void inject(TaskCardFragment taskCardFragment);

    void inject(AnswerCardPresenterImpl answerCardPresenter);

    void inject(AnswerCardFragment answerCardFragment);

    void inject(FinishCardPresenterImpl finishCardPresenter);

    void inject(FinishCardFragment finishCardFragment);
}
