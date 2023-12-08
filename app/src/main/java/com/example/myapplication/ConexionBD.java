package com.example.myapplication;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection SQL = null;


    private ConexionBD() {
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            SQL= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.20;databaseName=Android;user=sa;password=admin;");

        } catch (Exception e){

        }
    }

    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
            Log.d("ConexionBD", "Instancia creada correctamente");
        } else {
            Log.d("ConexionBD", "Instancia ya existe");
        }

        if (instancia.SQL != null) {
            Log.d("ConexionBD", "Conexión establecida correctamente");
        } else {
            Log.d("ConexionBD", "Conexión nula");
        }

        return instancia;
    }


    public Connection getConexion() {
        return SQL;
    }
}
