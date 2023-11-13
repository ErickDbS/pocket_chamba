package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class agregarServicio extends AppCompatActivity {

    private EditText txtName;
    private EditText txtArea;
    private EditText txtLocation;
    private EditText txtPhone;
    private Button btnMandarSolicitud;
    private String profecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);

        Spinner spinner = findViewById(R.id.spinner_id);
        txtName = findViewById(R.id.txtName);
        txtArea = findViewById(R.id.textArea);
        txtLocation = findViewById(R.id.txtLocation);
        txtPhone = findViewById(R.id.txtPhone);
        btnMandarSolicitud = findViewById(R.id.btnMandarSolicitud);


        // Crear un arreglo de elementos que deseas mostrar en el Spinner
        String[] elementos = {"Seleccione una opcion", "Plomeria", "Carpinteria", "Albañileria", "Mecanica"};

        // Crear un ArrayAdapter y establecerlo en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



         btnMandarSolicitud.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 profecion = (String) spinner.getSelectedItem();
                 newService();
                 Regresar(view);
             }
         });
    }

    public void Regresar(View view){
        Intent btnRegresar = new Intent(this, MenuPrincipal.class);
        startActivity(btnRegresar);
        finish();
    }

    // Singleton que crea una instncia unica a la BD

    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion = conexionBD.getConexion();
    public void newService(){
        try {
            PreparedStatement pat = conexion.prepareStatement("INSERT INTO ServiosRequest VALUES (?,?,?,?,?)");
            pat.setString(1,txtName.getText().toString());
            pat.setString(2,profecion.toString());
            pat.setString(3,txtArea.getText().toString());
            pat.setString(4,txtLocation.getText().toString());
            pat.setString(5,txtPhone.getText().toString());

            pat.executeUpdate();

            Toast.makeText(getApplicationContext(),"PETICION MANDADA CORRECTAMENTE", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }


}