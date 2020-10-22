package net.iessochoa.joseantoniolopez.ejemplospractica4.ejemplorecyclerv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iessochoa.joseantoniolopez.ejemplospractica4.R;

public class RecyclerviewActivity extends AppCompatActivity {
    private RecyclerView rvLista;
    private FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        rvLista = findViewById(R.id.rvLista);
        fabAdd = findViewById(R.id.fabAdd);


    }
}