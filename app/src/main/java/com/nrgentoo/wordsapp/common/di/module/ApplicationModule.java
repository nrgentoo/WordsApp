package com.nrgentoo.wordsapp.common.di.module;

import android.util.Log;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.common.AppRxFlux;
import com.nrgentoo.wordsapp.network.SkyengAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

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

    @Singleton
    @Provides
    SkyengAPI provideSkyengAPI() {
        return new RestAdapter.Builder()
                .setEndpoint(SkyengAPI.END_POINT)
                .setLog(message -> Log.v("Retrofit", message))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build().create(SkyengAPI.class);
    }
}
