package net.iessochoa.joseantoniolopez.ejemplospractica4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    //datos para el spinner
    final String[] datos =
            new String[]{"Elemento1","Elemento2","Elemento3","Elemento4","Elemento5"};
    Spinner sp_array, sp_conStringArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        //************SPINNER******************
        sp_array=(Spinner)findViewById(R.id.sp_array);

        final ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp_array.setAdapter(adaptador);

        //*******Con string Array
        sp_conStringArray=(Spinner)findViewById(R.id.sp_conArrayString);
        ArrayAdapter<CharSequence> adaptador2 =ArrayAdapter.createFromResource(this,
                R.array.valores_array, android.R.layout.simple_spinner_item);
        sp_conStringArray.setAdapter(adaptador2);
        //***Evento selección Spinner
        sp_conStringArray.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        String ele=(String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(),getString(R.string.msg_seleccion2)+ ele,Toast.LENGTH_LONG).show();

                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

    }

}
