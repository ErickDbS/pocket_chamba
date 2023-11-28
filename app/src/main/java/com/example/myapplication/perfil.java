package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class perfil extends AppCompatActivity {

    private TextView txtUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Intent intent = getIntent();
        String textoRecibido = intent.getStringExtra("key");

        txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(textoRecibido);



    }

    public void reportes(View view){
        Intent btnReportes = new Intent(this, reportes.class);
        startActivity(btnReportes);
        finish();
    }

    public void cartera(View view){
        Intent btnCartera = new Intent(this, cartera.class);
        startActivity(btnCartera);
        finish();
    }

    public void ayuda(View view){
        Intent btnAyuda = new Intent(this, ayuda.class);
        startActivity(btnAyuda);
        finish();
    }

    public void cerraSeison(View view){
        Intent btnCerrarSesion = new Intent(this, MainActivity.class);
        startActivity(btnCerrarSesion);
        finish();
    }


}