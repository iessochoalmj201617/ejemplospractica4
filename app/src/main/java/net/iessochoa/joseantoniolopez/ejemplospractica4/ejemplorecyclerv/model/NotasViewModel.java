package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel
 *https://developer.android.com/topic/libraries/architecture/viewmodel
 * La clase ViewModel nos permite mantener los datos en las reconstruciones. En el onCreate recuperamos el viewmodel
 * si venimos de una reconstrucción o creará uno nuevo si es nueva la app. Tenéis un ejemplo en
 * https://www.youtube.com/watch?v=9Ya3yieB8aI&t=7s,
 * https://www.youtube.com/watch?v=rOlWmK0wlJo
 * https://www.youtube.com/watch?v=A2RA36ibC4I
 * LiveData
 * https://developer.android.com/topic/libraries/architecture/livedata
 * Los datos que creamos en el viewmodel no se pierden y se mantienen en memoria
 * Los datos de tipo LiveData, nos permiten mantener observadores en el UI(la activity) para
 * detectar cuando hay cambios en los datos.
 * Mantendremos en esta clase el CRUD(altas,bajas y modificaciones) sobre la lista de Notas y al actualizar
 * el LiveData se llamará al observer creado en la activity para mostrar los datos en pantalla
 */
public class NotasViewModel  extends AndroidViewModel {
    //si queremos que la actividad reciba un aviso cuando se modifican los datos, tenemos que crear
    //un LiveData(https://developer.android.com/topic/libraries/architecture/livedata)
    //
    private MutableLiveData<List<Nota>> listaNotasLiveData;
    //esta lista se mantendrá durante la vida de la Actividad
    private List<Nota> listaNotas;
    public NotasViewModel(@NonNull Application application) {
        super(application);
        //el liveData nos permitirá recibir notificaciones  en la actividad cuando se modifique la lista
        listaNotasLiveData=new MutableLiveData<List<Nota>>();
        //creamos unos datos de ejemplo
        crearDatos();
        //avisamos de la modificación con el LiveData
        listaNotasLiveData.setValue(listaNotas);
    }
    /**
     * nos permite recuperar el LiveData para asignar el listener al Observador en la activity
     * cuando se modifican los datos
     * @return
     */
    public LiveData<List<Nota>> getNotaList(){
        return listaNotasLiveData;
    }

    /**
     * nos permite añadir una Nota a la lista
     * @param tarea
     */
    public void addNota(Nota tarea){
        //añadimos una Nota a la lista, si existe(mismo id), la sustituimos
        int i=listaNotas.indexOf(tarea);
        if(i<0)
            listaNotas.add(tarea);
        else{
            listaNotas.remove(i);
            listaNotas.add(i,tarea);
        }
        //avisamos al LiveData para que active el Observer y la actividad muestre los cambios
        listaNotasLiveData.setValue(listaNotas);
    }
    /*
    Eliminamos la nota por id
     */
    public void delNota(Nota nota){
        if(listaNotas.size()>0){
            listaNotas.remove(nota);
            //avisamos al LiveData para que active el Observer
            listaNotasLiveData.setValue(listaNotas);
        }
    }
    /**
     * creamos unos datos de muestra
     */
    private void crearDatos() {
        listaNotas=new ArrayList<Nota>();
        for(int i=0;i<12;i++) {
            Nota tarea = new Nota((int)(Math.random()*10)%2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, eget consequat ante lacinia et. Phasellus ut diam et diam euismod convallis");
            listaNotas.add(tarea);
        }


    }
}
