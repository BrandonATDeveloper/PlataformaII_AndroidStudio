package com.example.brand.persistencia2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Brand on 23/10/2017.
 */

public class SQLHelper extends SQLiteOpenHelper {

    final static String baseDatos = "BaseDeDatos.db";
    String tablaUno = "Contactos";
    String columnaNombre = "Nombre";
    String columnaApellido = "Apellido";
    String columnaId = "id";

    SQLHelper(Context c, SQLiteDatabase.CursorFactory factory){
        super(c, baseDatos, factory, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String codeSQL = "CREATE TABLE "+tablaUno+" ("+columnaId+" INTEGER PRIMARY KEY, "+columnaNombre+" TEXT, "+columnaApellido+" TEXT)";
        System.out.println(codeSQL);
        sqLiteDatabase.execSQL(codeSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablaUno);
        this.onCreate(sqLiteDatabase);
    }

//    public void insertContact(contacto contact){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues valores;
//        valores = new ContentValues();
//        valores.put(columnaNombre,contact.nombre);
//        valores.put(columnaApellido,contact.apellido);
//        db.insert(tablaUno, null, valores);
//        db.close();
//    }

    public int insertContact(contacto contact){
        int idUsuario = 0;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            ContentValues valores;
            valores = new ContentValues();
            valores.put(columnaNombre,contact.nombre);
            valores.put(columnaApellido,contact.apellido);
            idUsuario = (int) db.insert(tablaUno, null, valores);
        }
        db.close();
        return idUsuario;
    }

    public ArrayList<contacto> getContact() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tablaUno, new String[]{columnaId, columnaNombre, columnaApellido}, null, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            ArrayList<contacto> lista = new ArrayList<>();
            while (!cursor.isAfterLast()){
                contacto contact = new contacto();
                contact.nombre = cursor.getString(1);
                contact.apellido = cursor.getString(2);
                contact.id = cursor.getInt(0);
                lista.add(contact);
                cursor.moveToNext();
            }
            db.close();
            return lista;
        }else{
            db.close();
            return null;
        }
    }

    public void updateContact(contacto contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(columnaNombre, contact.nombre);
        valores.put(columnaApellido, contact.apellido);
        db.update(tablaUno, valores, "id = " + contact.id, null);
        //db.update(tablaUno, valores, columnaId + " = ? ", new String[]{contact.id + ""});
        db.close();
    }

    public void eliminarContacto(contacto contact){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues valores;
        valores = new ContentValues();
        valores.put(columnaNombre,contact.nombre);
        valores.put(columnaApellido,contact.apellido);
        sqLiteDatabase.delete(tablaUno,"id = " + contact.id, null);
        sqLiteDatabase.close();
    }

}
