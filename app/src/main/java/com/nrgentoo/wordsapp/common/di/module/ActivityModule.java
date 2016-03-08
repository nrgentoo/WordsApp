package com.nrgentoo.wordsapp.common.di.module;

import android.app.Activity;
import android.content.res.Resources;

import com.nrgentoo.wordsapp.view.common.AbstractActivity;
import com.nrgentoo.wordsapp.view.training.TrainingPresenter;
import com.nrgentoo.wordsapp.view.training.TrainingPresenterImpl;
import com.nrgentoo.wordsapp.view.training.TrainingView;

import dagger.Module;
import dagger.Provides;

/**
 * Activity Module
 */
@Module
public class ActivityModule {

    private AbstractActivity activity;

    public ActivityModule(AbstractActivity activity) {
        this.activity = activity;
    }

    @Provides
    TrainingView provideTrainingView() {
        if (activity instanceof TrainingView) {
            return (TrainingView) activity;
        } else {
            throw new IllegalStateException(TrainingView.class.getSimpleName() + " can be provided" +
                    " only from activity implemented that interface");
        }
    }

    @Provides
    TrainingPresenter provideTrainingPresenter() {
        return new TrainingPresenterImpl(activity);
    }

    @Provides
    Resources provideResources() {
        return activity.getResources();
    }
}
