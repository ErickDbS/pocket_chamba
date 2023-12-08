package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;

    List <listServicios> lst;



    public CustomAdapter(Context context, List<listServicios> lst) {
        this.context = context;
        this.lst = lst;

    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageViewServicio;
        TextView textViewNombre;
        TextView textViewDescripcion;

        listServicios ly = lst.get(i);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.lyservicios, null);

        imageViewServicio = view.findViewById(R.id.imageViewServicio);
        textViewNombre = view.findViewById(R.id.textViewNombre);
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion);

        imageViewServicio.setImageResource(ly.imagen);
        textViewNombre.setText(ly.nombre);
        textViewDescripcion.setText(ly.desc);

        //




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva instancia de listServicios con los datos actuales
                listServicios selectedService = new listServicios(ly.imagen, ly.nombre, ly.desc);

                // Crear un Intent y pasar datos a la nueva actividad
                Intent intent = new Intent(context, DetalleServicios.class);
                intent.putExtra("nombre", selectedService.getNombre());
                intent.putExtra("imagen", selectedService.getImagen());  // Usar la misma clave
                intent.putExtra("descripcion", selectedService.getDesc());

                // Iniciar la nueva actividad
                context.startActivity(intent);
            }
        });

        return view;
    }

}
