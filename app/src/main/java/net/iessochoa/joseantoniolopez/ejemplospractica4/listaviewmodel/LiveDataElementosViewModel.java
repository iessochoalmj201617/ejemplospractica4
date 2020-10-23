package net.iessochoa.joseantoniolopez.ejemplospractica4.listaviewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.iessochoa.joseantoniolopez.ejemplospractica4.Elemento;

import java.util.ArrayList;
import java.util.List;

/**
 *https://developer.android.com/topic/libraries/architecture/viewmodel
 * La clase ViewModel nos permite mantener los datos en las reconstruciones. En el onCreate recuperamos el viewmodel
 * si venimos de una reconstrucción o creará uno nuevo si es nueva la app
 */
public class LiveDataElementosViewModel extends AndroidViewModel {
    //si queremos que la actividad reciba un aviso cuando se modifican los datos, tenemos que crear
    //un LiveData
    private MutableLiveData<List<Elemento>> listaElementosLiveData;
    //esta lista se mantendrá durante la vida de la Actividad
    private List<Elemento> listaElementos;

//creamos la lista con unos datos
    public LiveDataElementosViewModel(Application application){
        super(application);
        //el liveData nos permitirá recibir notificaciones  en la actividad cuando se modifique la lista
        listaElementosLiveData=new MutableLiveData<List<Elemento>>();
        //creamos unos datos de ejemplo
        crearDatos();
        //avisamos de la modificación con el LiveData
        listaElementosLiveData.setValue(listaElementos);
    }

    /**
     * nos permite recuperar el LiveData para asignar el listener al Observador en la activity
     * cuando se modifican los datos
     * @return
     */
    public LiveData<List<Elemento>> getUserList(){
        return listaElementosLiveData;
    }

    /**
     * nos permite añadir un elemento a la lista
     * @param elemento
     */
    public void addElemento(Elemento elemento){
        //añadimos un elemento a la lista
        listaElementos.add(elemento);
        //avisamos al LiveData para que active el Observer
        listaElementosLiveData.setValue(listaElementos);
    }
    /*
    por sencillez, eliminamos el primer elemento de la lista
     */
    public void delElemento(){
        if(listaElementos.size()>0){
            listaElementos.remove(0);
            //avisamos al LiveData para que active el Observer
            listaElementosLiveData.setValue(listaElementos);
        }
    }

    /**
     * creamos unos datos de muestra
     */
    private void crearDatos() {
        listaElementos=new ArrayList<Elemento>();
        String v1 = "titulo";
        String v2 = "dato";
        String v3 = "Donec ut lorem est. Suspendisse vel porttitor turpis. Aenean gravida elit nec sodales hendrerit. Vivamus non tellus eu sapien malesuada imperdiet. Sed eget diam vitae sem mattis scelerisque. Morbi sed elementum urna. Praesent egestas, nulla sit amet porttitor eleifend";
        for (int i = 0; i <= 10; i++) {
            listaElementos.add(new Elemento(v1 + i, v2 + i, v3 + i));
        }
    }
}
