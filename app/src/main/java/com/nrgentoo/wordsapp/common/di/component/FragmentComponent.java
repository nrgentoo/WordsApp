package com.nrgentoo.wordsapp.common.di.component;

import com.nrgentoo.wordsapp.common.di.PerFragment;
import com.nrgentoo.wordsapp.common.di.module.ActivityModule;
import com.nrgentoo.wordsapp.common.di.module.FragmentModule;
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
}
