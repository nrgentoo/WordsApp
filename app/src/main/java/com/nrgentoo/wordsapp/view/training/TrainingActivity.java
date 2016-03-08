package com.nrgentoo.wordsapp.view.training;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.model.WordTask;
import com.nrgentoo.wordsapp.view.answercard.AnswerCardFragment;
import com.nrgentoo.wordsapp.view.common.AbstractActivity;
import com.nrgentoo.wordsapp.view.task.TaskCardFragment;

import javax.inject.Inject;

/**
 * Training activity
 */
public class TrainingActivity extends AbstractActivity implements TrainingView {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TrainingActivity.class);
    }

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    TrainingPresenter presenter;

    // --------------------------------------------------------------------------------------------
    //      UI REFERENCES
    // --------------------------------------------------------------------------------------------

    ProgressBar progress;
    ScrollView sv_content;

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // inject
        getComponent().inject(this);

        // init presenter
        presenter.onCreate();

        // inflate views
        progress = (ProgressBar) findViewById(R.id.progress);
        sv_content = (ScrollView) findViewById(R.id.sv_content);

        // get data
        presenter.getWordTasks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // destroy presenter
        presenter.onDestroy();
    }

    // --------------------------------------------------------------------------------------------
    //      TRAINING VIEW INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        sv_content.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
        sv_content.setVisibility(View.VISIBLE);
    }

    @Override
    public void nextTask() {
        if (getSupportFragmentManager().findFragmentById(R.id.container) == null) {
            // add first fragment without animation transition
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TaskCardFragment())
                    .commit();
        } else {
            // replace with next task fragment with animation transition
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                    .replace(R.id.container, new TaskCardFragment())
                    .commit();
        }
    }

    @Override
    public void showAnswerCard() {
        // show answer fragment
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.container, new AnswerCardFragment())
                .commit();
    }
}
