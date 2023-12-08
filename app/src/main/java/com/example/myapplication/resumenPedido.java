package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class resumenPedido extends AppCompatActivity {

    private TextView nombrePago;
    private ImageView imgServicio;
    private TextView txtNombre;
    private TextView txtcita;
    private String nombreServicio;
    private int imagen;
    private String cita2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_pedido);

        nombrePago = findViewById(R.id.txtPago);
        imgServicio = findViewById(R.id.imgServicio);
        txtNombre = findViewById(R.id.txtNombreServicio);
        txtcita = findViewById(R.id.txtCita);

        Intent intent = getIntent();
        String objeto = intent.getStringExtra("Objeto");
        String nombre = intent.getStringExtra("nombre");
        int imagenId = intent.getIntExtra("imagen", 0);
        String cita = intent.getStringExtra("cita");

        txtcita.setText(cita);
        nombrePago.setText(objeto);
        imgServicio.setImageResource(imagenId);
        txtNombre.setText(nombre);


        nombreServicio = nombre;
        imagen = imagenId;
        cita2 = cita;




    }

    public void menuPrincipal(View view) {
        Intent btnMenuPrincipal = new Intent(this, MenuPrincipal.class);
        Toast.makeText(this, "Orden Emitida Correctamente", Toast.LENGTH_SHORT).show();
        btnMenuPrincipal.putExtra("nombre", nombreServicio);
        btnMenuPrincipal.putExtra("imagen", imagen);
        btnMenuPrincipal.putExtra("cita", cita2);

        startActivity(btnMenuPrincipal);
        finish();
    }
}