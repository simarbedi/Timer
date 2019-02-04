package com.example.simarbedi.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView mEditText;
    SeekBar mSeekBar;
    Button mButton;
    CountDownTimer countDownTimer;
    boolean timerOn = false;

    public void resetTimer()
    {
        mSeekBar.setEnabled(true);
        mButton.setText("GO");
        mSeekBar.setProgress(30);
        mEditText.setText("00:30");
        countDownTimer.cancel();
        timerOn=false;
    }
    public void Click2(View view) {

        if (timerOn) {
            resetTimer();

        } else {
            timerOn = true;
            mSeekBar.setEnabled(false);
            mButton.setText("STOP");
            countDownTimer = new CountDownTimer(mSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                    Log.i("info", "messages" + mSeekBar.getProgress());
                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.air_horn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int i)
    {
        int minutes = i/60;
        int seconds = i%60;

        String second_string = Integer.toString(seconds);

        if(second_string.equals("0"))
        {
            second_string = "00";
        }

        mEditText.setText(Integer.toString(minutes)+":"+second_string);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.mTextView);
        mSeekBar = findViewById(R.id.mSeekBar);
        mButton = findViewById(R.id.mButton);

        mSeekBar.setMax(600);
        mSeekBar.setProgress(30);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
