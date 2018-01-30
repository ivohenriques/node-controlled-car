package com.example.ivo.projecto_final_ctesp_2018;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.sleep;
import static java.security.AccessController.getContext;

public class index extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_index);
        //Aplicação LandScape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Aplicacao sem com ecra ligado
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Fonte personalizada
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/krunch_bold.ttf");


        //declaracao de componentes
        final Button btn_wifi = (Button) findViewById(R.id.btn_wifi) ;
        final Button btn_options = (Button)findViewById(R.id.btn_options);
        final Button btn_controller_joy = (Button)findViewById(R.id.btn_cont_joy);
        final Button btn_controller_sen = (Button)findViewById(R.id.btn_cont_sen);

        //botoes
        btn_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        btn_controller_sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(index.this, MainActivity.class));
            }
        });
        btn_controller_joy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(index.this, JoyStickActivity.class));
            }
        });
        btn_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(index.this, OptionsActivity.class));
            }
        });

        btn_wifi.setTypeface(custom_font);
        btn_controller_sen.setTypeface(custom_font);
        btn_options.setTypeface(custom_font);
        btn_controller_joy.setTypeface(custom_font);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(getApplicationContext(), "Entrou",Toast.LENGTH_LONG).show();
        //wifi();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
