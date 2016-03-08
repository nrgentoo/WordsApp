package com.nrgentoo.wordsapp.common.di.component;

import com.nrgentoo.wordsapp.common.di.PerActivity;
import com.nrgentoo.wordsapp.common.di.module.ActivityModule;
import com.nrgentoo.wordsapp.view.main.MainActivity;
import com.nrgentoo.wordsapp.view.training.TrainingActivity;
import com.nrgentoo.wordsapp.view.training.TrainingPresenterImpl;

import dagger.Component;

/**
 * Activity Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(TrainingPresenterImpl trainingPresenter);

    void inject(TrainingActivity trainingActivity);
}
