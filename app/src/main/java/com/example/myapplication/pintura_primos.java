package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class pintura_primos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintura_primos);
    }

    public void cita(View view){
        Intent btnCita = new Intent(this, cita.class);
        startActivity(btnCita);
    }
}