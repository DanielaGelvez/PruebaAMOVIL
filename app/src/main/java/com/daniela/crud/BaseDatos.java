package com.daniela.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    String tabla="CREATE TABLE DEPARTAMENTOS(ID INTEGER PRIMARY KEY NOT NULL, NOMBRE CHAR(40) NOT NULL, CODIGO CHAR(20) NOT NULL)";
    public BaseDatos(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE DEPARTAMENTOS");//Si hay una nueva versión borra la tabla
        db.execSQL(tabla);
    }

}
