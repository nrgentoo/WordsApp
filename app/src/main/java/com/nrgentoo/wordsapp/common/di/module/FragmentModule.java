package com.nrgentoo.wordsapp.common.di.module;

import com.nrgentoo.wordsapp.view.answercard.AnswerCardPresenter;
import com.nrgentoo.wordsapp.view.answercard.AnswerCardPresenterImpl;
import com.nrgentoo.wordsapp.view.answercard.AnswerCardView;
import com.nrgentoo.wordsapp.view.common.AbstractFragment;
import com.nrgentoo.wordsapp.view.finishcard.FinishCardPresenter;
import com.nrgentoo.wordsapp.view.finishcard.FinishCardPresenterImpl;
import com.nrgentoo.wordsapp.view.finishcard.FinishCardView;
import com.nrgentoo.wordsapp.view.task.TaskPresenter;
import com.nrgentoo.wordsapp.view.task.TaskPresenterImpl;
import com.nrgentoo.wordsapp.view.task.TaskView;

import dagger.Module;
import dagger.Provides;

/**
 * Fragment module
 */
@Module
public class FragmentModule {

    private AbstractFragment fragment;

    public FragmentModule(AbstractFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    TaskPresenter provideTaskPresenter() {
        return new TaskPresenterImpl(fragment);
    }

    @Provides
    TaskView provideTaskView() {
        if (fragment instanceof TaskView) {
            return (TaskView) fragment;
        } else {
            throw new IllegalStateException(TaskView.class.getSimpleName() + " can be provided" +
                    " only from fragment implemented that interface");
        }
    }

    @Provides
    AnswerCardPresenter provideAnswerCardPresenter() {
        return new AnswerCardPresenterImpl(fragment);
    }

    @Provides
    AnswerCardView provideAnswerCardView() {
        if (fragment instanceof AnswerCardView) {
            return (AnswerCardView) fragment;
        } else {
            throw new IllegalStateException(AnswerCardView.class.getSimpleName() + " can be provided" +
                    " only from fragment implemented that interface");
        }
    }

    @Provides
    FinishCardPresenter provideFinishCardPresenter() {
        return new FinishCardPresenterImpl(fragment);
    }

    @Provides
    FinishCardView provideFinishCardView() {
        if (fragment instanceof FinishCardView) {
            return (FinishCardView) fragment;
        } else {
            throw new IllegalStateException(FinishCardView.class.getSimpleName() + " can be provided" +
                    " only from fragment implemented that interface");
        }
    }
}
