package com.example.brand.persistencia2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Secundario extends AppCompatActivity {

    EditText nombre, apellido;
    Button butn, borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundario);
        nombre = (EditText) findViewById(R.id.editText);
        apellido = (EditText) findViewById(R.id.editText2);
        butn = (Button) findViewById(R.id.button);
        borrar = (Button) findViewById(R.id.borrar);
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacto contact = new contacto();
                contact.nombre = nombre.getText().toString();
                contact.apellido = apellido.getText().toString();
                MainActivity.sql.insertContact(contact);
            }
        });
    }


}
