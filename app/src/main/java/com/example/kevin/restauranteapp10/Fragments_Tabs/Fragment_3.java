package com.example.kevin.restauranteapp10.Fragments_Tabs;


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
import com.example.kevin.restauranteapp10.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_3 extends Fragment implements AdapterView.OnItemClickListener {

    ArrayList<Integer> arrayListImagenes= new ArrayList<Integer>();
    ArrayList<String> arrayListTitulos= new ArrayList<String>();

    private int mPhotoSize, mPhotoSpacing;
    AdapterGridView adapterGridView;
    GridView gridView;

    public ArrayList<Integer> getArrayListImagenes() {
        return arrayListImagenes;
    }

    public ArrayList<String> getArrayListTitulos() {
        return arrayListTitulos;
    }


    public Fragment_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getArrayListTitulos().add("Ensalada a la Cresta");
        getArrayListTitulos().add("Ensalada Sosa");
        getArrayListTitulos().add("Ensalada de Pepinos");
        getArrayListTitulos().add("A la Zanahoria");
        getArrayListTitulos().add("Amarilla");
        getArrayListTitulos().add("Rodajas");
        getArrayListTitulos().add("Con queso");



        getArrayListImagenes().add(R.drawable.ensalada1);
        getArrayListImagenes().add(R.drawable.ensalada2);
        getArrayListImagenes().add(R.drawable.ensalada3);
        getArrayListImagenes().add(R.drawable.ensalada4);
        getArrayListImagenes().add(R.drawable.ensalada5);
        getArrayListImagenes().add(R.drawable.ensalada6);
        getArrayListImagenes().add(R.drawable.ensalada7);



        // get the photo size and spacing
        mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);

        View rootView = inflater.inflate(R.layout.fragment_fragment_3, container, false);

        // Here we inflate the layout we created above
        gridView = (GridView) rootView.findViewById(R.id.gridView3);
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


        Toast.makeText(getActivity(), "Has pulsado la posicion: " + position,
                Toast.LENGTH_SHORT).show();

    }
    }



