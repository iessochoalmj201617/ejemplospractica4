package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joseantoniolopez.ejemplospractica4.R;
import net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model.Nota;

import java.util.List;

/**
 * Adaptador del RecyclerView
 * https://www.youtube.com/watch?v=yM7UL-C3gE8
 * https://www.youtube.com/watch?v=0E71FuATqO0
 */
public class NotasAdapter extends RecyclerView.Adapter {
    // Lista con las notas que vamos a mostrar;
    private List<Nota> mListaNotas; //
    //definimos la interface para el control del click
    //Este nos permitirá detectar cuando el usuario pulsa en el icono borrar;
    private OnItemClickBorrarListener listenerBorrar;
    //Este nos permite de forma genérica detectar el click sobre el elemento
    private OnItemClickElementoListener listenerClickElemento;

    /**
     * Cuando el adaptador necesita un nuevo item en la lista crea uno
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //fijaros que le pasamos el layout que representa cada elemento
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(itemView);
    }

    /**
     * Cuando el adaptador va a mostrar un nuevo item, llama a este método y nos indica la posición en la lista
     * del elemento a mostrar y nos pasa el holder o view donde mostrar los datos
     * @param vh
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        NotaViewHolder holder = (NotaViewHolder) vh;
        if (mListaNotas != null) {
            //recuperamos la nota a mostrar
            final Nota nota = mListaNotas.get(position);
            //mostramos los datos;
            holder.tvDescripcion.setText(nota.getId()+"-"+nota.getDescripcion());
            //en función del tipo de nota, mostramos un icono u otro
            switch (nota.getTipo()) {
                case 0:
                    holder.ivTipo.setImageResource(R.drawable.ic_importante);
                    break;
                case 1:
                    holder.ivTipo.setImageResource(R.drawable.ic_normal);
                    break;
            }
        }
    }

    /**
     * el adaptador necesita saber el número de elementos a mostrar
     * @return
     */
    @Override
    public int getItemCount() {
        if (mListaNotas != null)
            return mListaNotas.size();
        else return 0;
    }

    /**
     * Este método nos permite asignar la lista al adaptador si hay cambios
     * y avisar para que reconstruya la lista
     * @param notaList
     */
    public void setNotas(List<Nota> notaList) {
        mListaNotas = notaList;
        //notificamos de cambios y se reconstruye la lista
        notifyDataSetChanged();
    }

    /**
     * Esta clase representa cada item, y nos permite mantener los controles o view que
     * queremos mostrar así como asignar listener de eventos sobre cada item
     */
    public class NotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivTipo;
        private TextView tvDescripcion;
        private ImageView ivBorrar;
        //podriamos mantener la nota que se muestra actualmente si fuera necesario
        //private Nota nota;

        private NotaViewHolder(View itemView) {
            super(itemView);
            //Buscamos los ids del item/layout item_notas.xml
            ivTipo = itemView.findViewById(R.id.ivTipo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            ivBorrar = itemView.findViewById(R.id.ivBorrar);
            //asignamos un listener al icono borrar, que se programará en la activity
            ivBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listenerBorrar != null)
                        //si se pulsa al icono borrar, le pasamos la nota. Podemos saber la posición del item en la lista
                        listenerBorrar.onItemClickBorrar(mListaNotas.get( NotaViewHolder.this.getAdapterPosition()));
                }
            });
            //si únicamente necesitamos un evento click sobre el elemento podemos definir un listener sobre el ViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listenerClickElemento != null)
                        listenerClickElemento.onItemClickElemento(mListaNotas.get( NotaViewHolder.this.getAdapterPosition()));
                }
            });
        }

    }

    /**
     * definimos las interface necesarias para las acciones que necesitemos sobre cada item que luego se
     * programarán en la activity
     */
    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Nota nota);
    }
    public interface OnItemClickElementoListener{
        void onItemClickElemento(Nota nota);
    }

    /**
     * permiten crear el listener de acción
     * @param listener
     */
    public void setOnClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;
    }
    public void setOnClickElementoListener(OnItemClickElementoListener listener) {
        this.listenerClickElemento = listener;
    }
}
