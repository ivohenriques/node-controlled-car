package com.example.ivo.projecto_final_ctesp_2018;

import android.app.Application;

import java.util.List;

/**
 * Created by Ivo on 16/01/2017.
 */
public class MyApp extends Application {
    public String rowId_prof;



    private static MyApp ourInstance = new MyApp();

    public static MyApp getInstance() {
        return ourInstance;
    }

    public MyApp() {
    }

    public String getRowId_prof() {
        return rowId_prof;
    }

    public void setRowId_prof(String rowId_prof) {this.rowId_prof = rowId_prof; }

}


