package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;


public class MenuPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnIconoUser;
    private Button btnPromociones;
    private ScrollView scrollView;
    private ConstraintLayout lyCons;
    private CoordinatorLayout lyCoordinator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        btnIconoUser = findViewById(R.id.btnUser);
        lyCons = findViewById(R.id.lyCons);
        lyCoordinator = findViewById(R.id.lyCoordinator);
        scrollView = findViewById(R.id.scroll);



    }

    public void nuevoServicio(View view){
        Intent btnAgregarServicio = new Intent(this, agregarServicio.class);
        startActivity(btnAgregarServicio);
    }

    public void perfil(View view){
        Intent intent = getIntent();
        String textoRecibido = intent.getStringExtra("key");

        // Se recupera el valor enviado desde el main y se vuelve a mandar al activity de perfil para ser mostrado
        Intent btnPerfil = new Intent(this, perfil.class);
        btnPerfil.putExtra("key", textoRecibido);
        startActivity(btnPerfil);
    }
}

