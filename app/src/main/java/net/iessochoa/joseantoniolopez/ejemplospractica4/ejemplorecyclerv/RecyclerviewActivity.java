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
    //nos permite mantener los datos ante reconstrucciones de la activity
    private NotasViewModel notasViewModel;
    //adaptador del recyclerView
    private NotasAdapter notasAdapter;
    //para crear nuevas notas de ejemplo con descripción diferente
    private int cuentaNotas =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        rvLista = findViewById(R.id.rvLista);
        fabAdd = findViewById(R.id.fabAdd);
        //***********RecyclerView*******************
        notasAdapter = new NotasAdapter();
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(notasAdapter);
        //*********ViewModel**************************
        //Recuperamos el ViewModel. Si fuera la primera vez, se crearía nuevo;
        notasViewModel = new ViewModelProvider(this).get(NotasViewModel.class);
        //Hemos creado la lista como un LiveData para que si hay cambios en la lista,
        // se muestren automáticamente
        notasViewModel.getNotaList().observe(this, new Observer<List<Nota>>() {
            @Override
            public void onChanged(List<Nota> nota) {
                //actualizamos el recyclerView si hay cambios en la lista de Notas
                notasAdapter.setNotas(nota);
            }
        });
        //*************CRUD***********************
        //************Nueva Nota***************
        //Al añadir una nota a la lista, activará el Observer anterior
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Por simplificar el código, el tipo es 0 si es importante y 1 si es normal
                //lo asignamos aleatorio
                final int tipo=(int)(Math.random()*2);
                Nota nota=new Nota(tipo,"nueva nota en la lista "+(cuentaNotas++));
                notasViewModel.addNota(nota);
            }
        });
        //********Borrar Nota***************
        //creamos el listener que se activará cuando de pulse sobre el icono de Borrar
        notasAdapter.setOnClickBorrarListener(new NotasAdapter.OnItemClickBorrarListener() {
            @Override
            public void onItemClickBorrar(Nota nota) {
                //mostramos un dialogo para preguntar si borramos y
                //llamamos al ViewModel para que actualice la lista
                borrarNota(nota);

            }
        });
        //**************Mostrar Nota************
        //mostramos la nota cuando se pulse sobre la nota
        notasAdapter.setOnClickElementoListener(new NotasAdapter.OnItemClickElementoListener() {
            @Override
            public void onItemClickElemento(Nota nota) {
                verNota(nota);

            }
        });
    }

    /**
     * Permite borrar la nota, previamente muestra un dialogo para asegurar al usuario
     * que desea borrarla
     * @param nota
     */
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
                        notasViewModel.delNota(nota);                    }
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
    /**
     * Mostramos un dialogo con la nota
     * @param nota
     */
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