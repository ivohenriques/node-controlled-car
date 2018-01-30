package com.example.ivo.projecto_final_ctesp_2018.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivo.projecto_final_ctesp_2018.MainActivity;
import com.example.ivo.projecto_final_ctesp_2018.MyApp;
import com.example.ivo.projecto_final_ctesp_2018.OptionsActivity;
import com.example.ivo.projecto_final_ctesp_2018.R;
import com.example.ivo.projecto_final_ctesp_2018.index;

/**
 * Created by Ivo on 05/12/2016.
 */

public class dialog_notas extends Dialog {

    private String comando ,ip1,ip2,ip3,ip4,IP;
    private Typeface custom_font;

    //fica com as propriedade do dialog_notas, ctl + o e dialog_notas context context {
    public dialog_notas(Context context) {
        super(context);
    }

    //Definam static...
    public void Initialize(final Context context) {
        final dialog_notas ratemyappdialog= new dialog_notas(context);
        ratemyappdialog.setContentView(R.layout.dialog_notas);

        //Parametros
        ratemyappdialog.setCancelable(false);
        ratemyappdialog.setCanceledOnTouchOutside(false);

        //GETTER E SETTERS
        final MyApp app = MyApp.getInstance();
        final String prof = app.getRowId_prof();



        //Declarações
        final EditText input = ratemyappdialog.findViewById(R.id.edt_input);
        final LinearLayout ip_field = ratemyappdialog.findViewById(R.id.lay_ip);
        final EditText edt_input_n1 = ratemyappdialog.findViewById(R.id.edt_input_n1);
        final EditText edt_input_n2 = ratemyappdialog.findViewById(R.id.edt_input_n2);
        final EditText edt_input_n3 = ratemyappdialog.findViewById(R.id.edt_input_n3);
        final EditText edt_input_n4 = ratemyappdialog.findViewById(R.id.edt_input_n4);
        final TextView txt_command = ratemyappdialog.findViewById(R.id.txt_command);
        TextView txt_ip = ratemyappdialog.findViewById(R.id.txt_ip);



        if(prof.equals("ip_on")){
            input.setVisibility(View.INVISIBLE);
            ip_field.setVisibility(View.VISIBLE);
            txt_command.setVisibility(View.INVISIBLE);
            txt_ip.setVisibility(View.VISIBLE);
        }else{
            input.setVisibility(View.VISIBLE);
            ip_field.setVisibility(View.INVISIBLE);
            txt_command.setVisibility(View.VISIBLE);
            txt_ip.setVisibility(View.INVISIBLE);
        }


        //Botão Alterar
        Button alterar = ratemyappdialog.findViewById(R.id.btn_alterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip1 = edt_input_n1.getText().toString();
                ip2 = edt_input_n2.getText().toString();
                ip3 = edt_input_n3.getText().toString();
                ip4 = edt_input_n4.getText().toString();
                comando = input.getText().toString();
                if(prof.equals("ip_on")) {
                    comando = "controlo";
                    if (ip1.equals("") || ip2.equals("") || ip3.equals("") || ip4.equals("")) {
                        Toast.makeText(getContext(), "Insert a valid IP", Toast.LENGTH_LONG).show();
                    }else{
                        IP = ip1+"."+ip2+"."+ip3+"."+ip4;
                        save("preferences_ip", IP);
                        //Refresh activity and exit dialog box
                        Intent i = new Intent(getContext(), OptionsActivity.class);
                        getContext().startActivity(i);
                        ratemyappdialog.dismiss();
                    }
                }else{
                    if (comando.equals("")) {
                        Toast.makeText(getContext(), "Insert a valid command", Toast.LENGTH_LONG).show();
                    }else{
                        switch (prof) {
                            case "acelarador_off":
                                save("preferences_acelarador_off", comando);
                                Toast.makeText(getContext(), "entrou no case acelarador", Toast.LENGTH_SHORT).show();
                                break;
                            case "acelarador_on":
                                save("preferences_acelarador_on", comando);
                                break;
                            case "travao_off":
                                save("preferences_travao_off", comando);
                                break;
                            case "travao_on":
                                save("preferences_travao_on", comando);
                                break;
                            case "medios_off":
                                save("preferences_medios_off", comando);
                                break;
                            case "medios_on":
                                save("preferences_medios_on", comando);
                                break;
                            case "maximos_off":
                                save("preferences_maximos_off", comando);
                                break;
                            case "maximos_on":
                                save("preferences_maximos_on", comando);
                                break;
                            case "marcha_down":
                                save("preferences_travao_on", comando);
                                break;
                            case "marcha_up":
                                save("preferences_acelarador_on", comando);
                                break;
                            case "marcha_normal":
                                save("preferences_normal_marca", comando);
                                break;
                            case "direcao_left":
                                save("preferences_esquerda_on", comando);
                                break;
                            case "direcao_right":
                                save("preferences_direita_on", comando);
                                break;
                            case "direcao_normal":
                                save("preferences_normal_direcao", comando);
                                break;
                        }
                        //Refresh activity and exit dialog box
                        Intent i = new Intent(getContext(), OptionsActivity.class);
                        getContext().startActivity(i);
                        ratemyappdialog.dismiss();
                    }
                }
        }
        });
        //Botão cancelar
        Button cancelar =(Button) ratemyappdialog.findViewById(R.id.btn_cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(ratemyappdialog.isShowing())
                        ratemyappdialog.dismiss();
                }
        });
        ratemyappdialog.show();
        //Fonte personalizada
        custom_font = Typeface.createFromAsset(ratemyappdialog.getContext().getAssets(),  "fonts/krunch_bold.ttf");
        edt_input_n1.setTypeface(custom_font);
        edt_input_n2.setTypeface(custom_font);
        edt_input_n3.setTypeface(custom_font);
        edt_input_n4.setTypeface(custom_font);
        txt_command.setTypeface(custom_font);
        txt_ip.setTypeface(custom_font);
        input .setTypeface(custom_font);
        cancelar.setTypeface(custom_font);
        alterar.setTypeface(custom_font);
    }
    private void save (String identificador, String comando_novo){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(identificador,comando_novo);
        editor.apply();
    }
}
