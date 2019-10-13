package net.iessochoa.joseantoniolopez.ejemplospractica4.viewmodel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.iessochoa.joseantoniolopez.ejemplospractica4.Elemento;
import net.iessochoa.joseantoniolopez.ejemplospractica4.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta actividad nos permite ver el uso de la clase ViewModel y LiveData para mantener datos durante
 * el ciclo de vida de la Actividad como parte de los nuevos patrones de diseño propuesto por Google con
 * JetPack. Para ello
 * -crearemos una clase ViewModel que nos permitirá guardar los datos durante la vida
 * de la actividad.
 * -Esta clase se ocupará por nosotros de crear o recuperar los datos en las reconstruciones de la actividad
 * -Además podremos aislar la actividad del manejo de datoss
 */
public class ListaViewModelActivity extends AppCompatActivity {
    //para crear nuevos datos en la lista
    private int n = 1;
    //El listView
    private ListView lvLista;
    //adaptador de tipo ArrayList. Necesitará un arraylist que mantendrá el ViewModel
    private ElementosVModelAdapter adapter;
    //El viewModel definido para mantener los datos(la lista) que no queramos perder en la reconstrucción
    private LiveDataElementosViewModel liveDataElementosViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_view_model);

        lvLista = (ListView) findViewById(R.id.lv_Lista);
        //creamos el LiveData. Nos permite mantener los datos en la reconstrución. El proveedor se encargará
        //por nosotros de crear uno nuevo o recuperarlo si venimos de una reconstrución
        liveDataElementosViewModel = ViewModelProviders.of(this).get(LiveDataElementosViewModel.class);
        //le añadimos un observador que nos permitirá actualizar la interfaz de usuario(la lista)
        //si cambian los datos
        liveDataElementosViewModel.getUserList().observe(this, new Observer<List<Elemento>>() {
            //recibimos la lista de los datos modificados(añadir o eliminar elementos)
            @Override
            public void onChanged(List<Elemento> elementos) {
                //si es la primera vez, creamos el adaptador
                if(adapter==null){
                    adapter=new ElementosVModelAdapter(ListaViewModelActivity.this,R.layout.item_elemento,(ArrayList<Elemento>) elementos);
                    lvLista.setAdapter(adapter);
                }else//la lista se ha modificado y se la volvemos a pasar al adaptador y actualizar
                    adapter.setLista(elementos);
            }
        });
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
                liveDataElementosViewModel.addElemento(new Elemento("TituloNuevo" + n, "DatoNuevo" + n, "Descripcion Nueva" + n));
                n++;
                return true;
            case R.id.action_del:
                liveDataElementosViewModel.delElemento();
                return true;
            case R.id.action_acercade:
                Toast.makeText(this, R.string.acercade, Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}