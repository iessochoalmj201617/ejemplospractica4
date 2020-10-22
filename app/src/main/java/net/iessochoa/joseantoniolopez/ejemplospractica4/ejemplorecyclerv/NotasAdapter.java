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

public class NotasAdapter extends RecyclerView.Adapter {
    // private final LayoutInflater mInflater;
    private List<Nota> mNotas; //
    //definimos la interface para el control del click
    //private OnItemClickEditarListener listenerEditar;
    private OnItemClickBorrarListener listenerBorrar;
    private OnItemClickElementoListener listenerClickElemento;


    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        NotaViewHolder holder = (NotaViewHolder) vh;
        if (mNotas != null) {
            final Nota nota = mNotas.get(position);
            //holder.tarea = tarea;
            holder.tvDescripcion.setText(nota.getDescripcion());
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

    @Override
    public int getItemCount() {
        if (mNotas != null)
            return mNotas.size();
        else return 0;
    }
    public void setNotas(List<Nota> notaList) {
        mNotas = notaList;
        notifyDataSetChanged();
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

            ivBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listenerBorrar != null)
                        listenerBorrar.onItemClickBorrar(mNotas.get( NotaViewHolder.this.getAdapterPosition()));
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listenerClickElemento != null)
                        listenerClickElemento.onItemClickElemento(mNotas.get( NotaViewHolder.this.getAdapterPosition()));
                }
            });
        }

    }

    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Nota nota);
    }
    public interface OnItemClickElementoListener{
        void onItemClickElemento(Nota nota);
    }
    public void setOnClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;
    }
    public void setOnClickElementoListener(OnItemClickElementoListener listener) {
        this.listenerClickElemento = listener;
    }
}
