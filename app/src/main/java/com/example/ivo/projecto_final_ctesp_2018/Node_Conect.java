package com.example.ivo.projecto_final_ctesp_2018;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ivo on 16/11/2017.
 */

//http://square.github.io/okhttp/

public class Node_Conect {

    public static String getNode(String urlUsuario) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(urlUsuario).build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException erro){
            return erro.toString();
        }
    }
}
