package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class agregarServicio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);

        Spinner spinner = findViewById(R.id.spinner_id);

        // Crear un arreglo de elementos que deseas mostrar en el Spinner
        String[] elementos = {"Seleccione una opcion", "Plomeria", "Carpinteria", "Albañileria", "Mecanica"};

        // Crear un ArrayAdapter y establecerlo en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        String profecion = (String) spinner.getSelectedItem();
    }

    public void Regresar(View view){
        Intent btnRegresar = new Intent(this, MenuPrincipal.class);
        startActivity(btnRegresar);
    }


}