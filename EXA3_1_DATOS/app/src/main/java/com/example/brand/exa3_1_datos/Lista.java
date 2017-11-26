package com.example.brand.exa3_1_datos;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Lista extends AppCompatActivity implements ListView.OnItemClickListener {


    ListView list;
    Button calcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        list = (ListView) findViewById(R.id.list);
        calcular = (Button) findViewById(R.id.button4);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Almacenador.elementos.size() < 5){
                    Toast.makeText(Lista.this, "Asegurese de tener 5 datos para realizar el calculo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Lista.this, ""+calcularDato(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        list.setOnItemClickListener(this);
        actualizarDatos();
        leerLista();
    }

    public void actualizarDatos(){
        ArrayList<String> datos =new ArrayList<>();
        for (Dato d: Almacenador.elementos){
            datos.add("Dato Uno: " + d.d1 + " Dato Dos: " + d.d2);
        }
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        list.setAdapter(adapter);
    }

    public void leerLista(){
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"file.txt");
            if (existeMemoriaExterna() && file.exists()){
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                Almacenador.elementos = (ArrayList<Dato>)stream.readObject();
                stream.close();
            }
        }catch (Exception e){

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

    @Override
    protected void onResume() {
        super.onResume();
        actualizarDatos();
        leerLista();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Dato dato;
        dato = Almacenador.elementos.get(i);
        double dato1 = dato.d1;
        double dato2 = dato.d2;
        Intent intent = new Intent(Lista.this, Modificar.class);
        intent.putExtra("dato1", dato1);
        intent.putExtra("dato2", dato2);
        intent.putExtra("index", i);
        startActivity(intent);
        finish();
    }

    public double calcularDato(){
        Dato d1,d2,d3;
        d1 = Almacenador.elementos.get(Almacenador.elementos.size()-1);
        d2 = Almacenador.elementos.get(Almacenador.elementos.size()-3);
        d3 = Almacenador.elementos.get(Almacenador.elementos.size()-5);
        double calculo = (d1.d1 * d2.d2) / (d3.d1 + d3.d2 - d1.d2) * d2.d1;
        return calculo;
    }

}
