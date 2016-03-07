package com.nrgentoo.wordsapp;

import android.app.Application;

import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ApplicationComponent;
import com.nrgentoo.wordsapp.common.di.component.DaggerApplicationComponent;
import com.nrgentoo.wordsapp.common.di.module.ApplicationModule;
import com.nrgentoo.wordsapp.store.DisplayMetricsStore;
import com.nrgentoo.wordsapp.store.DisplayMetricsStoreImpl;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Application class
 */
public class App extends Application implements HasComponent<ApplicationComponent>, RxViewDispatch {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    private static App INSTANCE;

    private ApplicationComponent applicationComponent;

    @Inject
    EventBus eventBus;

    // stores
    @Inject
    DisplayMetricsStore displayMetricsStore;

    // --------------------------------------------------------------------------------------------
    //      PUBLIC METHODS
    // --------------------------------------------------------------------------------------------

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        // inject
        getComponent().inject(this);
    }

    @Override
    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return applicationComponent;
    }

    // --------------------------------------------------------------------------------------------
    //      RX VIEW DISPATCH
    // --------------------------------------------------------------------------------------------

    @Override
    public void onRxStoreChanged(RxStoreChange change) {
        // dispatch event with change
        eventBus.post(change);
    }

    @Override
    public void onRxError(RxError error) {
        // dispatch event with error
        eventBus.post(error);
    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnRegistered() {

    }

    @Override
    public void onRxStoresRegister() {
        // register stores
        ((DisplayMetricsStoreImpl) displayMetricsStore).register();
    }
}
