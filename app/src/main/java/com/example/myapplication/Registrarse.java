package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                if (camposCompletos()) {
                    agregarUsuario();
                } else {
                    Toast.makeText(getApplicationContext(), "Completa todos los campos antes de registrarte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean camposCompletos() {
        String nombre = Nombre.getText().toString();
        String apellido = Apellido.getText().toString();
        String email = Email.getText().toString();
        String telefono = Telefono.getText().toString();
        String fecha = Fecha.getText().toString();
        String contrasenia = Contrasenia.getText().toString();

        return !nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !fecha.isEmpty() && !contrasenia.isEmpty();
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