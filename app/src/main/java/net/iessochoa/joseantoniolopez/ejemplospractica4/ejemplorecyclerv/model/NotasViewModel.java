package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class NotasViewModel  extends AndroidViewModel {
    //si queremos que la actividad reciba un aviso cuando se modifican los datos, tenemos que crear
    //un LiveData
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
     * nos permite añadir un Nota a la lista
     * @param tarea
     */
    public void addNota(Nota tarea){
        //añadimos una Nota a la lista
        int i=listaNotas.indexOf(tarea);
        if(i<0)
            listaNotas.add(tarea);
        else{
            listaNotas.remove(i);
            listaNotas.add(i,tarea);
        }

        //avisamos al LiveData para que active el Observer
        listaNotasLiveData.setValue(listaNotas);
    }
    /*
    por sencillez, eliminamos el primer Nota de la lista
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
