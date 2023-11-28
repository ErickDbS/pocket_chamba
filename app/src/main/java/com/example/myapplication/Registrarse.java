package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class Registrarse extends AppCompatActivity {

    public EditText Nombre;
    public EditText Apellido;
    public EditText Email;
    public EditText Telefono;
    public EditText Fecha;
    public EditText Contrasenia;

    public Button btnRegistrarse2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        btnRegistrarse2 = findViewById(R.id.btnRegistrarse2);
        Nombre = findViewById(R.id.txtNombre);
        Apellido = findViewById(R.id.txtApellido);
        Email = findViewById(R.id.txtEmail);
        Telefono = findViewById(R.id.txtTelefono);
        Fecha = findViewById(R.id.txtFecha);
        Contrasenia = findViewById(R.id.txtContrasenia);

        btnRegistrarse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarUsuario();

            }
        });
    }

    public void IniciarSesion(View view) {
        Intent Registrarse = new Intent(this, MainActivity.class);
        startActivity(Registrarse);
        finish();
    }


    // Singleton que crea una instncia unica a la BD

    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion = conexionBD.getConexion();


    public void agregarUsuario(){
        try {
            PreparedStatement pat = conexion.prepareStatement("INSERT INTO Registros VALUES (?,?,?,?,?,?)");
            pat.setString(1,Nombre.getText().toString());
            pat.setString(2,Apellido.getText().toString());
            pat.setString(3,Email.getText().toString());
            pat.setString(4,Telefono.getText().toString());
            pat.setString(5,Fecha.getText().toString());
            pat.setString(6,Contrasenia.getText().toString());
            pat.executeUpdate();

            Toast.makeText(getApplicationContext(),"USUARIO AGREGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
}