package com.daniela.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView lista;
    ArrayList<String> listado;
    EditText et_Busqueda;
    Button bt_agregarDept;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        lista = findViewById(R.id.listado_dpts);
        et_Busqueda = findViewById(R.id.et_Buscar);
        bt_agregarDept = findViewById(R.id.btn_agrDept);

        CargarListado(true);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Listado.this,listado.get(position), Toast.LENGTH_SHORT).show();
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                String codigo = listado.get(position).split(" ")[2];
                Intent intent = new Intent(Listado.this, Modificar.class);
                intent.putExtra("ID",clave);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Codigo",codigo);
                startActivity(intent);
            }
        });

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        et_Busqueda.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    CargarListado(false);
                    return true;
                }
                return false;
            }
        });

        bt_agregarDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Listado.this, AgregarDepartamento.class));
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListado(Boolean X) {
        if(X){
            listado = ListaDepartamentos();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listado);
            lista.setAdapter(adapter);
        } else {
            listado = Buscar(et_Busqueda.getText().toString());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listado);
            lista.setAdapter(adapter);
        }

    }

    private ArrayList<String> ListaDepartamentos(){
        ArrayList<String> depts = new ArrayList<String>();
        BaseDatos base = new BaseDatos(this, "Tabla",null, 1);
        SQLiteDatabase db = base.getReadableDatabase();
        String SQL = "SELECT ID, NOMBRE, CODIGO FROM DEPARTAMENTOS";
        Cursor c = db.rawQuery(SQL, null);
        if(c.moveToFirst()) {
            do {
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                depts.add(linea);

            } while (c.moveToNext());
        }
        db.close();
        return depts;
    }

    private ArrayList<String> Buscar(String Dato) {
        ArrayList<String> busqueda = new ArrayList<String>();
        BaseDatos base = new BaseDatos(this, "Tabla",null, 1);
        SQLiteDatabase db = base.getWritableDatabase();

        String SQL = "SELECT ID, NOMBRE, CODIGO FROM DEPARTAMENTOS WHERE NOMBRE='"+Dato+"'";
        Cursor c = db.rawQuery(SQL, null);
        if(c.moveToFirst()) {
            do {
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                busqueda.add(linea);

            } while (c.moveToNext());
        }
        db.close();
        return busqueda;
    }
}