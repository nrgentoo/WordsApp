package com.nrgentoo.wordsapp.store;

import android.content.SharedPreferences;

import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.actions.Keys;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;
import com.nrgentoo.wordsapp.common.di.component.ApplicationComponent;
import com.nrgentoo.wordsapp.common.di.module.ApplicationModule;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link DisplayMetricsStore} implementation
 */
public class DisplayMetricsStoreImpl extends RxStore implements DisplayMetricsStore {

    private static final String KEY_WIDTH = "key_width";
    private static final String KEY_HEIGHT = "key_height";

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    @Named(value = ApplicationModule.PREFS_DISPLAY_METRICS)
    SharedPreferences prefs;

    private int width;
    private int height;

    // --------------------------------------------------------------------------------------------
    //      CONSTRUCTOR
    // --------------------------------------------------------------------------------------------

    public DisplayMetricsStoreImpl(HasComponent<ApplicationComponent> hasComponent,
                                   Dispatcher dispatcher) {
        super(dispatcher);

        hasComponent.getComponent().inject(this);
    }

    // --------------------------------------------------------------------------------------------
    //      DISPLAY METRICS INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public int getDisplayWidth() {
        if (width == 0) {
            width = prefs.getInt(KEY_WIDTH, 0);
        }
        return width;
    }

    @Override
    public int getDisplayHeight() {
        if (height == 0) {
            height = prefs.getInt(KEY_HEIGHT, 0);
        }

        return height;
    }

    // --------------------------------------------------------------------------------------------
    //      RX STORE INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.SAVE_DISPLAY_METRICS:
                // save display metrics
                int width = action.get(Keys.PARAM_DISPLAY_WIDTH);
                int height = action.get(Keys.PARAM_DISPLAY_HEIGHT);
                saveMetrics(width, height);
                break;
            default:
                return;
        }

        postChange(new RxStoreChange(ID, action));
    }

    // --------------------------------------------------------------------------------------------
    //      PRIVATE METHODS
    // --------------------------------------------------------------------------------------------

    private void saveMetrics(int width, int height) {
        this.width = width;
        this.height = height;

        prefs.edit()
                .putInt(KEY_WIDTH, width)
                .putInt(KEY_HEIGHT, height)
                .apply();
    }
}
