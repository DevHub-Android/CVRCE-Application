package com.android.devhub.use.cvrceapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private static int WELCOME_TIMEOUT = 2000;
    ImageView image;
    ObjectAnimator animator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image = findViewById(R.id.imageView);
        animator = ObjectAnimator.ofFloat(image, View.ALPHA,0.0f,1.0f);
        animator.setDuration(2000);
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), webHome_mainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                finish();
            }
        }, WELCOME_TIMEOUT);}
    }

