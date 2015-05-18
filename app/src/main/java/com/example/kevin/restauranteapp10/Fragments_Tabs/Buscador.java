package com.example.kevin.restauranteapp10.Fragments_Tabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.kevin.restauranteapp10.Adaptadores.AdapterGridView;
import com.example.kevin.restauranteapp10.MainActivity2Activity;
import com.example.kevin.restauranteapp10.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buscador extends Fragment implements AdapterView.OnItemClickListener {

    private int mPhotoSize, mPhotoSpacing;
    AdapterGridView adapterGridView;
    GridView gridView;

    ArrayList<Integer> arrayListImagenes= new ArrayList<Integer>();
    ArrayList<String> arrayListTitulos= new ArrayList<String>();

    public ArrayList<Integer> getArrayListImagenes() {
        return arrayListImagenes;
    }

    public ArrayList<String> getArrayListTitulos() {
        return arrayListTitulos;
    }

    public Buscador() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getArrayListTitulos().add("Arroz Blanco");
        getArrayListTitulos().add("Arroz Negro");
        getArrayListTitulos().add("Arroz Malo");
        getArrayListTitulos().add("Arroz Malito");
        getArrayListTitulos().add("Arroz Feo");
        getArrayListTitulos().add("Arroz Crijiente");
        getArrayListTitulos().add("Arroz Liquido");
        getArrayListTitulos().add("Arroz Turco");



        getArrayListImagenes().add(R.drawable.arroz_1);
        getArrayListImagenes().add(R.drawable.arroz_2);
        getArrayListImagenes().add(R.drawable.arroz_3);
        getArrayListImagenes().add(R.drawable.arroz_4);
        getArrayListImagenes().add(R.drawable.arroz_5);
        getArrayListImagenes().add(R.drawable.arroz_6);
        getArrayListImagenes().add(R.drawable.arroz_7);
        getArrayListImagenes().add(R.drawable.arroz_8);
        getArrayListImagenes().add(R.drawable.arroz_9);


        // get the photo size and spacing
        mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);

        View rootView = inflater.inflate(R.layout.fragment_fragment_1, container, false);

        // Here we inflate the layout we created above
        gridView = (GridView) rootView.findViewById(R.id.gridView1);
        gridView.setAdapter(new AdapterGridView(getActivity(), getArrayListTitulos(), getArrayListImagenes()));
        gridView.setOnItemClickListener(this);


       gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (gridView.getNumColumns() == 0) {
                    final int numColumns = (int) Math.floor(gridView.getWidth() / (mPhotoSize + mPhotoSpacing));
                    if (numColumns > 0) {
                         int columnWidth = (gridView.getWidth() / numColumns) - mPhotoSpacing;
                        adapterGridView.setNumColumns(numColumns);
                        adapterGridView.setItemHeight(columnWidth);

                    }
                }
            }
        });

        return rootView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), "Has pulsado la posicion: " + position + parent.toString(),
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getActivity(), MainActivity2Activity.class);
        // passing array index
        i.putExtra("id", position);
        i.putExtra("view", "arroces");
        startActivity(i);

    }
}
