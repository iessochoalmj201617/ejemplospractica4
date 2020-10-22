package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv;

import android.content.Context;
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

public class NotasAdapter extends  RecyclerView.Adapter{
   // private final LayoutInflater mInflater;
    private List<Nota> mNotas; //
    //definimos la interface para el control del click
    //private OnItemClickEditarListener listenerEditar;
    private OnItemClickBorrarListener listenerBorrar;


    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        NotaViewHolder holder= (NotaViewHolder) vh;
        if (mNota != null) {
            final Nota nota = mNotas.get(position);
            //holder.tarea = tarea;
            holder.tvTecnico.setText(tarea.getTecnico());
            holder.tvResumen.setText(tarea.getResumen());
            switch (tarea.getEstado()) {
                case "Abierta":
                    holder.ivEstado.setImageResource(R.drawable.ic_abierta);
                    break;
                case "Cerrada":
                    holder.ivEstado.setImageResource(R.drawable.ic_terminada);
                    break;
                case "En curso":
                    holder.ivEstado.setImageResource(R.drawable.ic_encurso);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mNotas != null)
            return mNotas.size();
        else return 0;
    }
    public class NotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTipo;
        private TextView tvDescripcion;
        private ImageView ivBorrar;
        //private Nota nota;

        private NotaViewHolder(View itemView) {
            super(itemView);
            ivTipo = itemView.findViewById(R.id.ivTipo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            ivBorrar = itemView.findViewById(R.id.ivBorrar);
            this.
            ivBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listenerBorrar!= null)
                        listenerBorrar.onItemClickBorrar(mNotas.get((int)NotaViewHolder.this.getItemId()));
                }
            });
        }

    }
    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Nota nota);
    }
    public void setOnClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;
    }
}
