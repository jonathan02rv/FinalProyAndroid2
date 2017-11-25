package com.example.jhona.clothesshopapp.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jhonahome on 24/11/17.
 */

public class MyDatabase extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "BDCarro";

    public static final String TABLE_CARRO = "tb_producto";

    //Columnas de la Tabla Notes
    public static final String KEY_ID = "id";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_NAME = "nombre";
    public static final String KEY_CANT = "cantidad";
    public static final String KEY_TOTAL = "total";
    public static final String KEY_ESTADO = "estado";


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql= "CREATE TABLE " + TABLE_CARRO + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_BRAND + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_CANT + " TEXT,"
                + KEY_TOTAL +  " TEXT,"
                + KEY_ESTADO + " INTEGER" + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql= "DROP TABLE IF EXISTS " + TABLE_CARRO;
        db.execSQL(sql);
    }

}