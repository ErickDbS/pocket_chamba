package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class reportes extends AppCompatActivity {
    ConexionBD conexionBD = ConexionBD.getInstancia();
    Connection conexion;

    private EditText txtDesc;
    private String tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        Spinner spinnerTipo = findViewById(R.id.spnTipoReporte);


        // Crear un arreglo de elementos que deseas mostrar en el Spinner
        String[] Tipos = {"Seleccione una opcion", "Quejas", "Comentarios/Calificacion", "Problemas con la app", "Problemas con un colaborador"};

        // Crear un ArrayAdapter y establecerlo en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);
        tipo = (String) spinnerTipo.getSelectedItem();

    }



    public void newService() {
        // Declarar PreparedStatement fuera del bloque try-catc
        PreparedStatement pat = null;

        try {
            // Verificar que la conexión esté abierta
            if (conexion != null && !conexion.isClosed()) {
                // Excluir la columna ID en la sentencia INSERT
                pat = conexion.prepareStatement("INSERT INTO ServiosRequest VALUES (?,?)");
                pat.setString(1, tipo);
                pat.setString(2, txtDesc.getText().toString());

                pat.executeUpdate();

                Toast.makeText(getApplicationContext(), "PETICION MANDADA CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            } else {
                // Manejar el caso donde la conexión está cerrada
                Toast.makeText(getApplicationContext(), "Error: La conexión a la base de datos está cerrada", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = e.getSQLState() + ": " + e.getMessage();
            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        } finally {
            // Cerrar PreparedStatement en el bloque finally para asegurarse de que se cierre
            try {
                if (pat != null) {
                    pat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}