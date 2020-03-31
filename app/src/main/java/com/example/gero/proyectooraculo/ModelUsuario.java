package com.example.gero.proyectooraculo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gero on 28/11/2016.
 */

public class ModelUsuario extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "oraculo";

    // Usuarios table name
    private static final String TABLE_USUARIOS = "usuarios";

    // Usuarios Table Columns names
    private static final String KEY_ID_USUARIOS = "idUsuarios";
    private static final String KEY_ID_ROL = "idRol";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_CUENTA = "cuenta";
    private static final String KEY_CORREO = "correo";
    private static final String KEY_CLAVE = "clave";


    public ModelUsuario(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIO =
                "CREATE TABLE " + TABLE_USUARIOS +" ( "
                + KEY_ID_USUARIOS + ", INTEGER, "
                + KEY_ID_ROL + " INTEGER, "
                + KEY_NOMBRE + " TEXT, "
                + KEY_CUENTA + " TEXT, "
                + KEY_CORREO + " TEXT, "
                + KEY_CLAVE + " TEXT"
                +")";
        db.execSQL(CREATE_USUARIO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }
    // Adding new usuario
    public void addUsuario(Usuario usuario) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_USUARIOS ,usuario.get_idUsuario());
        values.put(KEY_ID_ROL ,usuario.get_idRol());
        values.put(KEY_NOMBRE ,usuario.get_nombre());
        values.put(KEY_CUENTA ,usuario.get_cuenta());
        values.put(KEY_CORREO ,usuario.get_correo());
        values.put(KEY_CLAVE ,usuario.get_clave());
        db.insert(TABLE_USUARIOS, null, values);
        db.close();
    }

    // Getting single usuario
    public Usuario getUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_USUARIOS,
                new String[]
                        {
                                KEY_ID_USUARIOS,
                                KEY_ID_ROL,
                                KEY_NOMBRE,
                                KEY_CUENTA,
                                KEY_CORREO,
                                KEY_CLAVE
                        },
                KEY_ID_USUARIOS+"=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Usuario usuario = new Usuario(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        return usuario;
    }

    // Getting All Usuarios
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarioList = new ArrayList<Usuario>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USUARIOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.set_idUsuario(Integer.parseInt(cursor.getString(0)));
                usuario.set_idRol(Integer.parseInt(cursor.getString(1)));
                usuario.set_nombre(cursor.getString(2));
                usuario.set_cuenta(cursor.getString(3));
                usuario.set_correo(cursor.getString(4));
                usuario.set_clave(cursor.getString(5));
                // Adding usuario to list
                usuarioList.add(usuario);
            } while (cursor.moveToNext());
        }

        // return usuario list
        return usuarioList;
    }


    public int getUsuariosCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USUARIOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);


        // return count
        return cursor.getCount();
    }

    public int updateUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, usuario.get_nombre());
        values.put(KEY_CUENTA, usuario.get_cuenta());
        values.put(KEY_CORREO, usuario.get_correo());
        values.put(KEY_CLAVE, usuario.get_clave());


        // updating row
        return db.update(TABLE_USUARIOS, values, KEY_ID_USUARIOS + " = ?",
                new String[] { String.valueOf(usuario.get_idUsuario()) });
    }


    public void deleteUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USUARIOS, KEY_ID_USUARIOS + " = ?",
                new String[] { String.valueOf(usuario.get_idUsuario()) });
        db.close();
    }
    public boolean existe(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE nombre=\"" + nombre + "\"", null);

        return cursor.getCount()!=0;
    }
    public Usuario getUsuario(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_USUARIOS,
                new String[]
                        {
                                KEY_ID_USUARIOS,
                                KEY_ID_ROL,
                                KEY_NOMBRE,
                                KEY_CUENTA,
                                KEY_CORREO,
                                KEY_CLAVE
                        },
                KEY_NOMBRE+"=?",
                new String[]{nombre},
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Usuario usuario = new Usuario(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        return usuario;
    }
}
