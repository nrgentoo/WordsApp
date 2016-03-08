package com.nrgentoo.wordsapp.common.di.component;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.actions.ActionsCreator;
import com.nrgentoo.wordsapp.common.di.module.ApplicationModule;
import com.nrgentoo.wordsapp.store.DisplayMetricsStoreImpl;
import com.nrgentoo.wordsapp.store.WordTasksStore;

import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;

/**
 * Application Component
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    // expose to sub-graphs
    Actions actions();

    EventBus eventBus();

    WordTasksStore wordTasksStore();

    // injections
    void inject(App app);

    void inject(ActionsCreator actionsCreator);

    void inject(DisplayMetricsStoreImpl displayMetricsStore);
}
