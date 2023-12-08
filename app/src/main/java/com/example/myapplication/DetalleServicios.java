package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleServicios extends AppCompatActivity {

    private ImageView imgServicio;
    private TextView txtNombre;
    private TextView  txtDesc;
    private Button btnContratar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_servicios);

        imgServicio = findViewById(R.id.imgServicio);
        txtNombre = findViewById(R.id.txtNombreServicio);
        txtDesc = findViewById(R.id.textDesc);

        // Obtén los datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        int imagenId = intent.getIntExtra("imagen", 0); // Usar la misma clave
        String descripcion = intent.getStringExtra("descripcion");

        txtNombre.setText(nombre);
        txtDesc.setText(descripcion);
        imgServicio.setImageResource(imagenId);
    }

    public void cita(View view){
        Intent btnCita = new Intent(this, cita.class);
        String nombre = getIntent().getStringExtra("nombre");
        int imagenId = getIntent().getIntExtra("imagen", 0);

        btnCita.putExtra("nombre", nombre);
        btnCita.putExtra("imagen", imagenId);
        startActivity(btnCita);
        finish();
    }

}