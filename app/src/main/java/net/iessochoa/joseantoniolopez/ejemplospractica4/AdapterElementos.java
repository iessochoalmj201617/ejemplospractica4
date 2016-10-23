package net.iessochoa.joseantoniolopez.ejemplospractica4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseA on 23/10/2016.
 */

public class AdapterElementos extends ArrayAdapter<Elemento> {


    ArrayList <Elemento> alLista;

    public AdapterElementos(Context context, int resource, ArrayList <Elemento> objects) {
        super(context, resource, objects);
        alLista=objects;

    }
    public ArrayList<Elemento> getAlLista() {
        return alLista;
    }

    public void setAlLista(ArrayList<Elemento> alLista) {
        this.alLista = alLista;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_elemento, null);

        Elemento elemento=alLista.get(position);

        TextView tv_v1 = (TextView)item.findViewById(R.id.tv_v1);
        tv_v1.setText(elemento.getV1());
        TextView tv_v2 = (TextView)item.findViewById(R.id.tv_v2);
        tv_v2.setText(elemento.getV2());
        TextView tv_v3 = (TextView)item.findViewById(R.id.tv_v3);
        tv_v3.setText(elemento.getV3());


        return(item);
    }
    public void addElemento(Elemento elemento){
        alLista.add(elemento);
        this.notifyDataSetChanged();
    }

    public void delElemento() {
        alLista.remove(0);
        this.notifyDataSetChanged();
    }
}
