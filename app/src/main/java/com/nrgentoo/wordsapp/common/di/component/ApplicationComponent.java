package com.nrgentoo.wordsapp.common.di.component;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.common.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);
}
