package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class cartera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartera);
    }

    public void metodoDePago(View view){
        Intent btnMetodoPago = new Intent(this, metodoDePago.class);
        startActivity(btnMetodoPago);
        finish();
    }
}