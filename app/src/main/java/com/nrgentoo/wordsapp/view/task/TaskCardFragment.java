package com.nrgentoo.wordsapp.view.task;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nrgentoo.wordsapp.R;
import com.nrgentoo.wordsapp.view.common.AbstractFragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    TextToSpeech textToSpeech;

    Map<String, Button> answerButtonIdMap = new HashMap<>();

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
        bt_answer1.setOnClickListener(v -> presenter.checkAnswer(bt_answer1.getText().toString()));
        bt_answer2.setOnClickListener(v -> presenter.checkAnswer(bt_answer2.getText().toString()));
        bt_answer3.setOnClickListener(v -> presenter.checkAnswer(bt_answer3.getText().toString()));
        bt_answer4.setOnClickListener(v -> presenter.checkAnswer(bt_answer4.getText().toString()));
        bt_dont_remember.setOnClickListener(v -> presenter.dontRemember());
    }

    @Override
    public void onPause() {
        super.onPause();

        // clear listeners
        bt_answer1.setOnClickListener(null);
        bt_answer2.setOnClickListener(null);
        bt_answer3.setOnClickListener(null);
        bt_answer4.setOnClickListener(null);
        bt_dont_remember.setOnClickListener(null);
    }

    // --------------------------------------------------------------------------------------------
    //      TASK VIEW INTERFACE
    // --------------------------------------------------------------------------------------------

    @Override
    public void setWord(String word) {
        tv_word.setText(word);
    }

    @Override
    public void setAnswers(List<String> answers) {
        bt_answer1.setText(answers.get(0));
        bt_answer2.setText(answers.get(1));
        bt_answer3.setText(answers.get(2));
        bt_answer4.setText(answers.get(3));

        // map buttons to answers
        answerButtonIdMap.put(answers.get(0), bt_answer1);
        answerButtonIdMap.put(answers.get(1), bt_answer2);
        answerButtonIdMap.put(answers.get(2), bt_answer3);
        answerButtonIdMap.put(answers.get(3), bt_answer4);
    }

    @Override
    public void playSound(String soundUrl, String word) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getContext(), Uri.parse("http:" + soundUrl));
            mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            // play with text-to-speech
            speakOut(word);
        }
    }

    @Override
    public void setRightAnswerGreen(String answer) {
        Button button = answerButtonIdMap.get(answer);
        setBackground(button, R.drawable.bg_button_green);
    }

    @Override
    public void setWrongAnswerRed(String answer) {
        Button button = answerButtonIdMap.get(answer);
        setBackground(button, R.drawable.bg_button_red);
    }

    // --------------------------------------------------------------------------------------------
    //      PRIVATE METHODS
    // --------------------------------------------------------------------------------------------

    private void speakOut(String word) {
        // init textToSpeech
        textToSpeech = new TextToSpeech(getContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    //noinspection deprecation
                    textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    private void setBackground(View view, int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(ContextCompat.getDrawable(getContext(),
                    drawableId));
        } else {
            //noinspection deprecation
            view.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),
                    drawableId));
        }
    }
}
