package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class pedidios extends AppCompatActivity {

    private ListView listViewPedidos;
    private List<listPedidos> pedidosUsuarioActual;
    private String user;
    private String nombreServicio;
    private String cita;
    private int imagen;
    private AdapterPedidos adapterPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidios);

        Intent intent = getIntent();

        nombreServicio = intent.getStringExtra("nombre");
        cita = intent.getStringExtra("cita");
        imagen = intent.getIntExtra("imagen", 0);

        listViewPedidos = findViewById(R.id.lyPedidos);

        // Obtén la lista actual antes de agregar el nuevo elemento
        pedidosUsuarioActual = getData();

        // Agrega el nuevo elemento a la lista
        pedidosUsuarioActual.add(new listPedidos(imagen, nombreServicio, cita));

        // Verifica si la lista está vacía y ajusta la visibilidad del ListView
        if (pedidosUsuarioActual.isEmpty()) {
            listViewPedidos.setVisibility(View.GONE);
        } else {
            listViewPedidos.setVisibility(View.VISIBLE);
        }

        // Utiliza la lista actualizada al crear o actualizar el adaptador
        if (adapterPedidos == null) {
            adapterPedidos = new AdapterPedidos(this, pedidosUsuarioActual);
            listViewPedidos.setAdapter(adapterPedidos);
        } else {
            adapterPedidos.notifyDataSetChanged();
        }
    }

    // Utiliza la lista existente en lugar de crear una nueva cada vez
    private List<listPedidos> getData() {
        if (pedidosUsuarioActual == null) {
            pedidosUsuarioActual = new ArrayList<>();
        }
        return pedidosUsuarioActual;
    }
}


