package com.example.kevin.restauranteapp10.BDatos;



        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
        import android.widget.Toast;

        import com.example.kevin.restauranteapp10.Entidades.PlatoComida;

/**
 * Clase que facilita el acceso a una Base de Datos SQLite creando la Base de datos a partir de un fichero
 * en la carpeta Assets
 * blog.findemor.es 06/02/2011
 **/
@SuppressWarnings("ALL")
public class BaseDatosHelper extends SQLiteOpenHelper {

    //La carpeta por defecto donde Android espera encontrar la Base de Datos de tu aplicación
    private static String DB_PATH = "/data/data/com.example.kevin.restauranteapp10/databases/";
    private static String DB_NAME = "bd";
    private SQLiteDatabase myDataBase;

    private String[] todasColumnas =new String[] {"_id","idPlato","nombre","imagen","descripcion","estrellas"};

    private final Context myContext;

    /**
     * Constructor
     *
     * Guarda una referencia al contexto para acceder a la carpeta assets de la aplicación y a los recursos
     * @param contexto
     **/
    public BaseDatosHelper(Context contexto) {

        super(contexto, DB_NAME, null, 3);
        this.myContext = contexto;
    }

    /**
     * Crea una base de datos vacia en el sistema y la sobreescribe con la que hemos puesto en Assets
     **/
    public void crearDataBase() throws IOException{

        boolean dbExist = comprobarBaseDatos();

        if(dbExist){

        }else{
            //Si no existe, creamos una nueva Base de datos en la carpeta por defecto de nuestra aplicación,
            //de esta forma el Sistema nos permitira sobreescribirla con la que tenemos en la carpeta Assets
            this.getReadableDatabase();
            try {

                copiarBaseDatos();
                System.out.println("Entra base de datos");
            } catch (IOException e) {
                throw new Error("Error al copiar la Base de Datos");
            }
        }
    }

    /**
     * Comprobamos si la base de datos existe
     * @return true si existe, false en otro caso
     **/
    private boolean comprobarBaseDatos(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //No existe
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copia la base de datos desde la carpeta Assets sobre la base de datos vacía recien creada en la carpeta del sistema,
     * desde donde es accesible
     **/
    private void copiarBaseDatos() throws IOException{

        try {
        //Abrimos la BBDD de la carpeta Assets como un InputStream
        InputStream myInput = myContext.getAssets().open("bd");

        //Carpeta de destino (donde hemos creado la BBDD vacia)
        String outFileName = DB_PATH + DB_NAME;

        //Abrimos la BBDD vacia como OutputStream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //Transfiere los Bytes entre el Stream de entrada y el de Salida
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
                   }

        //Cerramos los ficheros abiertos
        myOutput.flush();
        myOutput.close();
        myInput.close();

        } catch (FileNotFoundException e) {
            Log.e("ERROR", "Archivo no encontrado, " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", "Error al copiar la Base de Datos, " + e.toString());
        }
    }

    /**
     * Abre la base de datos
     **/
    public void abrirBaseDatos() throws SQLException{

        String myPath = DB_PATH + DB_NAME;
          myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    /**
     * Cierra la base de datos
     **/
    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //No usamos este metodo
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //No usamos este metodo
    }

    //Podemos anadir métodos  que accedan al contenido de la base de datos,
    //para realizar consultas, u operaciones CRUD (create, read, update, delete)

    /**
     *   METODOS PUBLIC PARA REALIZAR OPERACIONES CRUD
     *
     */

    //genera un contacto a partir de un objeto Cursor
    public PlatoComida cursorToplato(Cursor cursor) {
        PlatoComida platoComida = new PlatoComida();
        platoComida.setId(cursor.getLong(1));
        platoComida.setNombre(cursor.getString(2));
        platoComida.setImagen(cursor.getString(3));
        platoComida.setDescripcion(cursor.getString(4));
        platoComida.setEstrellas(cursor.getInt(5));

        return platoComida;
    }

    //consulta a la BD para obtener todos los contactos
    public Cursor getTodosContactos(String tabla) {
        return myDataBase.query(tabla, todasColumnas,null,null,null,null,null);
    }

    /**
     *   consulta de un contacto por nombre' (clave primaria)
     */
    public Cursor getContactoNombre(String nombre, String tabla) throws SQLException{
        Cursor mCursor = myDataBase.query(true, tabla, todasColumnas,
                "nombre" + "=" + nombre, null, null, null, null, null, null);
        //si hay datos devueltos, apunta al principio
        if (mCursor != null)  mCursor.moveToFirst();
        return mCursor;
    }

    public Cursor getPato(int numero,String tabla) throws SQLException{

        Cursor mCursor = myDataBase.query(true, tabla, todasColumnas,
                "idPlato" + "=" + numero,null,null,null,null,null);
        //si hay datos devueltos, apunta al principio
        if (mCursor != null)  mCursor.moveToFirst();
        return mCursor;
    }

    //consulta de un contacto por 'numero' (clave primaria)
    public Cursor getContactoNum(int num, String tabla) throws SQLException{

        Cursor mCursor = myDataBase.query(true, tabla, todasColumnas,
                "idPlato" + "=" + num, null, null, null, null, null, null);
        //si hay datos devueltos, apunta al principio
        if (mCursor != null)  mCursor.moveToFirst();
        return mCursor;
    }

    //Obtiene una lista de contactos traves de un objeto Cursor
    public List<PlatoComida> getAllplato(String tabla) {
        //Lista de contactos
        List<PlatoComida> listaPlatosComida= new ArrayList<PlatoComida>();
        //objeto cursor que se llena con el resultado de la consulta que obtiene todos los contactos
        Cursor cursor = this.getTodosContactos(tabla);
        //se posiciona al principio del cursor
        cursor.moveToFirst();
        //mientras hay datos en el cursor
        while (!cursor.isAfterLast()) {
            //genera un contacto
            PlatoComida comment = cursorToplato(cursor);
            //anade un contacto a la lista
            listaPlatosComida.add(comment);
            //avanza al siguiente
            cursor.moveToNext();
        }
        cursor.close();
        return listaPlatosComida;
    }




/*

    public void cargarBD() {
        try{
           String destPath="/data/data/com.example.kevin.restauranteapp10/databases";
            File f = new File (destPath);
            //si no existe el directorio
            if(!f.exists()){
                //crea el directorio
                f.mkdirs();
                //copiar la BD desde la carpeta res/assets a data/data/<nombre_paquete>/databases
                CopyBD(myContext.getAssets().open("db"), new FileOutputStream(destPath+"/db"));
            }else {
                File fdb =new File(destPath + "/db");
                if(!fdb.exists())
                    //copiar la BD desde la carpeta res/assets a data/data/<nombre_paquete>/databases
                    CopyBD(myContext.getAssets().open("db"), new FileOutputStream(fdb));
                else
                    Toast.makeText(myContext, "Ya existe la BD", Toast.LENGTH_SHORT).show();
            }
        }catch(FileNotFoundException e){
            System.out.println("Archivo de base de datos no existe");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //método para copiar un archivo de un lugar a otro
    public void CopyBD(InputStream is, OutputStream os){
        try{
//copia 1KB cada vez
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer))>0){
                os.write(buffer, 0 , length);
            }
            is.close();
            os.close();
        }catch (IOException e){
            System.out.println("Error en copia de base de datos");
        }
    }
*/


}