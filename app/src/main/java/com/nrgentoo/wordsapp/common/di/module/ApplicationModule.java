package com.nrgentoo.wordsapp.common.di.module;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.common.AppRxFlux;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Application module
 */
@Module
public class ApplicationModule {

    private final App app;
    private final AppRxFlux rxFlux;

    public ApplicationModule(App app) {
        this.app = app;
        rxFlux = AppRxFlux.init(app);
    }

    @Singleton
    @Provides
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
