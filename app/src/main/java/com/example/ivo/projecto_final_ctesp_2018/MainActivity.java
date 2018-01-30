package com.example.ivo.projecto_final_ctesp_2018;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float coordenadasY;
    private TextView mTextView;
    private String direcao;
    private int cont_d, cont_e, cont_n, situacao;
    private Typeface custom_font;
    private  String theOutput="nada",NewString="nada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Aplicação LandScape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Aplicacao sempre com ecra ligado
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Declaração de sensores
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Fonte personalizada
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/krunch_bold.ttf");


        //Declaração dos componentes
        final ImageButton acelarador = findViewById(R.id.pedal_gas);
        final ImageButton travao = findViewById(R.id.pedal_gas1);
        travao.setImageResource(R.drawable.pedal_1);
        acelarador.setImageResource(R.drawable.pedal_1);



        final MyApp app = MyApp.getInstance();
        final String prof = app.getRowId_prof();

        wifi();



start();





        //BOTOES TRAVOES
        travao.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    travao.setImageResource(R.drawable.pedal_2);
                    retrive_data("preferences_travao_on");
                }

                else if (event.getAction() == MotionEvent.ACTION_UP ) {
                    travao.setImageResource(R.drawable.pedal_1);
                    retrive_data("preferences_travao_off");
                }
                return false;
            }
        });

        //BOTOES ACELARADORES
        acelarador.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    acelarador.setImageResource(R.drawable.pedal_2);
                    retrive_data("preferences_acelarador_on");






                }
                else if (event.getAction() == MotionEvent.ACTION_UP ) {
                    acelarador.setImageResource(R.drawable.pedal_1);
                    retrive_data("preferences_acelarador_off");
                }
                return false;
            }

        }
        );

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

    protected void onResume() {
        super.onResume();

        //Recomeça sensor
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        stop();
        //Pará sensor
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        coordenadasY = event.values[1];

        mTextView = findViewById(R.id.y_value);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/krunch_bold.ttf");
        //mTextView.setText(Float.toString(Math.round(coordenadasY)));
        mTextView.setTypeface(custom_font);



        if (coordenadasY > 2.10){
            direcao = "direita";
            cont_e = 0;
            cont_n = 0;
            situacao = cont_d++;
            //mTextView.setText("direita" + situacao);
        }else if (coordenadasY < -2.10){
            direcao = "esquerda";
            cont_n = 0;
            cont_d = 0;
            situacao = cont_e++;
            //mTextView.setText("esquerda" + situacao);
        }else{
            direcao = "neutra";
            cont_d = 0;
            cont_e = 0;
            situacao = cont_n++;
            //mTextView.setText("neutra" + situacao );
        }

        if (direcao == "direita"){
            if(situacao > 0){
            }else{
                solicita("direita");
                //retrive_data("preferences_direita_on");
            }
        }else if (direcao == "esquerda"){
            if(situacao > 0){
            }else{
                solicita("esquerda");
                //retrive_data("preferences_esquerda_on");
            }
        }else if (direcao == "neutra"){
            if(situacao > 0){
            }else{
                solicita("neutra");
                //retrive_data("preferences_normal_direcao");
            }
        }


    }



    //NODEMCU LUA

    public void solicita(String comando){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            //Retrive SharePreference
            //SharedPreferences preferences_r = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            //String ip_final = preferences_r.getString("preferences_ip", "");
            //String url = "http://"+ ip_final +"/";
            String url = "http://192.168.4.1/";
            new SolicitarDados().execute(url + comando);
        }else{
            Toast.makeText(MainActivity.this, "ERROR - no connection", Toast.LENGTH_LONG).show();
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
                Toast.makeText(MainActivity.this, "ERROR - Sem resultados", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void retrive_data(String preference){
        SharedPreferences preferences_r = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String command = preferences_r.getString(preference, "");
        if(command.equals("")){
            Toast.makeText(MainActivity.this, "Configure command on Options", Toast.LENGTH_LONG).show();
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


    private void getCollision() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Jsoup.connect("http://192.168.4.1").get();
                } catch (IOException e) {
                    theOutput = e.getMessage().toString();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NewString = theOutput.replaceAll("Unexpected status line: ", "");
                    }
                });
            }
        }).start();
    }


    private Timer timer;
    private TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            //Toast.makeText(getApplicationContext(), "ta true", Toast.LENGTH_LONG).show();
            getCollision();
            if (NewString.equals("TA ON")){
                Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                vib.vibrate(1000);
            }
        }
    };

    public void start() {
        if(timer != null) {
            return;
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void stop() {
        timer.cancel();
        timer = null;
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity.this, index.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
