package com.example.kevin.restauranteapp10.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kevin.restauranteapp10.Fragments_Tabs.Fragment_1;
import com.example.kevin.restauranteapp10.Fragments_Tabs.Fragment_2;
import com.example.kevin.restauranteapp10.Fragments_Tabs.Fragment_3;
import com.example.kevin.restauranteapp10.Fragments_Tabs.Fragment_4;

/**
 * Created by kevin on 06/05/2015.
 */
    public class AdapterViewPager extends FragmentStatePagerAdapter {

        public AdapterViewPager(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new Fragment_1();
                case 1:
                    return new Fragment_2();
                case 2:
                    return new Fragment_3();
                case 3:
                    return new Fragment_4();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 4;
        }

    public CharSequence getPageTitle(int position)
    {
        String titulo = "";
        switch (position)
        {
            case 0:
                return titulo = "Arroces";
            case 1:
                return titulo = "Pescados";
            case 2:
                return titulo = "Ensaladas";
            case 3:
                return titulo = "Postres";
        }
        return null;
    }
    }

