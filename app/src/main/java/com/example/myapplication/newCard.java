package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                agregarTarjeta();
                Intent btnMetodoDePago = new Intent(newCard.this, metodoDePago.class);
                startActivity(btnMetodoDePago);
                finish();
            }
        });
    }

    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion = conexionBD.getConexion();

    public void agregarTarjeta(){
        try {
            PreparedStatement pat = conexion.prepareStatement("INSERT INTO Tarjetas VALUES (?,?,?,?)");
            pat.setString(1,txtTarjeta.getText().toString());
            pat.setString(2,txtFecha.getText().toString());
            pat.setString(3,txtCvv.getText().toString());
            pat.setString(4,txtTitular.getText().toString());

            pat.executeUpdate();

            Toast.makeText(getApplicationContext(),"TARJETA AGREGADA CORRECTAMENTE", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
}