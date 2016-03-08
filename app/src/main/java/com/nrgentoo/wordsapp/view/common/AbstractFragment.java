package com.nrgentoo.wordsapp.view.common;

import android.support.v4.app.Fragment;

import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;

/**
 * Abstract fragment
 */
abstract public class AbstractFragment extends Fragment implements HasComponent<ActivityComponent> {

    // --------------------------------------------------------------------------------------------
    //      HAS COMPONENT INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public ActivityComponent getComponent() {
        ActivityComponent component = null;
        if (getActivity() instanceof HasComponent) {
            component = (ActivityComponent) ((HasComponent) getActivity()).getComponent();
        }

        return component;
    }
}
