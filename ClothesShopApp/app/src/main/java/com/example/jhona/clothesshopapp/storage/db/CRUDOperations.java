package com.example.jhona.clothesshopapp.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.jhona.clothesshopapp.storage.request.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonahome on 24/11/17.
 */

public class CRUDOperations {

    private MyDatabase helper;
    public CRUDOperations(SQLiteOpenHelper _helper) {
        super();
        // TODO Auto-generated constructor stub
        helper =(MyDatabase)_helper;
    }

    public void addProducto(ProductEntity productoEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_BRAND, productoEntity.getBrand());
        values.put(MyDatabase.KEY_NAME, productoEntity.getNombre());
        values.put(MyDatabase.KEY_CANT, productoEntity.getCantidad());
        values.put(MyDatabase.KEY_TOTAL, productoEntity.getTotal());
        values.put(MyDatabase.KEY_ESTADO, productoEntity.getEstado());

        db.insert(MyDatabase.TABLE_CARRO, null, values);
        db.close();
    }

    public ProductEntity getProducto(String id)
    {
        SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(MyDatabase.TABLE_CARRO,
                new String[]{
                        MyDatabase.KEY_ID,MyDatabase.KEY_BRAND, MyDatabase.KEY_NAME,
                        MyDatabase.KEY_CANT, MyDatabase.KEY_TOTAL, MyDatabase.KEY_ESTADO},

                MyDatabase.KEY_ID + "=?",

                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        Integer nid = cursor.getInt(0);
        String brand = cursor.getString(1);
        String name = cursor.getString(2);
        String cantidad = cursor.getString(3);
        String total = cursor.getString(4);
        Integer estado = cursor.getInt(5);

        ProductEntity productoEntity = new ProductEntity( nid, brand, name, cantidad,total);

        db.close();
        return productoEntity;
    }

    public List<ProductEntity> getAllProducts()
    {
        List<ProductEntity> lst =new ArrayList<ProductEntity>();
        String sql= "SELECT  * FROM " + MyDatabase.TABLE_CARRO;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                ProductEntity contact =new ProductEntity();
                contact.setId(cursor.getInt(0));
                contact.setBrand(cursor.getString(1));
                contact.setNombre(cursor.getString(2));
                contact.setCantidad(cursor.getString(3));
                contact.setTotal(cursor.getString(4));
                contact.setEstado(cursor.getInt(5));


                lst.add(contact);
            }while(cursor.moveToNext());
        }
        db.close();
        return lst;
    }

    public List<ProductEntity> getAllProductsPendientes()
    {
        List<ProductEntity> lst =new ArrayList<ProductEntity>();
        String sql= "SELECT  * FROM " + MyDatabase.TABLE_CARRO + " WHERE " + MyDatabase.KEY_ESTADO + " = 0";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                ProductEntity contact =new ProductEntity();
                contact.setId(cursor.getInt(0));
                contact.setBrand(cursor.getString(1));
                contact.setNombre(cursor.getString(2));
                contact.setCantidad(cursor.getString(3));
                contact.setTotal(cursor.getString(4));
                contact.setEstado(cursor.getInt(5));


                lst.add(contact);
            }while(cursor.moveToNext());
        }
        db.close();
        return lst;
    }

    public int getNoteCount()
    {
        String sql= "SELECT * FROM "+MyDatabase.TABLE_CARRO;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count=cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    //--------------------------------------------
    public int updateNote(ProductEntity productoEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.KEY_BRAND, productoEntity.getBrand());
        values.put(MyDatabase.KEY_NAME, productoEntity.getNombre());
        values.put(MyDatabase.KEY_CANT, productoEntity.getCantidad());
        values.put(MyDatabase.KEY_TOTAL, productoEntity.getTotal());
        values.put(MyDatabase.KEY_ESTADO, productoEntity.getEstado());

        int row =db.update(MyDatabase.TABLE_CARRO,
                values,
                MyDatabase.KEY_ID+"=?",
                new String[]{String.valueOf(productoEntity.getId())});
        db.close();

        return row;
    }

    //--------------------------------------------
    public int deleteNote(ProductEntity productoEntity)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row= db.delete(MyDatabase.TABLE_CARRO,
                MyDatabase.KEY_ID+"=?",
                new String[]{String.valueOf(productoEntity.getId())});
        db.close();
        return row;
    }

    public long getNoteCountWithStatement(){
        String sql= "select count(*) from "+MyDatabase.TABLE_CARRO;
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteStatement s = db.compileStatement(sql);
        long count = s.simpleQueryForLong();
        db.close();

        return count;
    }


    private void clearTable(String table){
        String clearDBQuery = "DELETE FROM "+table;
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        db.execSQL(clearDBQuery);
        db.close();
    }

    public void clearDb(){
        clearTable(MyDatabase.TABLE_CARRO);
        //clearTable(MyDatabase.TABLE_USER);
    }
}
