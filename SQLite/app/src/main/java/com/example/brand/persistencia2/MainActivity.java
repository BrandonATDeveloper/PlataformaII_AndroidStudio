package com.example.brand.persistencia2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
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

public class MainActivity extends AppCompatActivity  {

    ListView list;
    Button btn;
    static SQLHelper sql;
    static ArrayList<contacto> listaContactos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list);
        btn = (Button)findViewById(R.id.button2);
        sql = new SQLHelper(getApplicationContext(), null);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Secundario.class);
                startActivity(intent);
            }
        });
    }

    public void readList(){
        listaContactos = sql.getContact();
    }

    public void updateListView(){
        ArrayList<String> aux = new ArrayList<>();
        for (contacto c : listaContactos){
            aux.add(c.nombre);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, aux);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            readList();
            updateListView();
        }catch (Exception e){

        }
    }

}
