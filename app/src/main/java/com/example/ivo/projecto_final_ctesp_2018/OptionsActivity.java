package com.example.ivo.projecto_final_ctesp_2018;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.ivo.projecto_final_ctesp_2018.Dialogs.dialog_notas;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);
        //Aplicação LandScape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Aplicacao sempre com ecra ligado
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Fonte personalizada
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/krunch_bold.ttf");

        //GETTER E SETTER
        final MyApp app = MyApp.getInstance();
        final String prof = app.getRowId_prof();







        //Declaração componentes IP
        TextView txt_ip  = (TextView) findViewById(R.id.txt_ip);
        txt_ip .setTypeface(custom_font);
        final ImageButton img_ip = (ImageButton) findViewById(R.id.img_ip);
        final TextView txt_change_ip  = (TextView)  findViewById(R.id.txt_change_ip);
        txt_change_ip .setTypeface(custom_font);

        //Declaração componentes sensor - acelarador
        TextView txt_acelarador_apres_off  = (TextView) findViewById(R.id.txt_acelarador_apres);
        txt_acelarador_apres_off .setTypeface(custom_font);
        //Declaração componentes sensor - acelarador OFF
        final ImageButton img_acelarador_off = (ImageButton) findViewById(R.id.img_acelarador_off);
        final TextView txt_acelarador_off  = (TextView)  findViewById(R.id.txt_acelarador_off);
        txt_acelarador_off .setTypeface(custom_font);
        //Declaração componentes sensor - acelarador ON
        final ImageButton img_acelarador_on = (ImageButton) findViewById(R.id.img_acelarador_on);
        final TextView txt_acelarador_on  = (TextView)  findViewById(R.id.txt_acelarador_on);
        txt_acelarador_on.setTypeface(custom_font);

        //Declaração componentes sensor - travao
        TextView txt_travao_apres_on = (TextView) findViewById(R.id.txt_travao_apres);
        txt_travao_apres_on.setTypeface(custom_font);
        //Declaração componentes sensor - travao OFF
        final ImageButton img_travao_off = (ImageButton) findViewById(R.id.img_travao_off);
        final TextView txt_travao_off  = (TextView)  findViewById(R.id.txt_travao_off);
        txt_travao_off .setTypeface(custom_font);
        //Declaração componentes sensor - travao ON
        final ImageButton img_travao_on = (ImageButton) findViewById(R.id.img_travao_on);
        final TextView txt_travao_on  = (TextView)  findViewById(R.id.txt_travao_on);
        txt_travao_on.setTypeface(custom_font);

        //Declaração componentes luzes
        TextView txt_luzes_apres = (TextView)  findViewById(R.id.txt_luzes_apres);
        txt_luzes_apres.setTypeface(custom_font);
        //Declaração componentes medios OFF
        final ImageButton img_medios_off = (ImageButton) findViewById(R.id.img_medios_off);
        final TextView txt_medios_off = (TextView)  findViewById(R.id.txt_medios_off);
        txt_medios_off .setTypeface(custom_font);
        //Declaração componentes medios ON
        final ImageButton img_medios_on = (ImageButton) findViewById(R.id.img_medios_on);
        final TextView txt_medios_on  = (TextView)  findViewById(R.id.txt_medios_on);
        txt_medios_on.setTypeface(custom_font);
        //Declaração componentes maximos OFF
        final ImageButton img_maximos_off = (ImageButton) findViewById(R.id.img_maximos_off);
        final TextView txt_maximos_off = (TextView)  findViewById(R.id.txt_maximos_off);
        txt_maximos_off .setTypeface(custom_font);
        //Declaração componentes maximos ON
        final ImageButton img_maximos_on = (ImageButton) findViewById(R.id.img_maximos_on);
        final TextView txt_maximos_on = (TextView)  findViewById(R.id.txt_maximos_on);
        txt_maximos_on.setTypeface(custom_font);

        //Declaração JoyStick - Marcha
        TextView txt_joy_marcha_apres = (TextView)  findViewById(R.id.txt_joy_marcha_apres);
        txt_joy_marcha_apres.setTypeface(custom_font);
        //Declaração JoyStick - Marcha DOWN
        final ImageButton img_joy_marcha_down = (ImageButton) findViewById(R.id.img_joy_marcha_down);
        final TextView txt_joy_marcha_down = (TextView)  findViewById(R.id.txt_joy_marcha_down);
        txt_joy_marcha_down .setTypeface(custom_font);
        //Declaração JoyStick - Marcha UP
        final ImageButton img_joy_marcha_up = (ImageButton) findViewById(R.id.img_joy_marcha_up);
        final TextView txt_joy_marcha_up  = (TextView)  findViewById(R.id.txt_joy_marcha_up);
        txt_joy_marcha_up.setTypeface(custom_font);
        //Declaração JoyStick - Marcha NORMAL
        final ImageButton img_joy_marcha_normal = (ImageButton) findViewById(R.id.img_joy_marcha_normal);
        final TextView txt_joy_marcha_normal = (TextView)  findViewById(R.id.txt_joy_marcha_normal);
        txt_joy_marcha_normal .setTypeface(custom_font);

        //Declaração JoyStick - Direcao
        TextView txt_joy_direcao_apres = (TextView)  findViewById(R.id.txt_joy_direcao_apres);
        txt_joy_direcao_apres.setTypeface(custom_font);
        //Declaração JoyStick - Direcao LEFT
        final ImageButton img_joy_direcao_left = (ImageButton) findViewById(R.id.img_joy_direcao_left);
        final TextView txt_joy_direcao_left = (TextView)  findViewById(R.id.txt_joy_direcao_left);
        txt_joy_direcao_left .setTypeface(custom_font);
        //Declaração JoyStick - Direcao Right
        final ImageButton img_joy_direcao_right = (ImageButton) findViewById(R.id.img_joy_direcao_right);
        final TextView txt_joy_direcao_right  = (TextView)  findViewById(R.id.txt_joy_direcao_right);
        txt_joy_direcao_right.setTypeface(custom_font);
        //Declaração JoyStick - Direcao NORMAL
        final ImageButton img_joy_direcao_normal = (ImageButton) findViewById(R.id.img_joy_direcao_normal);
        final TextView txt_joy_direcao_normal = (TextView)  findViewById(R.id.txt_joy_direcao_normal);
        txt_joy_direcao_normal .setTypeface(custom_font);







        //Retrive SharedPreference
        //IP
        retrive_data("preferences_ip", txt_change_ip);
        //SENSOR - ACELARADOR
        retrive_data("preferences_acelarador_off", txt_acelarador_off);
        retrive_data("preferences_acelarador_on", txt_acelarador_on);
        //SENSOR - TRAVAO
        retrive_data("preferences_travao_off", txt_travao_off);
        retrive_data("preferences_travao_on", txt_travao_on);
        //LUZES
        retrive_data("preferences_medios_off", txt_medios_off);
        retrive_data("preferences_medios_on", txt_medios_on);
        retrive_data("preferences_maximos_off", txt_maximos_off);
        retrive_data("preferences_maximos_on", txt_maximos_on);
        //JOYSTICK - MARCHA
        retrive_data("preferences_acelarador_on", txt_joy_marcha_up);
        retrive_data("preferences_travao_on", txt_joy_marcha_down);
        retrive_data("preferences_normal_marca", txt_joy_marcha_normal);
        //JOYSTICK - DIRECAO
        retrive_data("preferences_esquerda_on", txt_joy_direcao_left);
        retrive_data("preferences_direita_on", txt_joy_direcao_right);
        retrive_data("preferences_normal_direcao", txt_joy_direcao_normal);






        //BOTOES IP
        img_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setRowId_prof("ip_on");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }

        });

        //BOTOES SENSOR ACELARADOR
        img_acelarador_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("acelarador_off");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        img_acelarador_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("acelarador_on");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });

        //BOTOES SENSOR TRAVÃO
        img_travao_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setRowId_prof("travao_off");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }

        });
        img_travao_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("travao_on");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });

        //BOTOES LUZES
        //MEDIOS
        img_medios_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("medios_on");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        img_medios_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("medios_off");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        //MAXIMOS
        img_maximos_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("maximos_on");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        img_maximos_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("maximos_off");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });




        //JOYSTICK
        //BOTOES JOYSTICK - MARCHA
        img_joy_marcha_down.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("marcha_down");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        img_joy_marcha_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setRowId_prof("marcha_up");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }

        });
        img_joy_marcha_normal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("marcha_normal");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        //BOTOES JOYSTICK - DIRECAO
        img_joy_direcao_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("direcao_left");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
        img_joy_direcao_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setRowId_prof("direcao_right");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }

        });
        img_joy_direcao_normal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                app.setRowId_prof("direcao_normal");
                dialog_notas d = new dialog_notas(OptionsActivity.this);
                d.Initialize(OptionsActivity.this);
            }
        });
    }



    private void retrive_data(String preference, TextView txt1){
        SharedPreferences preferences_acelarador_off = PreferenceManager.getDefaultSharedPreferences(this);
        String acelarqador_off = preferences_acelarador_off.getString(preference, "");
        txt1.setText(acelarqador_off);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OptionsActivity.this, index.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
