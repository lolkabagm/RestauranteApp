package com.example.kevin.restauranteapp10;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.example.kevin.restauranteapp10.BDatos.BaseDatosHelper;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 6000;
    AnimationDrawable frameAnimation;

    BaseDatosHelper miBBDDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        miBBDDHelper = new BaseDatosHelper(this);

        /**
         *  Crear base de datos y abrirla
         */
        crearBBDD();

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);
        ImageView img = (ImageView) findViewById(R.id.loadingView);
        img.setBackgroundResource(R.drawable.spinner_list);
        frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreenActivity.this, MainActivity.class);

                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }


    /*
     *  CONEXION BASE DE DATOS ******************
     */
    public void crearBBDD() {

        miBBDDHelper = new BaseDatosHelper(this);

        try {

            miBBDDHelper.crearDataBase();
            miBBDDHelper.abrirBaseDatos();
            miBBDDHelper.close();

        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }

    /*
     ****************************************
     */
}