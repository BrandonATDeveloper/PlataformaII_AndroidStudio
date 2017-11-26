package com.example.brand.exa3_1_datos;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Almacenador extends AppCompatActivity implements Serializable {

    static ArrayList<Dato> elementos = new ArrayList<>();
    EditText dato1, dato2;
    Button guardar, activity2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dato1 = (EditText)findViewById(R.id.editText2);
        dato2 = (EditText)findViewById(R.id.editText);
        guardar = (Button)findViewById(R.id.button);
        activity2 = (Button)findViewById(R.id.button2);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });
        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (elementos.size() <5){
                        Toast.makeText(Almacenador.this, "Asegurese de añadir 5 datos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Almacenador.this, Lista.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void guardarDatos() {
        try{
            double d1 = Double.parseDouble(dato1.getText().toString());
            double d2 = Double.parseDouble(dato2.getText().toString());
            Dato dato = new Dato(d1, d2);
            if (datoRepetido(dato)){
                Toast.makeText(this, "Dato repetido, Ingrese datos distintos", Toast.LENGTH_SHORT).show();
            }else{
                elementos.add(dato);
                guardarArchivo();
            }
        }catch (Exception e){
            Toast.makeText(this, "Porfavor ingresar datos numericos", Toast.LENGTH_SHORT).show();
        }
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
                stream.writeObject(elementos);
                stream.close();
            }
        }catch (Exception e){

        }
    }

    public boolean datoRepetido(Dato dato){
        if (elementos.size() == 0){
            return false;
        }
        Dato data;
        data = elementos.get(elementos.size()-1);
        boolean comparar = (dato.d1 == data.d1 && dato.d2 == data.d2);
        return comparar;
    }

}
