package com.example.user.recyclerview;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SongActivity extends AppCompatActivity {

    EditText Cuplet;
    EditText Pripev;
    EditText Last;
    Button playBtn;
    public SeekBar positionBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    public MediaPlayer mp;
    int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String PrimeCuplet = sharedPreferences.getString("cuplet", "");
        String PrimePripev = sharedPreferences.getString("pripev", "");
        String LastText = sharedPreferences.getString("last", "");

        Cuplet = (EditText) findViewById(R.id.Cuplet);
        Pripev = (EditText) findViewById(R.id.Pripev);
        Last = (EditText) findViewById(R.id.Last);

        String CupletText = "еуые";

        try {
            InputStream is = getAssets().open(PrimeCuplet);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            CupletText = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Cuplet.setText(CupletText);
        Pripev.setText(PrimePripev);
        Last.setText(LastText);

        playBtn = (Button) findViewById(R.id.playBtn);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimeLabel);

        mp = MediaPlayer.create(this, R.raw.angel);
        mp.setLooping(true);

        mp.seekTo(0);
        totalTime = mp.getDuration();


        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {

                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();


    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentPosition = msg.what;

            positionBar.setProgress(currentPosition);


            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime - currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {

        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) {
            timeLabel += "0";
        }
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnOnClick(View view) {

        if (!mp.isPlaying()) {
            //stopping
            mp.start();
            playBtn.setBackgroundResource(R.drawable.stop);
        } else {
            //playing
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }

    }
}