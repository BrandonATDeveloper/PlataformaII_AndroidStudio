package com.example.brand.persistencia2;

import java.io.Serializable;

/**
 * Created by Brand on 16/10/2017.
 */

public class contacto implements Serializable {
    String nombre, apellido;
    int id;

    contacto(){

    }

    public contacto(int ID, String nom, String ape){
        this.id = ID;
        this.nombre = nom;
        this.apellido = ape;
    }
}
