package com.example.myapplication;

public class listServicios {
    public int id;
    public int imagen;
    public String nombre;
    public String desc;

    public listServicios(int imagen, String nombre, String desc) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
