package com.example.kevin.restauranteapp10.Activitys_NavDrawer;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.restauranteapp10.BDatos.BaseDatosHelper;
import com.example.kevin.restauranteapp10.Entidades.PlatoComida;
import com.example.kevin.restauranteapp10.R;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLOutput;


public class MainActivity2Activity extends ActionBarActivity {
    BaseDatosHelper baseDatosHelper;
    TextView textView ;
    ImageView imageView;
    String nombreTabla ="";
    Integer posicion ;
    PlatoComida platoComida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        setContentView(R.layout.activity_main_activity2);
        textView = (TextView)findViewById(R.id.txtDescripcion);
        imageView=(ImageView)findViewById(R.id.imageView2);
           actionBar.setDisplayHomeAsUpEnabled(true);
        platoComida = new PlatoComida();
        Intent i = getIntent();
        nombreTabla = i.getExtras().getString("view");
         posicion =  i.getExtras().getInt("id");

        // obtengo los datos de la base de datos del plato seleccionado en el grid view.
       platoComida = getPlato();

       this.setTitle(platoComida.getNombre());  // Modifico el titulo del activity con el nombre del plato

        imageView.setImageDrawable(this.getResources().getDrawable(this.getResources().getIdentifier(platoComida.getImagen(), "drawable", getPackageName())));
            System.out.println("ENTRA por imgen");

        textView.setText(platoComida.getDescripcion());


        //   Fragment fragment = new Fragment_1();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *  Obtengo el plato seleccionado.
     * @return
     */
    public PlatoComida getPlato() {
        baseDatosHelper = new BaseDatosHelper(this);
        PlatoComida platoComida = new PlatoComida();
        Cursor cursor ;
        //Abrimos una conexiònZ
        baseDatosHelper.abrirBaseDatos();

        cursor = baseDatosHelper.getPato(posicion, nombreTabla);

      platoComida =  baseDatosHelper.cursorToplato(cursor);


        baseDatosHelper.close();
        //Devolvemos los datos
        return platoComida;
    }

    /**
     *  Convertir una cadena de texto en string
     *
     * @param dir - Cadena de caracteres a convertir.
     * @return
     * @throws IllegalAccessException
     */
    public int  obtener_drawable(String dir) throws IllegalAccessException {

        int id = getResources().getIdentifier(dir, "drawable", getPackageName());


      return id;
    }

}
