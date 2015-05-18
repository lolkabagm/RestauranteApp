package com.example.kevin.restauranteapp10;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.support.v7.widget.SearchView;

import com.example.kevin.restauranteapp10.Adaptadores.AdapterViewPager;
import com.example.kevin.restauranteapp10.Adaptadores.NavigationAdapter;

import com.example.kevin.restauranteapp10.Fragments_Tabs.Item_Nav;

import java.util.ArrayList;

import static com.example.kevin.restauranteapp10.R.drawable.ic_action_drawer;
import static com.example.kevin.restauranteapp10.R.id.contenedorPrincipal;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener, SearchView.OnQueryTextListener {

    ActionBar actionBarActivity;


    /**
     *   VARIABLES NAVIGATION DRAWER
     */
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    ListView listView ;
    private CharSequence tituloSec;
    FragmentManager fragmentManager;

    SearchView mSearchView;

    PagerAdapter adapter ;

    private TypedArray NavIcons;
    private String[] titulos;
    private ArrayList<Item_Nav> NavItms;
    NavigationAdapter NavAdapter;


    /**
     *   VARIABLES TABS SWIPE
     */

    private ViewPager mViewPager;
    AdapterViewPager mAdapterViewPager;

    Integer pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBarActivity = getSupportActionBar();






        /*
            CONFIGURACION DEL NAVIGATION DRAWER

            - Lista de opciones del navigation..
            - Drawer
            - Listview
            - Adaptador de la lista.
            - Listener de las opciones del navigation drawer
         */

       //final String[] opciones = getResources().getStringArray(R.array.opcionesNav);
        drawerLayout = (DrawerLayout) findViewById(contenedorPrincipal);
        listView = (ListView) findViewById(R.id.menuIzq);
        actionBarActivity.setIcon(R.drawable.ic_app);

        //Declaramos el header el caul sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header, null);
        //Establecemos header
        listView.addHeaderView(header);

        //Tomamos listado  de imgs desde drawable
        NavIcons = getResources().obtainTypedArray(R.array.iconosNav);

        //Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
        titulos = getResources().getStringArray(R.array.opcionesNav);

        //Listado de titulos de barra de navegacion
        NavItms = new ArrayList<Item_Nav>();
        //Agregamos objetos Item_objct al array

        for (int i = 0; i < titulos.length; i++)
        {
          NavItms.add(new Item_Nav(titulos[i], NavIcons.getResourceId(i,-1)));

        }

        //Declaramos y seteamos nuestrp adaptador al cual le pasamos el array con los titulos
        NavAdapter= new NavigationAdapter(this,NavItms);
        listView.setAdapter(NavAdapter);

        //listView.setAdapter(new ArrayAdapter<>(actionBarActivity.getThemedContext(), android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = null;
                Intent intent;
                String dir = "Ctra. Níjar-San José, 22";
                actionBarActivity.setSubtitle("");

                switch (position) {

                    case 1:

                       intent= new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + dir));
                        //lanzar el intent
                        startActivity(intent);
                        break;

                 case 2:
                       intent = new Intent(MainActivity.this, MainActivity2Activity.class);
                        // passing array index
                        intent.putExtra("id", position);
                        intent.putExtra("view", "arroces");
                        startActivity(intent);
                        break;
                    case 3:
                       intent = new Intent(MainActivity.this, MainActivity2Activity.class);
                        // passing array index
                        intent.putExtra("id", position);
                        intent.putExtra("view", "arroces");
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, MainActivity2Activity.class);
                        // passing array index
                        intent.putExtra("id", position);
                        intent.putExtra("view", "arroces");
                        startActivity( intent);
                        break;

                }

             /*  fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();
*/
                listView.setItemChecked(position, true);
               // actionBarActivity.setSubtitle(tituloSec);
                //drawerLayout.closeDrawer(listView);
            }
        });


        /**
         * HABILITAR ICONO DE LA APLICACION PARA EL NAVIGATION DRAWER
       /*  */
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_action_drawer,
                R.string.abierto,
                R.string.cerrado) {

            /**
             * METODOS PARA MANEJAR LOS EVENTOS DE APERTURA Y CIERRE DEL NAVIGATION DRAWER
              */
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
                //actionBarActivity.setSubtitle(tituloSec);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
                //actionBarActivity.setSubtitle(" ");
            }
        };
        //Seteamos la escucha
       // drawerLayout.setDrawerListener(drawerToggle);
        actionBarActivity.setDisplayHomeAsUpEnabled(true);
        actionBarActivity.setHomeButtonEnabled(true);


        /**
         *  TABS SWIPE .. View pager.
         */

             adapter = new AdapterViewPager( getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.pager1);

            mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBarActivity.setSelectedNavigationItem(position);

                // Vaya cambiando el hint de Buscar, por el nombre de la tab seleccionada.
                mSearchView.setQueryHint("Buscar " + adapter.getPageTitle(position));


            }
        });

        actionBarActivity.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (int i = 0; i < 4; i++)
        {
            actionBarActivity.addTab(actionBarActivity.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this));


        }



           /* actionBarActivity.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            ActionBar.Tab tab = actionBarActivity.newTab().setText(adapter.getPageTitle(0)).setTabListener(this);
            actionBarActivity.addTab(tab);

            tab = actionBarActivity.newTab().setText(adapter.getPageTitle(1)).setTabListener(this);
            actionBarActivity.addTab(tab);

            tab = actionBarActivity.newTab().setText(adapter.getPageTitle(2)).setTabListener(this);
            actionBarActivity.addTab(tab);

            tab = actionBarActivity.newTab().setText(adapter.getPageTitle(3)).setTabListener(this);
            actionBarActivity.addTab(tab);*/




    }


    /**
     *  INFLAMOS EL MENU DE LA APP
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /**
         *  Item buscar de la barra de acciones.
         */
        MenuItem mBuscarItem = menu.findItem(R.id.buscar);
        SearchManager mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) mBuscarItem.getActionView();
        mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(getComponentName()));

        mSearchView.setQueryHint("Buscar " + adapter.getPageTitle(actionBarActivity.getSelectedNavigationIndex()).toString());
        mSearchView.setOnQueryTextListener(this);


        /**
         *  item compartir de la barra de acciones.
         */
        MenuItem mCompartirItem = menu.findItem(R.id.compartir);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(mCompartirItem);
        shareActionProvider.setShareIntent(compartirApp());

        return true;
    }

    private Intent compartirApp (){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Descargar app de pruebas de Kevin xD");
        intent.setType("text/plain");
        return intent;
    }

    /*
      OPCIONES DEL MENU
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (drawerToggle.onOptionsItemSelected(item)) {

            return true;
        }

     /*   switch (item.getItemId()) {

            case R.id.compartir:
                Toast.makeText(getApplicationContext(), "Compartir", Toast.LENGTH_SHORT).show();
                return true;

        }*/
        return super.onOptionsItemSelected(item);
    }



    /**
     *    METODOS DE NAVIGATION DRAWER
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override //Ociltar los iconos cuando se abre el menu
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    /**
     *    METODOS DE TAB SWIPE Y INTERFACE ONPAGECHANGELISTENER
     */

    // INTERFACE ONPAGECHANGELISTENER
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        getSupportActionBar().setSelectedNavigationItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    // INTERFACE ACTIONBAR.TABLISTENER

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fT) {

        // La pagina que este seleccionada con el view pager, que tambien se marque el Tab correspondiente.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        return false;
    }
}
