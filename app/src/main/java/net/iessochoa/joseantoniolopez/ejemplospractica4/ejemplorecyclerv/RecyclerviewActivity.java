package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iessochoa.joseantoniolopez.ejemplospractica4.R;
import net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model.Nota;
import net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv.model.NotasViewModel;

import java.util.List;

public class RecyclerviewActivity extends AppCompatActivity {
    private RecyclerView rvLista;
    private FloatingActionButton fabAdd;
    private NotasViewModel notasViewModel;
    private NotasAdapter notasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        rvLista = findViewById(R.id.rvLista);
        fabAdd = findViewById(R.id.fabAdd);
        notasAdapter = new NotasAdapter();
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(notasAdapter);
        //Recuperamos el ViewModel new ViewModelProvider(this).get(TareasViewModel.class);
        notasViewModel = new ViewModelProvider(this).get(NotasViewModel.class);
        //Este livedata nos permite ver todos los contactos y en caso de que haya un cambio en la
        //base de datos, se mostrará automáticamente
        notasViewModel.getNotaList().observe(this, new Observer<List<Nota>>() {
            @Override
            public void onChanged(List<Nota> nota) {
                notasAdapter.setNotas(nota);
            }
        });
        //************Nueva Nota***************
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nota nota=new Nota((int)(Math.random()*10)%2,"nueva nota en la lista");
                notasViewModel.addNota(nota);
            }
        });
        //********Borrar Nota***************
        notasAdapter.setOnClickBorrarListener(new NotasAdapter.OnItemClickBorrarListener() {
            @Override
            public void onItemClickBorrar(Nota nota) {
                borrarNota(nota);

            }
        });
        //**************Mostrar Nota************
        notasAdapter.setOnClickElementoListener(new NotasAdapter.OnItemClickElementoListener() {
            @Override
            public void onItemClickElemento(Nota nota) {
                verNota(nota);

            }
        });
    }

    private void borrarNota(final Nota nota) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(RecyclerviewActivity.this);
        dialogo.setTitle("Aviso");// titulo y mensaje

        dialogo.setMessage("Está seguro que desea eliminar la nota con id "+nota.getId());
// agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.yes
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok
                        notasViewModel.delNota(nota);
                    }
                });
        dialogo.setNegativeButton(android.R.string.no
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso cancel

                    }
                });
        dialogo.show();
    }

    private void verNota(Nota nota) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(RecyclerviewActivity.this);
        dialogo.setTitle("Nota "+nota.getId());// titulo y mensaje

        dialogo.setMessage(nota.getDescripcion());
// agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.ok
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok

                    }
                });

        dialogo.show();
    }
}