package com.nrgentoo.wordsapp.view.training;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.view.common.AbstractActivity;

/**
 * Training activity
 */
public class TrainingActivity extends AbstractActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TrainingActivity.class);
    }

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
    }
}
