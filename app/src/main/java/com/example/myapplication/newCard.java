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

public class newCard extends AppCompatActivity {

    private EditText txtTarjeta;
    private EditText txtFecha;
    private EditText txtCvv;
    private EditText txtTitular;
    private Button btnAñadirCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        txtTarjeta = findViewById(R.id.txtTarjeta);
        txtFecha = findViewById(R.id.txtFechaTarjeta);
        txtCvv = findViewById(R.id.txtCvv);
        txtTitular = findViewById(R.id.txtNombreTarjeta);
        btnAñadirCard = findViewById(R.id.btnGuardarTarjeta);

        btnAñadirCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camposCompletos()) {
                    agregarTarjeta();
                    Intent btnMetodoDePago = new Intent(newCard.this, metodoDePago.class);
                    startActivity(btnMetodoDePago);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Completa todos los campos antes de guardar la tarjeta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private boolean camposCompletos() {
        String numeroTarjeta = txtTarjeta.getText().toString();
        String fechaTarjeta = txtFecha.getText().toString();
        String cvv = txtCvv.getText().toString();
        String titular = txtTitular.getText().toString();

        return !numeroTarjeta.isEmpty() && !fechaTarjeta.isEmpty() && !cvv.isEmpty() && !titular.isEmpty();
    }

    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion = conexionBD.getConexion();

    public void agregarTarjeta() {
        String numeroTarjeta = txtTarjeta.getText().toString();
        String fechaTarjeta = txtFecha.getText().toString();
        String cvv = txtCvv.getText().toString();
        String titular = txtTitular.getText().toString();

        if (numeroTarjeta.isEmpty() || fechaTarjeta.isEmpty() || cvv.isEmpty() || titular.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show();
            return; // Sale del método si algún campo está vacío
        }

        try {
            PreparedStatement pat = conexion.prepareStatement("INSERT INTO Tarjetas VALUES (?,?,?,?)");
            pat.setString(1, numeroTarjeta);
            pat.setString(2, fechaTarjeta);
            pat.setString(3, cvv);
            pat.setString(4, titular);

            pat.executeUpdate();

            Toast.makeText(getApplicationContext(), "TARJETA AGREGADA CORRECTAMENTE", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}