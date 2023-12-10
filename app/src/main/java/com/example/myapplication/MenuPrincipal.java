package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MenuPrincipal extends AppCompatActivity  {

    private ImageButton btnIconoUser;

    private ConstraintLayout lyCons;
    private CoordinatorLayout lyCoordinator;
    private String nombreServicio;
    private String cita;
    private int imagen;
    private String telefono;

    ListView lyServicios;
    List<listServicios> lst;


    ConexionBD conexionBD = ConexionBD.getInstancia();

    // Singleton que crea una instncia unica a la BD

    Connection conexion = conexionBD.getConexion();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        btnIconoUser = findViewById(R.id.btnUser);
        lyCoordinator = findViewById(R.id.lyCoordinator);
        lyServicios = findViewById(R.id.lyPedidos);




        // Realizar la consulta a la base de datos en segundo plano
        new GetDataFromDatabase().execute();


        lyServicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listServicios ly = lst.get(i);

                Intent intent = new Intent(MenuPrincipal.this, DetalleServicios.class);
                intent.putExtra("nombre", ly.getNombre());
                intent.putExtra("imagen", ly.getImagen());
                intent.putExtra("descripcion", ly.getDesc());

                startActivity(intent);

            }
        });


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


    public void nuevoServicio(View view){
        Intent btnAgregarServicio = new Intent(this, agregarServicio.class);
        startActivity(btnAgregarServicio);
    }

    public void perfil(View view){
        Intent intent = getIntent();
        String textoRecibido = intent.getStringExtra("key");
        telefono = intent.getStringExtra("telefono");

        //Recupera el valor del activity de resumen pedido
        nombreServicio = intent.getStringExtra("nombre");
        cita = intent.getStringExtra("cita");
        imagen = intent.getIntExtra("imagen",0);

        // Se recupera el valor enviado desde el main y se vuelve a mandar al activity de perfil para ser mostrado
        Intent btnPerfil = new Intent(this, perfil.class);
        btnPerfil.putExtra("key", textoRecibido);

        //datos del resumen pedido
        btnPerfil.putExtra("nombre",nombreServicio);
        btnPerfil.putExtra("cita",cita);
        btnPerfil.putExtra("imagen",imagen);
        btnPerfil.putExtra("telefono",telefono);

        startActivity(btnPerfil);
    }
}

