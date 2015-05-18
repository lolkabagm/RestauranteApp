package com.example.kevin.restauranteapp10.Fragments_Tabs;

/**
 * Created by kevin on 17/05/2015.
 */
public class Item_Nav {

    private String titulo;
    private int icono;
    public Item_Nav(String title, int icon) {
        this.titulo = title;
        this.icono = icon;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getIcono() {
        return icono;
    }
    public void setIcono(int icono) {
        this.icono = icono;
    }
}
