package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {

        private EditText numTel;
        private EditText pass;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.iniciar_sesion);

            // Inicializa los EditText
            numTel = findViewById(R.id.NumTel);
            pass = findViewById(R.id.pass);
        }


    public void MenuPrincipal(View view) {
        String numTelFinal = "6682417874";
        String passFinal = "erick";

            String User = numTel.getText().toString();
            String Password = pass.getText().toString();

            if (User.equals(numTelFinal) && Password.equals(passFinal)) {
                // Cambia 'MenuPrincipal.class' al nombre correcto de tu actividad
                Intent btnSesion = new Intent(this, MenuPrincipal.class);
                startActivity(btnSesion);

            } else {
                Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
            }
        }

        public void Registrarse(View view) {
            Intent btnRegistrarse = new Intent(this, Registrarse.class);
            startActivity(btnRegistrarse);

        }
}