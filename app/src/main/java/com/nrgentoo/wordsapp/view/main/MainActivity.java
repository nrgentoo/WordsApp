package com.nrgentoo.wordsapp.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.Toast;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.actions.Actions;
import com.nrgentoo.wordsapp.view.common.AbstractActivity;
import com.nrgentoo.wordsapp.view.training.TrainingActivity;

import javax.inject.Inject;

public class MainActivity extends AbstractActivity {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    Actions actions;

    // --------------------------------------------------------------------------------------------
    //      UI REFERENCES
    // --------------------------------------------------------------------------------------------

    private Button bt_start;

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject this
        getComponent().inject(this);

        // inflate views
        bt_start = (Button) findViewById(R.id.bt_start);

        // get display width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        actions.saveDisplayMetrics(width, height);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // set listeners
        bt_start.setOnClickListener(v -> startTraining());
    }

    @Override
    protected void onPause() {
        super.onPause();

        // clear listeners
        bt_start.setOnClickListener(null);
    }

    // --------------------------------------------------------------------------------------------
    //      PRIVATE METHODS
    // --------------------------------------------------------------------------------------------

    private void startTraining() {
        // launch training activity
        startActivity(TrainingActivity.getCallingIntent(this));
    }
}
