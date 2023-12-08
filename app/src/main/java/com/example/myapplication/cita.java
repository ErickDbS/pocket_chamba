package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class cita extends AppCompatActivity {
        private boolean bool = false;
        private EditText cita;
        private String fechaCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

        cita = findViewById(R.id.editCita);

    }

    public void pagar(View view){
        fechaCita = cita.getText().toString();
        if (fechaCita.isEmpty()){
            Toast.makeText(this, "Rellene el campo", Toast.LENGTH_SHORT).show();

        } else {

            Intent btnPagar = new Intent(this, cartera.class);
            String nombre = getIntent().getStringExtra("nombre");
            int imagenId = getIntent().getIntExtra("imagen", 0);

            btnPagar.putExtra("nombre", nombre);
            btnPagar.putExtra("imagen", imagenId);
            btnPagar.putExtra("cita",fechaCita);
            btnPagar.putExtra("objeto", bool = true);
            startActivity(btnPagar);
            finish();
        }
    }

}