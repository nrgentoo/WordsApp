package com.nrgentoo.wordsapp.common.di.module;

import com.nrgentoo.wordsapp.view.common.AbstractFragment;
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
}
