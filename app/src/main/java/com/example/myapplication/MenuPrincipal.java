package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MenuPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnIconoUser;
    private Button btnPromociones;
    private ScrollView scrollView;
    private ConstraintLayout lyCons;
    private CoordinatorLayout lyCoordinator;

    ListView lyServicios;
    List<listServicios> lst;


    ConexionBD conexionBD = ConexionBD.getInstancia();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        btnIconoUser = findViewById(R.id.btnUser);
        lyCoordinator = findViewById(R.id.lyCoordinator);
        lyServicios = findViewById(R.id.lyServicios);

        // Realizar la consulta a la base de datos en segundo plano
        new GetDataFromDatabase().execute();
    }



    // AsyncTask para realizar la consulta en segundo plano
    private class GetDataFromDatabase extends AsyncTask<Void, Void, List<listServicios>> {

        @Override
        protected List<listServicios> doInBackground(Void... voids) {
            List<listServicios> serviciosList = new ArrayList<>();
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                // Obtener la conexión de la instancia de ConexionBD
                connection = conexionBD.getConexion();

                // Realizar la consulta
                String query = "SELECT Nombre, Descripcion FROM ServiosRequest";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                // Procesar los resultados y crear la lista de servicios
                while (resultSet.next()) {
                    String nombre = resultSet.getString("Nombre");
                    String descripcion = resultSet.getString("Descripcion");
                    // Añadir el servicio a la lista
                    serviciosList.add(new listServicios(R.drawable.imgservicios, nombre, descripcion));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return serviciosList;
        }

        @Override
        protected void onPostExecute(List<listServicios> serviciosList) {
            if (serviciosList != null) {
                // Actualizar el ListView con los datos de la base de datos
                CustomAdapter adapter = new CustomAdapter(MenuPrincipal.this, serviciosList);
                lyServicios.setAdapter(adapter);
            } else {
                Toast.makeText(MenuPrincipal.this, "Error al obtener datos de la base de datos", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void carpinteria(View view){
        Intent btnCarpinteria = new Intent(this, carpinteria_luna.class);
        startActivity(btnCarpinteria);
    }

    public void plomeria(View view){
        Intent btnPlomeria = new Intent(this, plomeria_chuy.class);
        startActivity(btnPlomeria);
    }

    public void pintura(View view){
        Intent btnPintura = new Intent(this, pintura_primos.class);
        startActivity(btnPintura);
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

