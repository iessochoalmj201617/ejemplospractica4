package net.iessochoa.joseantoniolopez.ejemplospractica4.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.iessochoa.joseantoniolopez.ejemplospractica4.Elemento;
import net.iessochoa.joseantoniolopez.ejemplospractica4.R;

import java.util.ArrayList;
import java.util.List;

public class ElementosVModelAdapter  extends ArrayAdapter<Elemento> {

    //lista de datos a mostrar
    List<Elemento> alLista;

    /**
     * Constructor de clase
     *
     * @param context:   el contexto normalmente es la Activity
     * @param resource:  el layout en el que se mostrará cada elemento

     */
    public ElementosVModelAdapter(Context context, int resource,ArrayList<Elemento> lista) {
        super(context, resource,lista);
        alLista=lista;
    }

    /**
     * Nos permite actualizar la lista e indicar al adaptador que reconstruya la lista
     * @param alLista
     */
    public void setLista(List<Elemento> alLista) {
        this.alLista = alLista;

        notifyDataSetChanged();//actualizamos el ListView
    }

    /**
     * Es la el método más importante. Nos permite asignar los datos de un elemento de la lista en
     * el listview
     *
     * @param position:    posición del elemento a mostrar
     * @param convertView: es un layout de un elemento que se deja de mostrar en pantalla y nos permite
     *                     reciclarlo para ahorrar recursos
     * @param parent:      No lo vamos a utilizar
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        //comprobamos si tenemos un layout de un item mostrado anterior y que ya no se muestra
        //y si no lo tenemos porque es null, creamos uno nuevo
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.item_elemento, null);
        }
        //elemento a mostrar de la lista
        Elemento elemento = alLista.get(position);
        //buscamos los controles del layout. Lo ideal es aplicar el patron de diseño ViewHolder para optimizar
        //  el proceso como
        //os indica http://www.sgoliver.net/blog/interfaz-de-usuario-en-android-controles-de-seleccion-iii/
        TextView tv_v1 = (TextView) item.findViewById(R.id.tv_v1);
        tv_v1.setText(elemento.getV1());
        TextView tv_v2 = (TextView) item.findViewById(R.id.tv_v2);
        tv_v2.setText(elemento.getV2());
        TextView tv_v3 = (TextView) item.findViewById(R.id.tv_v3);
        tv_v3.setText(elemento.getV3());


        return (item);
    }


}
