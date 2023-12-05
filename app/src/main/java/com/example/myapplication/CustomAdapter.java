package com.example.myapplication;

import android.content.Context;
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

        listServicios li = lst.get(i);

        if (view==null)
            view= LayoutInflater.from(context).inflate(R.layout.lyservicios,null);

        imageViewServicio = view.findViewById(R.id.imageViewServicio);
        textViewNombre = view.findViewById(R.id.textViewNombre);
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion);

        imageViewServicio.setImageResource(li.imagen);
        textViewNombre.setText(li.nombre);
        textViewDescripcion.setText(li.desc);

        return view;
    }
}
