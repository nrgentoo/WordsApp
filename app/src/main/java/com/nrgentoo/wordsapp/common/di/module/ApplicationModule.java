package com.nrgentoo.wordsapp.common.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.actions.ActionsCreator;
import com.nrgentoo.wordsapp.common.AppRxFlux;
import com.nrgentoo.wordsapp.network.SkyengAPI;
import com.nrgentoo.wordsapp.store.DisplayMetricsStore;
import com.nrgentoo.wordsapp.store.DisplayMetricsStoreImpl;
import com.nrgentoo.wordsapp.store.WordTasksStore;
import com.nrgentoo.wordsapp.store.WordTasksStoreImpl;

import javax.inject.Named;
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

    @Singleton
    @Provides
    Actions provideActions() {
        return new ActionsCreator(app, rxFlux.getDispatcher(), rxFlux.getSubscriptionManager());
    }

    public static final String PREFS_DISPLAY_METRICS = "display_metrics";

    @Singleton
    @Provides
    @Named(value = PREFS_DISPLAY_METRICS)
    SharedPreferences provideDisplayMetricsPrefs() {
        return app.getSharedPreferences(PREFS_DISPLAY_METRICS, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    DisplayMetricsStore provideDisplayMetricsStore() {
        return new DisplayMetricsStoreImpl(app, rxFlux.getDispatcher());
    }

    @Singleton
    @Provides
    WordTasksStore provideWordTasksStore() {
        return new WordTasksStoreImpl(rxFlux.getDispatcher());
    }
}
