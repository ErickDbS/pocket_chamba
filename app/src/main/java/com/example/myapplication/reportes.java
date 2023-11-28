package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class reportes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        Spinner spinnerTipo = findViewById(R.id.spnTipoReporte);
        Spinner spinnerFiltro = findViewById(R.id.spnFiltro);

        // Crear un arreglo de elementos que deseas mostrar en el Spinner
        String[] Tipos = {"Seleccione una opcion", "Quejas", "Comentarios/Calificacion", "Problemas con la app", "Problemas con un colaborador"};
        String[] Filtros = {"Seleccione una opcion"};

        // Crear un ArrayAdapter y establecerlo en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Filtros);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiltro.setAdapter(adapter2);
    }
}