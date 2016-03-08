package com.nrgentoo.wordsapp.view.answercard;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.view.common.AbstractFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Answer card fragment
 */
public class AnswerCardFragment extends AbstractFragment implements AnswerCardView {

    // --------------------------------------------------------------------------------------------
    //      FIELDS
    // --------------------------------------------------------------------------------------------

    @Inject
    AnswerCardPresenter presenter;

    // --------------------------------------------------------------------------------------------
    //      UI REFERENCES
    // --------------------------------------------------------------------------------------------

    private TextView tv_word;
    private TextView tv_translation;
    private ImageView iv_picture;
    private Button bt_next;

    // --------------------------------------------------------------------------------------------
    //      LIFECYCLE
    // --------------------------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer_card, container, false);

        // inject
        getComponent().inject(this);

        // inflate views
        tv_word = (TextView) view.findViewById(R.id.tv_word);
        tv_translation = (TextView) view.findViewById(R.id.tv_translation);
        iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
        bt_next = (Button) view.findViewById(R.id.bt_next);

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
        bt_next.setOnClickListener(v -> presenter.moveToNextWord());
    }

    @Override
    public void onPause() {
        super.onPause();

        // clear listeners
        bt_next.setOnClickListener(null);
    }

    // --------------------------------------------------------------------------------------------
    //      ANSWER CARD VIEW INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void setWord(String word) {
        tv_word.setText(word);
    }

    @Override
    public void setPicture(String pictureUrl) {
        Picasso.with(getContext())
                .load(Uri.parse("http:" + pictureUrl))
                .into(iv_picture);
    }

    @Override
    public void setTranslation(String translation) {
        tv_translation.setText(translation);
    }
}
