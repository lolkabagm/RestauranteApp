package com.example.kevin.restauranteapp10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;


public class MainActivity2Activity extends ActionBarActivity {

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        textView = (TextView)findViewById(R.id.title2);
        Intent i = getIntent();


        // Selected image id
        int position = i.getExtras().getInt("id");
        String cadena = i.getExtras().getString("view");
        String cad = String.valueOf(position);
        this.setTitle(cadena);
        textView.setText("Hola Kevin" + cad);

     //   Fragment fragment = new Fragment_1();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
