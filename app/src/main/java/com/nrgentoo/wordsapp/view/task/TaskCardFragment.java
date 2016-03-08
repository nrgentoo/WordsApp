package com.nrgentoo.wordsapp.view.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.view.common.AbstractFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Task card fragment
 */
public class TaskCardFragment extends AbstractFragment implements TaskView {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    TaskPresenter presenter;

    // --------------------------------------------------------------------------------------------
    //      UI REFERENCES
    // --------------------------------------------------------------------------------------------

    private TextView tv_word;
    private Button bt_answer1, bt_answer2, bt_answer3, bt_answer4, bt_dont_remember;

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_card, container, false);

        // inject
        getComponent().inject(this);

        // inflate views
        tv_word = (TextView) view.findViewById(R.id.tv_word);
        bt_answer1 = (Button) view.findViewById(R.id.bt_answer1);
        bt_answer2 = (Button) view.findViewById(R.id.bt_answer2);
        bt_answer3 = (Button) view.findViewById(R.id.bt_answer3);
        bt_answer4 = (Button) view.findViewById(R.id.bt_answer4);
        bt_dont_remember = (Button) view.findViewById(R.id.bt_dont_remember);

        return view;
    }

    // --------------------------------------------------------------------------------------------
    //      TASK VIEW INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void setWord(String word) {

    }

    @Override
    public void setAnswers(List<String> answers) {

    }

    @Override
    public void playSound() {

    }
}
