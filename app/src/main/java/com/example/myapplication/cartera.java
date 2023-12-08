package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class cartera extends AppCompatActivity {

    private ImageView imgDinero;
    private boolean bool;
    private String metodoPago;
    private TextView CashName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartera);

        CashName = findViewById(R.id.txtNameCash);

        imgDinero = findViewById(R.id.imgDinero2);
        Intent intent = getIntent();
        bool = intent.getBooleanExtra("objeto",false);
        metodoPago = CashName.getText().toString();
        int imagenId = intent.getIntExtra("imagen", 0); // Usar la misma clave
        String nombre = intent.getStringExtra("nombre");
        String cita = intent.getStringExtra("cita");

            imgDinero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bool == true) {
                        Intent intent1 = new Intent(cartera.this, resumenPedido.class);
                        intent1.putExtra("Objeto", metodoPago);
                        intent1.putExtra("nombre", nombre);
                        intent1.putExtra("imagen", imagenId);
                        intent1.putExtra("cita",cita);
                        startActivity(intent1);
                        finish();
                    } else {
                        Toast.makeText(cartera.this, "Accion invalida", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    public void metodoDePago(View view){
        Intent btnMetodoPago = new Intent(this, metodoDePago.class);
        startActivity(btnMetodoPago);
        finish();
    }
}