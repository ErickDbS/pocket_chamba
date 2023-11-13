package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class metodoDePago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_de_pago);
    }

    public void newCard(View view){
        Intent btnNewCard = new Intent(this, newCard.class);
        startActivity(btnNewCard);
    }
}