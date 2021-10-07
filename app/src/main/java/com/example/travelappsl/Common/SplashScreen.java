package com.example.travelappsl.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.TextureView;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.travelappsl.R;
import com.example.travelappsl.User.UserDashboard;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIMER = 2000;
//    ImageView backgroundImage;
//    TextureView poweredLine;
//
//    //animation
//    Animation sideAnim, bottomAnim;
//
//    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

//        animation
//        backgroundImage = findViewById(R.id.splash_background);
//        poweredLine = findViewById(R.id.powered_line);
//
//        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
//        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
//
//        backgroundImage.setAnimation(sideAnim);
//        poweredLine.setAnimation(bottomAnim);
//
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
            startActivity(intent);
            finish();

        },SPLASH_TIMER);


    }
}