package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.PreparedStatement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    private EditText numTel;
    private EditText pass;
    private ConexionBD conexionBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);

        // Inicializa los EditText
        numTel = findViewById(R.id.NumTel);
        pass = findViewById(R.id.pass);

        // Crear una instancia de ConexionBD
        conexionBD = ConexionBD.getInstancia();
    }

    public void MenuPrincipal(View view) {
        String nombreUsuario = numTel.getText().toString();
        String contrasenia = pass.getText().toString();

        // Realizar la conexión en un hilo secundario
        new IniciarSesionTask().execute(nombreUsuario, contrasenia);
    }

    private class IniciarSesionTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String nombreUsuario = params[0];
            String contrasenia = params[1];

            // Obtener la conexión de la instancia de ConexionBD
            Connection conexion = conexionBD.getConexion();

            try {
                // Verificar si la conexión es nula antes de usarla
                if (conexion != null) {
                    // Verificar las credenciales del usuario
                    return validarCredenciales(conexion, nombreUsuario, contrasenia);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            if (resultado) {
                // Credenciales válidas, redirigir al usuario a la pantalla principal
                redirigirMenuPrincipal(numTel.getText().toString());
            } else {
                // Credenciales incorrectas, mostrar mensaje de error
                mostrarError("Credenciales incorrectas");
            }
        }

        private boolean validarCredenciales(Connection conexion, String nombreUsuario, String contrasenia) throws SQLException {
            String consulta = "SELECT * FROM Registros WHERE Telefono = ? AND contraseña = ?";
            try (PreparedStatement pat = conexion.prepareStatement(consulta)) {
                pat.setString(1, nombreUsuario);
                pat.setString(2, contrasenia);

                try (ResultSet rs = pat.executeQuery()) {
                    return rs.next(); // Devuelve true si encuentra al menos una fila (credenciales válidas)
                }
            }
        }

        private void redirigirMenuPrincipal(String nombreUsuario) {
            // Inicio de sesión exitoso, redirigir al usuario a la pantalla principal
            Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
            intent.putExtra("key", nombreUsuario);
            startActivity(intent);
        }

        private void mostrarError(String mensaje) {
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void Registrarse(View view) {
        Intent btnRegistrarse = new Intent(this, Registrarse.class);
        startActivity(btnRegistrarse);
    }
}