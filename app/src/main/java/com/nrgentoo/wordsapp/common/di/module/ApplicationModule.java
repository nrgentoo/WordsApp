package com.nrgentoo.wordsapp.common.di.module;

import com.nrgentoo.wordsapp.App;

import dagger.Module;

/**
 * Application module
 */
@Module
public class ApplicationModule {

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }
}
