package com.example.brand.persistencia2;

/**
 * Created by Brand on 16/10/2017.
 */

public class contacto {
    String nombre, apellido, telefono, correo;

    public contacto(String nombre, String ... Data){
        this.nombre = nombre;
        apellido = Data[0];
        telefono = Data[1];
        correo = Data[2];
    }
}
