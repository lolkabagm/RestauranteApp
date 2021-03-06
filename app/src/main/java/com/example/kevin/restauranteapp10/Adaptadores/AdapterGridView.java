package com.example.kevin.restauranteapp10.Adaptadores;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.restauranteapp10.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AdapterGridView extends BaseAdapter {

   ArrayList<Integer> arrayImagenes= new ArrayList<Integer>();
    ArrayList<String> arrayTitulos= new ArrayList<String>();

    private LayoutInflater mInflater;
    private int mItemHeight = 0;
    private int mNumColumns = 0;
    private RelativeLayout.LayoutParams mImageViewLayoutParams;

    public AdapterGridView(Context context, ArrayList<String> titulo, ArrayList<Integer> imagenes) {
        arrayImagenes = imagenes;
        arrayTitulos = titulo;

        mInflater = LayoutInflater.from(context);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mImageViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public int getCount() {
        return arrayTitulos.size();
    }

    // set numcols
    public void setNumColumns(int numColumns) {
        mNumColumns = numColumns;
    }

    public int getNumColumns() {
        return mNumColumns;
    }

    // set photo item height
    public void setItemHeight(int height) {
        if (height == mItemHeight) {
            return;
        }
        mItemHeight = height;
        mImageViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {

        if (view == null) {
            view = mInflater.inflate(R.layout.photo_item, parent, false);
            view.setTag(R.id.imagen, view.findViewById(R.id.imagen));
            view.setTag(R.id.title, view.findViewById(R.id.title));
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.imagen);
        TextView title = (TextView) view.findViewById(R.id.title);

        imageView.setLayoutParams(mImageViewLayoutParams);

        // Check the height matches our calculated column width
        if (imageView.getLayoutParams().height != mItemHeight) {
            imageView.setLayoutParams(mImageViewLayoutParams);
        }

        imageView.setImageResource(arrayImagenes.get(position));
        title.setText(arrayTitulos.get(position));

        return view;

    }
}