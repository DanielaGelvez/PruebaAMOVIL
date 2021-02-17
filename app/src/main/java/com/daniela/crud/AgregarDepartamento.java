package com.daniela.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarDepartamento extends AppCompatActivity {

    EditText et_departamento, et_codigoDane;
    Button btn_crearDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregardepartamento);

        et_departamento = findViewById(R.id.et_nombreDeptEdit);
        et_codigoDane = findViewById(R.id.et_codDeptEdit);
        btn_crearDept = findViewById(R.id.btn_crearDept);

        btn_crearDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDpto(et_departamento.getText().toString(), et_codigoDane.getText().toString());
                onBackPressed();
            }
        });

    }


    private void crearDpto(String Nombre, String Codigo) {
        BaseDatos base = new BaseDatos(this, "Tabla",null, 1);
        SQLiteDatabase db = base.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("NOMBRE", Nombre);
            c.put("CODIGO", Codigo);
            db.insert("DEPARTAMENTOS",null, c);
            db.close();
            Toast.makeText(this,"Departamento creado", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}