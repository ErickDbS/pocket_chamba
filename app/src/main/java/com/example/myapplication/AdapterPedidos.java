package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterPedidos extends BaseAdapter {
    Context context;
    List<listPedidos> lst;

    public AdapterPedidos(Context context, List<listPedidos> lst) {
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

        ImageView imageViewPedido;
        TextView textViewNombrePedido;
        TextView textViewFecha;

        listPedidos ly = lst.get(i);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.lypedidos, null);

        imageViewPedido = view.findViewById(R.id.imageViewPedido);
        textViewNombrePedido = view.findViewById(R.id.textViewNombrePedido);
        textViewFecha = view.findViewById(R.id.textViewFecha);

        imageViewPedido.setImageResource(ly.getImagen());
        textViewNombrePedido.setText(ly.getNombre());
        textViewFecha.setText(ly.getFecha());

        return view;
    }
}
