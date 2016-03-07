package com.nrgentoo.wordsapp.common.di.component;

import com.nrgentoo.wordsapp.common.di.PerActivity;
import com.nrgentoo.wordsapp.common.di.module.ActivityModule;

import dagger.Component;

/**
 * Activity Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}
