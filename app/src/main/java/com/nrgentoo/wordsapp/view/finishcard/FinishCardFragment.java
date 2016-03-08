package com.nrgentoo.wordsapp.view.finishcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.view.common.AbstractFragment;

import javax.inject.Inject;

/**
 * Finish card with training result
 */
public class FinishCardFragment extends AbstractFragment implements FinishCardView {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    FinishCardPresenter presenter;

    // --------------------------------------------------------------------------------------------
    //      UI REFERENCES
    // --------------------------------------------------------------------------------------------

    private TextView tv_score;
    private Button bt_again;

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_card, container, false);

        // inject
        getComponent().inject(this);

        // inflate views
        tv_score = (TextView) view.findViewById(R.id.tv_score);
        bt_again = (Button) view.findViewById(R.id.bt_again);

        // init presenter
        presenter.onCreate();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // destroy presenter
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();

        // set listeners
        bt_again.setOnClickListener(v -> presenter.runAgain());
    }

    @Override
    public void onPause() {
        super.onPause();

        // clear listeners
        bt_again.setOnClickListener(null);
    }

    // --------------------------------------------------------------------------------------------
    //      FINISH CARD VIEW INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void setScore(int rightAnswers, int totalWords) {
        String score = rightAnswers + "/" + totalWords;
        tv_score.setText(score);
    }
}
