package net.iessochoa.joseantoniolopez.ejemplospractica4;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Este ejemplo nos permitira ver como se utilizan los menús, spinner y listview
 * Veremos como cuando cambiamos la orientación del dispositivo, si no tomamos las medidas
 * adecuadas, perdemos los datos del usuario.
 */

public class MainActivity extends AppCompatActivity {
    public static String STATE_LISTA_ELEMENTOS = "net.iessochoa.joseantoniolopez.ejemplospractica4.main.lista_elementos";
    //datos para el spinner
    final String[] datos =
            new String[]{"Elemento1","Elemento2","Elemento3","Elemento4","Elemento5"};
    Spinner sp_array, sp_conStringArray;
    ListView lvLista;
    //datos para la  lista
    ArrayList<Elemento> al_datos;
    AdapterElementos adaptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //************SPINNER******************
        sp_array=(Spinner)findViewById(R.id.sp_array);

        final ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp_array.setAdapter(adaptador);

        //*******Con string Array
        sp_conStringArray=(Spinner)findViewById(R.id.sp_conArrayString);
        ArrayAdapter<CharSequence> adaptador2 =ArrayAdapter.createFromResource(this,
                R.array.valores_array, android.R.layout.simple_spinner_item);
        sp_conStringArray.setAdapter(adaptador2);
        //***Evento selección Spinner
        sp_conStringArray.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        String ele=(String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(),getString(R.string.msg_seleccion2)+ ele,Toast.LENGTH_LONG).show();

                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        //********************************************
        //***********LISTVIEW************************
        //********************************************
        lvLista=(ListView) findViewById(R.id.lv_Lista);
        //comentar para que funcione la regeneración de datos
        crearDatos();
        //descomentar para que funcione la regeneración de datos y comentar el anterior y tambien
        //descomentar el método onSaveInstanceState
//        crearDatosComprobandoRegeneracion(savedInstanceState);
        adaptadorLista=new AdapterElementos(this,R.layout.item_elemento,al_datos);
        lvLista.setAdapter(adaptadorLista);
        //Evento de click sobre un elemento de la lista
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos un cuadro de dialogo para que veais como se implementa
                Elemento elemento=(Elemento)adapterView.getItemAtPosition(i);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle(R.string.title_Aviso);// titulo y mensaje

                dialogo.setMessage(getString(R.string.msg_seleccion)+elemento.getV1()+"-"+elemento.getV2());
                // agregamos botón Ok y su evento
                dialogo.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok

                    }
                });
                //Si queremos incluir la opción de cancelar
                /*dialogo.setNegativeButton(android.R.string.no
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Qué hacemos en caso cancel
                            }
                        });*/
                dialogo.show();
            }
        });
        //Evento para un long click
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, String.format(getString(R.string.msg_PulsacionLongLinsta), i),Toast.LENGTH_SHORT).show();

                //Importante, para que no ejecute después el evento click,  tiene que devolver true
                return true;
            }
        });

    }

    /**
     * Ejecutamos este método para comprobar que ocurre cuando no se controla
     * la recreación de la  Activity
     */
    private void crearDatos() {
        al_datos=new ArrayList<Elemento>();
            String v1 = "titulo";
            String v2 = "dato";
            String v3 = "Donec ut lorem est. Suspendisse vel porttitor turpis. Aenean gravida elit nec sodales hendrerit. Vivamus non tellus eu sapien malesuada imperdiet. Sed eget diam vitae sem mattis scelerisque. Morbi sed elementum urna. Praesent egestas, nulla sit amet porttitor eleifend";
            for (int i = 0; i <= 10; i++) {
                al_datos.add(new Elemento(v1 + i, v2 + i, v3 + i));
            }
    }

    /**
     * Nos permite comprobar previamente si estamos dentro de una recreación de la Activity
     * como probando si el Bundle que trae onCreate es null
     * @param savedInstanceState: es el parámetro que viene en onCreate
     */
    private void crearDatosComprobandoRegeneracion(Bundle savedInstanceState) {
        //al_datos=new ArrayList<Elemento>();
        //si es la primera vez que se crea la activity
        if(savedInstanceState == null) {//creamos unos datos a mostrar
            //añadimos unos cuantos datos de prueba
            crearDatos();
        }else {//Venimos de una recreación ya que tenemos datos en el bundle y recuperamos los datos
            al_datos=savedInstanceState.getParcelableArrayList(STATE_LISTA_ELEMENTOS);

        }
    }
    //******************MENUS*************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                adaptadorLista.addElemento(new Elemento("TituloNuevo","DatoNuevo","Descripcion Nueva"));
                return true;
            case R.id.action_del:
                adaptadorLista.delElemento();
                return true;
            case R.id.action_acercade:
                Toast.makeText(this, R.string.acercade,Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//*****************************CONTROL DE LA RECREACIÓN DE LA ACTIVIDAD****************
    //Descomentar para no perder los datos cuando hay una recreación de la actividad cuando
    //por ejemplo se cambia la orientación

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Guardamos la lista de elementos
        outState.putParcelableArrayList(STATE_LISTA_ELEMENTOS,adaptadorLista.getAlLista());


    }*/
}
