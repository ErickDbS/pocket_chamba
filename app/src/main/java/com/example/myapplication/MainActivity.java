package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.PreparedStatement;


import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.namespace.NamespaceContext;


public class MainActivity extends AppCompatActivity {

    private EditText numTel;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);

        // Inicializa los EditText
        numTel = findViewById(R.id.NumTel);
        pass = findViewById(R.id.pass);
    }


    public void MenuPrincipal(View view) {
        String nombreUsuario = numTel.getText().toString();
        String contrasenia = pass.getText().toString();

        if (iniciarSesion(nombreUsuario, contrasenia)) {
            // Inicio de sesión exitoso, redirige al usuario a la pantalla principal
            // Puedes usar Intents para realizar esta redirección.
            Intent btnIniciarSesion = new Intent(this, MenuPrincipal.class);
            startActivity(btnIniciarSesion);
        } else {
            // Credenciales incorrectas, muestra un mensaje de error
            Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

    }

    public void Registrarse(View view) {

        Intent btnRegistrarse = new Intent(this, Registrarse.class);
        startActivity(btnRegistrarse);
    }


    public Connection conSQL(){
        Connection SQL = null;
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            SQL= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.25;databaseName=Android;user=sa;password=admin;");
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return SQL;
    }

    public boolean iniciarSesion(String telefono, String contrasenia) {
        try {
            // Preparar una consulta para verificar las credenciales
            String consulta = "SELECT * FROM Registros WHERE Telefono = ? AND contraseña = ?";
            PreparedStatement pat = conSQL().prepareStatement(consulta);
            pat.setString(1, telefono);
            pat.setString(2, contrasenia);

            ResultSet rs = pat.executeQuery();

            // Si se encuentra una fila, las credenciales son válidas
            if (rs.next()) {
                return true; // Inicio de sesión exitoso
            } else {
                return false; // Credenciales incorrectas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Manejar excepciones adecuadamente
        }
    }



}