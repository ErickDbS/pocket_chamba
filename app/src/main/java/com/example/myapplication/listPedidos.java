package com.example.myapplication;
public class listPedidos {
    private int imagen;
    private String nombre;
    private String Fecha;

    public listPedidos(int imagen, String nombre, String fecha) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.Fecha = fecha;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

}
