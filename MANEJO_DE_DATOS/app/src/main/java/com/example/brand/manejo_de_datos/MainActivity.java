package com.example.brand.manejo_de_datos;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    EditText datos;
    CheckBox sd;
    Button guardar, cargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos = (EditText)findViewById(R.id.editText);
        sd = (CheckBox)findViewById(R.id.sd);
        guardar = (Button)findViewById(R.id.guardar);
        cargar = (Button)findViewById(R.id.cargar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sd.isChecked()){
                    guardarEnMemoriaExterna();
                }else{
                    guardarEnMemoriaInterna();
                }
            }
        });
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sd.isChecked()){
                    cargarEnMemoriaExterna();
                }else{
                    cargarEnMemoriaInterna();
                }
            }
        });
    }

    public boolean existeMemoria(){
        String estado = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(estado)){
            return true;
        }
        return false;
    }

    public void cargarEnMemoriaExterna(){
        try{
            if (existeMemoria()){
                File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"archivo.txt");
                FileInputStream stream = new FileInputStream(archivo);
                InputStreamReader lector = new InputStreamReader(stream);
                BufferedReader contenido = new BufferedReader(lector);
                String linea = "", mensaje = "";
                while ((linea = contenido.readLine()) != null){
                    mensaje += linea;
                }
                lector.close();
                datos.setText(mensaje);
            }
        }catch (Exception e){

        }
    }

    public void guardarEnMemoriaExterna(){
        try{
            if (existeMemoria()){
                File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"archivo.txt");
                FileOutputStream stream = new FileOutputStream(archivo, false);
                OutputStreamWriter escritor = new OutputStreamWriter(stream);
                escritor.write(datos.getText().toString());
                escritor.close();
            }
        }catch (Exception e){

        }
    }

    public void cargarEnMemoriaInterna(){
        try{
            File archivo = new File(getApplicationContext().getFilesDir(),"archivo.txt");
            FileReader lector = new FileReader(archivo);
            String linea = "", mensaje = "";
            BufferedReader contenido = new BufferedReader(lector);
            while ((linea = contenido.readLine()) != null){
                mensaje += linea;
            }
            lector.close();
            datos.setText(mensaje);
        }catch (Exception e){

        }
    }

    public void guardarEnMemoriaInterna(){
                                //En donde estara el archivo ,        nombre del archivo
        try{
            File archivo = new File(getApplicationContext().getFilesDir(),"archivo.txt");
            FileWriter escritor = new FileWriter(archivo, false);
            escritor.write(datos.getText().toString());
            escritor.close();
        }catch (Exception e){

        }


    }

}
