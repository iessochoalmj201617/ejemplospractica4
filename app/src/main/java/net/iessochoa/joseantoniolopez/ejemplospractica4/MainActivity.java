package net.iessochoa.joseantoniolopez.ejemplospractica4;

import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.iessochoa.joseantoniolopez.ejemplospractica4.viewmodel.ListaViewModelActivity;

import java.util.ArrayList;

/**
 * Este ejemplo nos permitira ver como se utilizan los menús, spinner y listview
 * Veremos como cuando cambiamos la orientación del dispositivo, si no tomamos las medidas
 * adecuadas, perdemos los datos del usuario.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void accionBotones(View v){
        Class claseActivity=null;
        switch (v.getId()){
            case R.id.btnSpinner:
                claseActivity=SpinnerActivity.class;
                break;
            case R.id.btnSinReconstruccion:
                claseActivity=ListaSinReconstruccionActivity.class;
                break;
            case R.id.btnListaConRecostruccion:
                claseActivity=ListaConReconstruccionActivity.class;
                break;
            case R.id.btnViewModel:
                claseActivity= ListaViewModelActivity.class;
        }
        Intent intent;
        intent = new Intent(this, claseActivity);
        startActivity(intent);
    }
    //******************MENUS*************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(this, R.string.anyadir,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_del:
                Toast.makeText(this, R.string.eliminar,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_acercade:
                Toast.makeText(this, R.string.acercade,Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
