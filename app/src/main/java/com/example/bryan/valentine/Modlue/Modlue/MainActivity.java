package com.example.bryan.valentine.Modlue.Modlue;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryan.valentine.Modlue.Services.PlayMediaService;
import com.example.bryan.valentine.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setFont();

        //create Connection to service;
        mConnectionToService = new Intent(getBaseContext(), PlayMediaService.class);
        startPlayMediaService();
        //after TimeSong, Service will stop.
        Handler delayTime = new Handler();
        delayTime.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopPlayMediaService();
            }
        }, mTimeSong);

        //animation
        animationForHearMini();
        animationForTextName();
        animationForHeartRed();
        animationForTextValentine();
        addFragmentQuestion();



    }
    private void addFragmentQuestion(){
       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               FragmentManager fragmentManager = getFragmentManager();
               FragmentTransaction transaction = fragmentManager.beginTransaction();
               QuestionFragment questionFragment =  new QuestionFragment();
               transaction.add(R.id.layout_container,questionFragment,"MyFragment");
               transaction.commit();
           }
       },5000);

    }
    private void animationForTextValentine(){
        CountDownTimer countDownTimer = new CountDownTimer(mTimeSong,3000) {
            @Override
            public void onTick(long l) {
                Animation leftToRight = AnimationUtils.loadAnimation(getBaseContext(),R.anim.left_to_right);
                mtvValentine.startAnimation(leftToRight);
            }

            @Override
            public void onFinish() {
                mtvValentine.clearAnimation();

            }
        };
        countDownTimer.start();
    }
    private void animationForHeartRed(){
        CountDownTimer countDownTimer = new CountDownTimer(mTimeSong, 6000) {
            @Override
            public void onTick(long l) {
                Animation fade_in = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_in);
                mivHeartRed.startAnimation(fade_in);
                Animation fade_out = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);
                mivHeartRed.startAnimation(fade_out);
            }

            @Override
            public void onFinish() {
                mivHeartRed.clearAnimation();

            }
        };
        countDownTimer.start();

    }
    private void animationForTextName(){
        CountDownTimer countDownTimer = new CountDownTimer(mTimeSong, 400) {
            @Override
            public void onTick(long l) {
            Animation topBottom = AnimationUtils.loadAnimation(getBaseContext(),R.anim.top_to_bottom);
            mtvNameBoy.startAnimation(topBottom);
            mtvNameGirl.startAnimation(topBottom);
            }

            @Override
            public void onFinish() {
                mtvNameGirl.clearAnimation();
                mtvNameBoy.clearAnimation();
            }
        };
        countDownTimer.start();
    }
    private void animationForHearMini() {

        CountDownTimer countDownTimer = new CountDownTimer(mTimeSong, 500) {
            @Override
            public void onTick(long l) {
                Animation zoomIn = AnimationUtils.loadAnimation(getBaseContext(), R.anim.zoom_in);
                mivHeartMini.startAnimation(zoomIn);
                Animation zoomOut = AnimationUtils.loadAnimation(getBaseContext(), R.anim.zoom_out);
                mivHeartMini.startAnimation(zoomOut);
            }

            @Override
            public void onFinish() {
            mivHeartMini.clearAnimation();

            }
        };
        countDownTimer.start();
    }
    private void startPlayMediaService() {
        startService(mConnectionToService);
        Toast.makeText(getBaseContext(), getResources().getString(R.string.start_service), Toast.LENGTH_SHORT).show();

    }
    private void stopPlayMediaService() {
        //after 4m2s call stop service;
        stopService(mConnectionToService);
        Toast.makeText(getBaseContext(), getResources().getString(R.string.stop_service), Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mivHeartMini = findViewById(R.id.image_heart_mini);
        mtvNameBoy = findViewById(R.id.text_name_boy);
        mtvNameGirl = findViewById(R.id.text_name_girl);
        mivHeartRed = findViewById(R.id.image_heart_red);
        mtvValentine = findViewById(R.id.text_valentine);
    }
    private void setFont(){
        mCustomFont = Typeface.createFromAsset(getAssets(), "fonts/fiolex_girl.ttf");
        mtvValentine.setTypeface(mCustomFont);
        mtvNameBoy.setTypeface(mCustomFont);
        mtvNameGirl.setTypeface(mCustomFont);
    }


    // region VARS
    private Intent mConnectionToService;
    //242000
    private long mTimeSong = 242000;
    private ImageView mivHeartMini;
    private TextView mtvNameBoy;
    private TextView mtvNameGirl;
    private ImageView mivHeartRed;
    private Typeface mCustomFont;
    private TextView mtvValentine;

    //endregion
}
