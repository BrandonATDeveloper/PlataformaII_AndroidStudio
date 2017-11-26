package com.example.brand.persistencia2;

import android.content.Intent;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView list;
    Button btn;

    static ArrayList<contacto> listaContactos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list);
        btn = (Button)findViewById(R.id.button2);
        list.setOnItemClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Secundario.class);
                startActivity(intent);
            }
        });
        leerLista();
        actualizarContactos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        leerLista();
        actualizarContactos();
    }

    public void actualizarContactos(){
        ArrayList<String> aux = new ArrayList<>();
        for (contacto c : listaContactos){
            aux.add(c.nombre);
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, aux);
        list.setAdapter(adapter);
    }

    public void leerLista(){
        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"file.txt");
            if (existeMemoriaExterna() && file.exists()){
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                listaContactos = (ArrayList<contacto>)stream.readObject();
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        contacto cnt;
        cnt = listaContactos.get(i);
        String nombre = cnt.nombre;
        String apellido = cnt.apellido;
        String telefono = cnt.telefono;
        String correo = cnt.correo;
        Intent intent = new Intent(MainActivity.this, Secundario.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("tel", telefono);
        intent.putExtra("correo", correo);
        intent.putExtra("index", i);
        startActivity(intent);
    }
}
