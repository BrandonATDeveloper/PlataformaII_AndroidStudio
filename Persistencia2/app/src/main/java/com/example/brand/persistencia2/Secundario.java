package com.example.brand.persistencia2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Secundario extends AppCompatActivity {

    EditText nombre, apellido, telefono, correo;
    int index = -1;
    Button butn, borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundario);
        nombre = (EditText) findViewById(R.id.editText);
        apellido = (EditText) findViewById(R.id.editText2);
        telefono = (EditText) findViewById(R.id.telefono);
        correo = (EditText) findViewById(R.id.correo);
        butn = (Button) findViewById(R.id.button);
        borrar = (Button) findViewById(R.id.borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarContacto();
            }
        });
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == -1){
                    guardarContacto();
                    Toast.makeText(Secundario.this, "Contacto Agregado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Secundario.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    contacto contacto = new contacto(nombre.getText().toString(), apellido.getText().toString(),telefono.getText().toString(), correo.getText().toString());
                    MainActivity.listaContactos.set(index, contacto);
                    guardarArchivo();
                    Toast.makeText(Secundario.this, "Contacto Modificado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Secundario.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        try {
            nombre.setText(getIntent().getExtras().getString("nombre"));
            apellido.setText(getIntent().getExtras().getString("apellido"));
            telefono.setText(getIntent().getExtras().getString("tel"));
            correo.setText(getIntent().getExtras().getString("correo"));
            index = getIntent().getExtras().getInt("index");
        }catch (Exception e){
        }
    }



    public void guardarContacto(){
        contacto contacto = new contacto(nombre.getText().toString(), apellido.getText().toString(),telefono.getText().toString(), correo.getText().toString());
        MainActivity.listaContactos.add(contacto);
        guardarArchivo();
    }

    public Boolean existeMemoriaExterna(){
        String estado = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(estado)){
            return true;
        }else{
            return false;
        }
    }

    public void guardarArchivo(){
        try{
            if (existeMemoriaExterna()){
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"file.txt");
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
                stream.writeObject(MainActivity.listaContactos);
                stream.close();
            }
        }catch (Exception e){

        }
    }

    public void borrarContacto(){
        MainActivity.listaContactos.remove(index);
        guardarArchivo();
        Toast.makeText(Secundario.this, "Contacto Eliminado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Secundario.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
