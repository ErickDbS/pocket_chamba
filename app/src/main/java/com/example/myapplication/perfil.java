package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class perfil extends AppCompatActivity  {

    private TextView txtUserName;
    String textoRecibido;

    private String nombreServicio;
    private String cita;
    private int imagen;
    private String numTel;

    // Singleton que crea una instncia unica a la BD
    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion = conexionBD.getConexion();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtUserName = findViewById(R.id.txtUserName);

        // Obtén el número de teléfono del intent
        Intent intent = getIntent();
        numTel = intent.getStringExtra("telefono");

        // Inicia la tarea asincrónica para obtener el nombre de usuario
        new ObtenerNombreUsuarioTask().execute();





    }



    private class ObtenerNombreUsuarioTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            // Llama a tu método obtenerNombreDeUsuario aquí
            return obtenerNombreDeUsuario(numTel);
        }

        @Override
        protected void onPostExecute(String nombreUsuario) {
            if (nombreUsuario != null) {
                // Muestra el nombre de usuario en el TextView
                txtUserName.setText(nombreUsuario);
            } else {
                // Manejo de error si no se pudo obtener el nombre de usuario
                // Puedes mostrar un mensaje o realizar otra acción
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
    public void reportes(View view){
        Intent btnReportes = new Intent(this, reportes.class);
        startActivity(btnReportes);

    }

    public void cartera(View view){
        Intent btnCartera = new Intent(this, cartera.class);
        startActivity(btnCartera);

    }

    public void ayuda(View view){
        Intent btnAyuda = new Intent(this, ayuda.class);
        btnAyuda.putExtra("key",textoRecibido);
        Intent intent = getIntent();

        nombreServicio = intent.getStringExtra("nombre");
        cita = intent.getStringExtra("cita");
        imagen = intent.getIntExtra("imagen",0);

        btnAyuda.putExtra("nombre",nombreServicio);
        btnAyuda.putExtra("cita",cita);
        btnAyuda.putExtra("imagen", imagen);

        startActivity(btnAyuda);

    }

    public void cerraSeison(View view){
        Intent btnCerrarSesion = new Intent(this, MainActivity.class);
        // Borrar las preferencias al cerrar sesión
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("NOMBRE");
        editor.apply();
        startActivity(btnCerrarSesion);

    }

}