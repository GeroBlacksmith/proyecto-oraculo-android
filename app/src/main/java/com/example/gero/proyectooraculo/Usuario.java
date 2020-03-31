package com.example.gero.proyectooraculo;

/**
 * Created by Gero on 28/11/2016.
 */

public class Usuario {
    int _idUsuario;
    int _idRol;
    String _nombre;
    String _cuenta;
    String _correo;
    String _clave;

    public Usuario(){

    }
    public Usuario(int _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public Usuario(String cuenta, String clave) {
        this._cuenta = cuenta;
        this._clave = clave;
    }

    public Usuario(int _idUsuario, int idRol, String nombre, String cuenta, String correo, String clave) {
        this._idUsuario = _idUsuario;
        this._idRol = idRol;
        this._nombre = nombre;
        this._cuenta = cuenta;
        this._correo = correo;
        this._clave = clave;
    }

    public int get_idUsuario() {
        return _idUsuario;
    }

    public void set_idUsuario(int _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public int get_idRol() {
        return _idRol;
    }

    public void set_idRol(int _idRol) {
        this._idRol = _idRol;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_cuenta() {
        return _cuenta;
    }

    public void set_cuenta(String _cuenta) {
        this._cuenta = _cuenta;
    }

    public String get_correo() {
        return _correo;
    }

    public void set_correo(String _correo) {
        this._correo = _correo;
    }

    public String get_clave() {
        return _clave;
    }

    public void set_clave(String _clave) {
        this._clave = _clave;
    }
}
