package net.iessochoa.joseantoniolopez.ejemplospractica4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaConReconstruccionActivity extends AppCompatActivity {
    //constante clave-valor creada para recuperar los datos en la reconstrucción.
    //fijaros que comienza por STATE_
    public static String STATE_LISTA_ELEMENTOS = "net.iessochoa.joseantoniolopez.ejemplospractica4.ListaConReconstruccionActivity.lista_elementos";

    ListView lvLista;
    //datos para la  lista
    ArrayList<Elemento> al_datos;
    ElementosAdapter adaptadorLista;
    int n=1;
//el método onCreato viene con un Bundle llamado savedInstanceState que es nulo si es la primera vez
    //que se crea la actividad y diferente de null si hemos guardado los datos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_con_reconstruccion);

        //********************************************
        //***********LISTVIEW************************
        //********************************************
        lvLista= findViewById(R.id.lv_Lista);
        //si es la primera vez que se crea la activity
        if(savedInstanceState == null) {//creamos unos datos a mostrar
            //añadimos unos cuantos datos de prueba
            crearDatos();
        }else {//Venimos de una recreación ya que tenemos datos en el bundle y recuperamos los datos
            al_datos=savedInstanceState.getParcelableArrayList(STATE_LISTA_ELEMENTOS);
        }
        adaptadorLista=new ElementosAdapter(this,R.layout.item_elemento,al_datos);
        lvLista.setAdapter(adaptadorLista);
        //Evento de click sobre un elemento de la lista
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Creamos un cuadro de dialogo para que veais como se implementa
                Elemento elemento=(Elemento)adapterView.getItemAtPosition(i);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(ListaConReconstruccionActivity.this);
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
                Toast.makeText(ListaConReconstruccionActivity.this, String.format(getString(R.string.msg_PulsacionLongLinsta), i),Toast.LENGTH_SHORT).show();

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
                adaptadorLista.addElemento(new Elemento("TituloNuevo"+n,"DatoNuevo"+n,"Descripcion Nueva"+n));
                n++;
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
    //cuando la actividad va a se destruida por ejemplo por el giro de pantalla, se llama a
    //este método
    //el Bundle que trae como parámetro nos sirve para guardar los datos que
    //no queramos perder

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Guardamos la lista de elementos. Al se un objeto complejo, lo guardamos como parcelable
        outState.putParcelableArrayList(STATE_LISTA_ELEMENTOS,adaptadorLista.getAlLista());

    }
}
