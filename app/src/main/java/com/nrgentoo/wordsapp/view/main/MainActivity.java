package com.nrgentoo.wordsapp.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.nrgentoo.wordsapp.R;

public class MainActivity extends AppCompatActivity {

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

        // inflate views
        bt_start = (Button) findViewById(R.id.bt_start);
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
        Toast.makeText(this, "Start training", Toast.LENGTH_SHORT).show();
    }
}
