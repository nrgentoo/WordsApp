package com.nrgentoo.wordsapp.view.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nrgentoo.wordsapp.App;
import com.nrgentoo.wordsapp.common.di.HasComponent;
import com.nrgentoo.wordsapp.common.di.component.ActivityComponent;
import com.nrgentoo.wordsapp.common.di.component.DaggerFragmentComponent;
import com.nrgentoo.wordsapp.common.di.component.FragmentComponent;
import com.nrgentoo.wordsapp.common.di.module.ActivityModule;
import com.nrgentoo.wordsapp.common.di.module.FragmentModule;

import icepick.Icepick;

/**
 * Abstract fragment
 */
abstract public class AbstractFragment extends Fragment implements HasComponent<FragmentComponent> {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    private FragmentComponent fragmentComponent;

    // --------------------------------------------------------------------------------------------
    //      HAS COMPONENT INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public FragmentComponent getComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .activityModule(new ActivityModule((AbstractActivity) getActivity()))
                    .applicationComponent(App.getInstance().getComponent())
                    .build();
        }

        return fragmentComponent;
    }

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
}
