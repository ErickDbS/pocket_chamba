package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.PreparedStatement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    private EditText numTel;
    private EditText pass;
    public String UserName;

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
        new IniciarSesionTask().execute();

        if (iniciarSesion(nombreUsuario, contrasenia)) {
            // Inicio de sesión exitoso, redirige al usuario a la pantalla principal
            // Puedes usar Intents para realizar esta redirección.
            String UserName = obtenerNombreDeUsuario(nombreUsuario);



            Intent btnIniciarSesion = new Intent(this, MenuPrincipal.class);

            // Se recupera el nombre del usuario que inicio sesion y se manda al activity MenuPrincipal
            btnIniciarSesion.putExtra("key", UserName);
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

    // Singleton que crea una instncia unica a la BD
    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion = conexionBD.getConexion();




    public boolean iniciarSesion(String telefono, String contrasenia) {
        try {
            // Preparar una consulta para verificar las credenciales
            String consulta = "SELECT * FROM Registros WHERE Telefono = ? AND contraseña = ?";
            PreparedStatement pat = conexion.prepareStatement(consulta);
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



    private class IniciarSesionTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            // Llama a tu método iniciarSesion aquí
            return iniciarSesion(numTel.getText().toString(), pass.getText().toString());
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            if (resultado) {
                // Inicio de sesión exitoso
                // Realiza la redirección aquí

                // Obtén el nombre de usuario
                String nombreUsuario = obtenerNombreDeUsuario(numTel.getText().toString());

                if (nombreUsuario != null) {
                    // Crea un Intent para abrir la actividad MenuPrincipal
                    Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);

                    // Pasa el nombre de usuario como extra al Intent
                    intent.putExtra("key", nombreUsuario);

                    // Inicia la actividad MenuPrincipal
                    startActivity(intent);
                } else {
                    // Manejo de error si no se pudo obtener el nombre de usuario
                    Toast.makeText(getApplicationContext(), "Error al obtener el nombre de usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Credenciales incorrectas
                // Muestra el Toast o realiza otra acción aquí
                Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String obtenerNombreDeUsuario(String telefono) {
        try {
            // Preparar una consulta para obtener el nombre de usuario
            String consulta = "SELECT Nombre FROM Registros WHERE Telefono = ?";
            PreparedStatement pat = conexion.prepareStatement(consulta);
            pat.setString(1, telefono);

            ResultSet rs = pat.executeQuery();

            // Si se encuentra una fila, devuelve el nombre de usuario
            if (rs.next()) {
                return rs.getString("Nombre");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}