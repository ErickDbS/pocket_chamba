package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ayuda extends AppCompatActivity {

    private String nombre;
    private String nombreServicio;
    private String cita;
    private int imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("key");

    }

    public void pedidos(View view){
        Intent btnPedidos = new Intent(this, pedidios.class);
        btnPedidos.putExtra("key",nombre);

        Intent intent = getIntent();

        nombreServicio = intent.getStringExtra("nombre");
        cita = intent.getStringExtra("cita");
        imagen = intent.getIntExtra("imagen",0);

        btnPedidos.putExtra("nombre",nombreServicio);
        btnPedidos.putExtra("cita",cita);
        btnPedidos.putExtra("imagen", imagen);
        startActivity(btnPedidos);

    }
}