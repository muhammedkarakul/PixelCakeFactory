package com.mycompany.myngdroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import istanbul.gamelab.ngdroid.base.BaseActivity;

public class Splash extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView imageview = findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        imageview.startAnimation(animation);

        Thread timer = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent ıntent = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(ıntent);
                    finish();
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

}
