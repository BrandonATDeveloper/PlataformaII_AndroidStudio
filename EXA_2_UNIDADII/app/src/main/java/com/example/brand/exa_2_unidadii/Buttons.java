package com.example.brand.exa_2_unidadii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Buttons extends AppCompatActivity {

    String datoButton = "", datoRadio = "", datoSeek = "";
    Button uno, dos, tres, volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        uno = (Button)findViewById(R.id.uno);
        dos = (Button)findViewById(R.id.dos);
        tres = (Button)findViewById(R.id.tres);
        volver = (Button)findViewById(R.id.volver);
        try{
            datoButton = getIntent().getExtras().getString("datoButton");
            datoRadio = getIntent().getExtras().getString("datoRadio");
            datoSeek = getIntent().getExtras().getString("datoSeek");
        }catch (Exception e){}
    }

    public void volver(View v){
        capturaDato(volver);
        Intent intent = new Intent(Buttons.this, MainActivity.class);
        if (datoButton != ""){
            intent.putExtra("datoButton",datoButton);
        }
        if (datoRadio != ""){
            intent.putExtra("datoRadio",datoRadio);
        }
        if (datoSeek != ""){
            intent.putExtra("datoSeek",datoSeek);
        }
        startActivity(intent);
        finish();
    }

    public void capturaDato(View v){
        if(v.getId() == uno.getId()){
            datoButton = "1";
            Toast.makeText(this, "Seleccionaste: "+datoButton, Toast.LENGTH_SHORT).show();
        }else if(v.getId() == dos.getId()){
            datoButton = "2";
            Toast.makeText(this, "Seleccionaste: "+datoButton, Toast.LENGTH_SHORT).show();
        }else if(v.getId() == tres.getId()){
            datoButton = "3";
            Toast.makeText(this, "Seleccionaste: "+datoButton, Toast.LENGTH_SHORT).show();
        }
    }

}
