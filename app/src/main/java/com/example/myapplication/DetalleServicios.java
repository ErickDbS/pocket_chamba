package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalleServicios extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private ImageView imgServicio;
    private TextView txtNombre;
    private TextView  txtDesc;
    private Button btnContratar;
    GoogleMap mMap;


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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng mochis = new LatLng(25.7609803,-108.985453217);
        mMap.addMarker(new MarkerOptions().position(mochis).title("Los Mochis"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mochis, 15)); // Ajusta el nivel de zoom según sea necesario

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }
}