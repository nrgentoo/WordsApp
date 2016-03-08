package com.nrgentoo.wordsapp.view.training;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.model.WordTask;
import com.nrgentoo.wordsapp.view.answercard.AnswerCardFragment;
import com.nrgentoo.wordsapp.view.common.AbstractActivity;
import com.nrgentoo.wordsapp.view.finishcard.FinishCardFragment;
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

    private ProgressBar progress;
    private ScrollView sv_content;
    private View training_progress;
    private View total_progress;

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // inject
        getComponent().inject(this);

        // inflate views
        progress = (ProgressBar) findViewById(R.id.progress);
        sv_content = (ScrollView) findViewById(R.id.sv_content);
        training_progress = findViewById(R.id.training_progress);
        total_progress = findViewById(R.id.total_progress);

        // init presenter
        presenter.onCreate();

        // get data
        presenter.getWordTasks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // destroy presenter
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // finish training
        presenter.finishTraining();
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

    @Override
    public void showFinishCard() {
        // show finish fragment
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.container, new FinishCardFragment())
                .commit();
    }

    @Override
    public void setTrainingProgress(int progress, int total) {
        training_progress.post(() -> {
            float ratio = progress / (float) (total);
            int width = (int) (total_progress.getWidth() * ratio);
            FrameLayout.LayoutParams param = new FrameLayout
                    .LayoutParams(width, FrameLayout.LayoutParams.MATCH_PARENT);
            training_progress.setLayoutParams(param);
        });
    }
}
