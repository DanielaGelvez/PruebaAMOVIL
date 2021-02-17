package com.daniela.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    EditText et_departamento, et_codigoDane;
    Button btn_modDept, btn_eliminar;
    int id;
    String nombre, codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            id = b.getInt("ID");
            nombre = b.getString("Nombre");
            codigo = b.getString("Codigo");
        }

        et_departamento = findViewById(R.id.et_nombreDeptEdit);
        et_codigoDane = findViewById(R.id.et_codDeptEdit);
        btn_modDept = findViewById(R.id.btn_modDept);
        btn_eliminar = findViewById(R.id.btn_eliminar);

        et_departamento.setText(nombre);
        et_codigoDane.setText(codigo);


        btn_modDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id, et_departamento.getText().toString(), et_codigoDane.getText().toString());
                onBackPressed();
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });

    }

    private void Modificar(int ID, String Nombre, String Codigo) {
        BaseDatos base = new BaseDatos(this, "Tabla",null, 1);
        SQLiteDatabase db = base.getWritableDatabase();

        String SQL = "UPDATE DEPARTAMENTOS SET NOMBRE='" + Nombre + "', CODIGO='" + Codigo + "' where ID="+ID;
        db.execSQL(SQL);
        db.close();
    }

    private void Eliminar(int ID) {
        BaseDatos base = new BaseDatos(this, "Tabla",null, 1);
        SQLiteDatabase db = base.getWritableDatabase();

        String SQL = "DELETE FROM DEPARTAMENTOS WHERE ID="+ID;
        db.execSQL(SQL);
        db.close();
    }



}