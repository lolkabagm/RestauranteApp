package com.example.kevin.restauranteapp10.Entidades;

/**
 * Created by kevin on 18/05/2015.
 */
public class PlatoComida {


    private  String nombre = "";
    private String imagen ="";
    private String descripcion = "";
    private int estrellas = 0;
    private long idPlato = 0;

    public long getId() {
        return idPlato;
    }

    public void setId(long id) {
        this.idPlato = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
}
