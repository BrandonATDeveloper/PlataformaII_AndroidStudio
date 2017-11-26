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

public class Modificar extends AppCompatActivity {

    EditText dato1, dato2;
    Button guardar, borrar;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        dato1 = (EditText)findViewById(R.id.editText3);
        dato2 = (EditText)findViewById(R.id.editText4);
        guardar = (Button)findViewById(R.id.button3);
        borrar = (Button)findViewById(R.id.button5);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarDato();
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarDato();
                Intent intent = new Intent(Modificar.this, Lista.class);
                startActivity(intent);
                finish();
            }
        });
        try{
            double d1 = getIntent().getExtras().getDouble("dato1");
            double d2 = getIntent().getExtras().getDouble("dato2");
            index = getIntent().getExtras().getInt("index");
            dato1.setText(""+d1);
            dato2.setText(""+d2);
        }catch (Exception e){
        }
    }

    public void modificarDato(){
        try{
            double d1 = Double.parseDouble(dato1.getText().toString());
            double d2 = Double.parseDouble(dato2.getText().toString());
            Dato dato = new Dato(d1, d2);
            if (datoRepetidoAbajo(dato)){
                Toast.makeText(this, "Dato repetido, Ingrese datos distintos", Toast.LENGTH_SHORT).show();
            }else if(datoRepetidoArriba(dato)){
                Toast.makeText(this, "Dato repetido, Ingrese datos distintos", Toast.LENGTH_SHORT).show();
            }else{
                Almacenador.elementos.set(index, dato);
                guardarArchivo();
                Toast.makeText(this, "Dato Modificado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Modificar.this, Lista.class);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){
            Toast.makeText(this, "Porfavor ingresar datos numericos", Toast.LENGTH_SHORT).show();
        }
    }

    public void borrarDato(){
        Almacenador.elementos.remove(index);
        guardarArchivo();
        Toast.makeText(Modificar.this, "Dato Eliminado", Toast.LENGTH_SHORT).show();
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
                stream.writeObject(Almacenador.elementos);
                stream.close();
            }
        }catch (Exception e){

        }
    }

    public boolean datoRepetidoAbajo(Dato dato){
        if (Almacenador.elementos.size() == 0){
            return false;
        }
        Dato data;
        data = Almacenador.elementos.get(index - 1);
        boolean comparar = (dato.d1 == data.d1 && dato.d2 == data.d2);
        return comparar;
    }

    public boolean datoRepetidoArriba(Dato dato){
        if (Almacenador.elementos.size() == 0){
            return false;
        }
        Dato data;
        data = Almacenador.elementos.get(index + 1);
        boolean comparar = (dato.d1 == data.d1 && dato.d2 == data.d2);
        return comparar;
    }

}
