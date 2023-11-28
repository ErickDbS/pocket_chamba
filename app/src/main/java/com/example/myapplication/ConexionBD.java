package com.example.myapplication;

import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection SQL = null;


    private ConexionBD() {
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            SQL= DriverManager.getConnection("jdbc:jtds:sqlserver://172.20.10.4;databaseName=Android;user=sa;password=admin;");

        } catch (Exception e){

        }
    }

    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return SQL;
    }
}
