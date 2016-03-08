package com.nrgentoo.wordsapp.view.common;

import android.support.v7.app.AppCompatActivity;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;
import com.nrgentoo.wordsapp.common.di.component.DaggerActivityComponent;
import com.nrgentoo.wordsapp.common.di.module.ActivityModule;

/**
 *Base activity
 */
abstract public class AbstractActivity extends AppCompatActivity
        implements HasComponent<ActivityComponent> {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    private ActivityComponent activityComponent;

    // --------------------------------------------------------------------------------------------
    //      HAS COMPONENT INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public ActivityComponent getComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(App.getInstance().getComponent())
                    .build();
        }

        return activityComponent;
    }
}
