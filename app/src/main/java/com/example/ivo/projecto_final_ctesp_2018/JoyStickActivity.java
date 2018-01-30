package com.example.ivo.projecto_final_ctesp_2018;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class JoyStickActivity extends Activity {

    // how to layout the joystick
    RelativeLayout layout_joystick,layout_joystick2;
    //joystick class to figure out where the joystick is pressed.
    Joystick_Constructer js,js2;
    private Typeface custom_font;
    private int cont_d, cont_e, cont_n, situacao;
    private int cont_d1, cont_e1, cont_n1, situacao1;
    private String direcao;
    private String direcao1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_joy_stick);
        //Aplicação LandScape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Aplicacao sempre com ecra ligado
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Fonte personalizada
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/krunch_bold.ttf");
        //Info SSID
        wifi();
        //Assigning all Layout Components
        layout_joystick = findViewById(R.id.layout_joystick);
        layout_joystick2 = findViewById(R.id.layout_joystick2);
        //Setting up Joystick Class
        js = new Joystick_Constructer(getApplicationContext(), layout_joystick, R.drawable.analogicov3);
        js2 = new Joystick_Constructer(getApplicationContext(), layout_joystick2, R.drawable.analogicov3);
        //set size of inner joystick image when layout is pressed.
        js.setStickSize(175, 175);
        js2.setStickSize(175, 175);
        //sets size of joystick area.
        js.setLayoutSize(500, 500);
        js2.setLayoutSize(500, 500);
        //add joystick on start
        js.draw_off(250,250);
        js2.draw_off(250,250);
        //max inner joystick travel
        js.setOffset(175);
        js2.setOffset(175);

        final TextView mTextView = findViewById(R.id.y_value);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/krunch_bold.ttf");
        mTextView.setTypeface(custom_font);

        final TextView mTextView1 = findViewById(R.id.x_value);
        mTextView.setTypeface(custom_font);



        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);//draw the joystick with a new motion event
                //if the motion event is the layout is pressed, or is already pressed and is moved
                if(arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    //update the joystick direction
                    int direction = js.get8Direction();
                    if(direction == Joystick_Constructer.STICK_UP || direction == Joystick_Constructer.STICK_UPLEFT || direction == Joystick_Constructer.STICK_UPRIGHT) {
                        layout_joystick.setBackgroundDrawable( getResources().getDrawable(R.drawable.joystick_up) );
                        direcao1 = "up";
                        cont_e1 = 0;
                        cont_n1 = 0;
                        situacao1 = cont_d1++;
                        mTextView1.setText("up" + situacao1);
                    } else if(direction == Joystick_Constructer.STICK_DOWN || direction == Joystick_Constructer.STICK_DOWNLEFT || direction == Joystick_Constructer.STICK_DOWNRIGHT) {
                        layout_joystick.setBackgroundDrawable( getResources().getDrawable(R.drawable.joystick_down) );
                        direcao1 = "down";
                        cont_n1 = 0;
                        cont_d1 = 0;
                        situacao1 = cont_e1++;
                        mTextView1.setText("down" + situacao1);
                    }
                }else {
                    js.draw_off(250,250);
                    layout_joystick.setBackgroundDrawable( getResources().getDrawable(R.drawable.joystick_normal_v6) );
                    direcao1 = "neutra";
                    cont_d1 = 0;
                    cont_e1 = 0;
                    situacao1 = cont_n1++;
                    mTextView1.setText("neutra" + situacao1 );
                }
                if (direcao1 == "up"){
                    if(situacao1 > 0){
                    }else{
                       // Toast.makeText(JoyStickActivity.this, "TA NO PREFERENCES UP", Toast.LENGTH_LONG).show();
                        retrive_data("preferences_travao_off");
                        retrive_data("preferences_acelarador_on");
                    }
                }else if (direcao1 == "down"){
                    if(situacao1 > 0){
                    }else{
                        retrive_data("preferences_acelarador_off");
                        retrive_data("preferences_travao_on");
                    }
                }else if (direcao1 == "neutra"){
                    if(situacao1 > 0){
                    }else{
                        retrive_data("preferences_normal_marcha");
                    }
                }
                return true;
            }
        });

        layout_joystick2.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                js2.drawStick(arg1);//draw the joystick with a new motion event
                //if the motion event is the layout is pressed, or is already pressed and is moved
                if(arg1.getAction() == MotionEvent.ACTION_DOWN || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    //update the joystick direction
                    int direction = js2.get8Direction();
                    if(direction == Joystick_Constructer.STICK_RIGHT || direction == Joystick_Constructer.STICK_UPRIGHT || direction == Joystick_Constructer.STICK_DOWNRIGHT) {
                        layout_joystick2.setBackgroundDrawable( getResources().getDrawable(R.drawable.joystick_right) );
                        direcao = "direita";
                        cont_e = 0;
                        cont_n = 0;
                        situacao = cont_d++;
                        mTextView.setText("direita" + situacao);
                    } else if(direction == Joystick_Constructer.STICK_LEFT|| direction == Joystick_Constructer.STICK_UPLEFT || direction == Joystick_Constructer.STICK_DOWNLEFT) {
                        layout_joystick2.setBackgroundDrawable( getResources().getDrawable(R.drawable.joystick_left) );
                        direcao = "esquerda";
                        cont_n = 0;
                        cont_d = 0;
                        situacao = cont_e++;
                        mTextView.setText("esquerda" + situacao);
                    }
                }else {
                    js2.draw_off(250,250);
                    layout_joystick2.setBackgroundDrawable( getResources().getDrawable(R.drawable.joystick_normal_v7) );
                    direcao = "neutra";
                    cont_d = 0;
                    cont_e = 0;
                    situacao = cont_n++;
                    mTextView.setText("neutra" + situacao );
                }
                if (direcao == "direita"){
                    if(situacao > 0){
                    }else{
                        retrive_data("preferences_direita_off");
                        retrive_data("preferences_direita_on");
                    }
                }else if (direcao == "esquerda"){
                    if(situacao > 0){
                    }else{
                        retrive_data("preferences_esquerda_off");
                        retrive_data("preferences_esquerda_on");
                    }
                }else if (direcao == "neutra"){
                    if(situacao > 0){
                    }else{
                        retrive_data("preferences_normal_direcao");
                    }
                }
                return true;
            }
        });

        //BOTOES LUZES
        final ToggleButton max = findViewById(R.id.maximos);
        max.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(max.isChecked()) {
                    retrive_data("preferences_maximos_on");
                }
                else {
                    retrive_data("preferences_maximos_off");
                }
            }
        });
        final ToggleButton med = findViewById(R.id.medios);
        med.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(med.isChecked()) {
                    retrive_data("preferences_medios_on");
                }
                else {
                    retrive_data("preferences_medios_off");
                }
            }
        });
    }

    public void solicita(String comando){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            //Retrive SharePreference
            //SharedPreferences preferences_r = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            //String ip_final = preferences_r.getString("preferences_ip", "");
            //String url = "http://"+ ip_final +"/";
            String url = "http://192.168.4.1/";
            new JoyStickActivity.SolicitarDados().execute(url + comando);
        }else{
            Toast.makeText(JoyStickActivity.this, "ERROR - no connection", Toast.LENGTH_LONG).show();
        }
    }

    private class SolicitarDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            return Node_Conect.getNode(url[0]);
        }
        @Override
        protected void onPostExecute(String resultado) {
            if(resultado != null){

            }else{
                Toast.makeText(JoyStickActivity.this, "ERROR - Sem resultados", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void retrive_data(String preference){
        SharedPreferences preferences_r = PreferenceManager.getDefaultSharedPreferences(JoyStickActivity.this);
        String command = preferences_r.getString(preference, "");
        if(command.equals("")){
            Toast.makeText(JoyStickActivity.this, "Configure the command on Options", Toast.LENGTH_LONG).show();
        }else{
            solicita(command);
        }
    }

    void wifi(){
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo ();
        String ssid  = info.getSSID();
        final TextView txt_wifi = findViewById(R.id.wifi_value);
        txt_wifi.setTypeface(custom_font);
        if (!wifiManager.isWifiEnabled()){
            txt_wifi.setText("NO WIFI CONNECTION");
        }else{
            txt_wifi.setText("Wi-Fi Network: " + ssid);
        }
    }
}
