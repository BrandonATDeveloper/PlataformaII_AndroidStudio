package com.example.brand.exa_2_unidadii;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioButtons extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    String datoButton = "", datoRadio = "", datoSeek = "";
    RadioGroup opcion;
    RadioButton unoR, dosR, tresR;
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttons);
        opcion = (RadioGroup)findViewById(R.id.opcion);
        unoR = (RadioButton)findViewById(R.id.unoR);
        dosR = (RadioButton)findViewById(R.id.dosR);
        tresR = (RadioButton)findViewById(R.id.tresR);
        volver = (Button)findViewById(R.id.volverRadio);
        opcion.setOnCheckedChangeListener(this);
        try{
            datoButton = getIntent().getExtras().getString("datoButton");
            datoRadio = getIntent().getExtras().getString("datoRadio");
            datoSeek = getIntent().getExtras().getString("datoSeek");
        }catch (Exception e){}
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        if (i == R.id.unoR){
            datoRadio = "1";
        }else if (i == R.id.dosR){
            datoRadio = "2";
        }else if (i == R.id.tresR){
            datoRadio = "3";
        }
    }

    public void volverRadio(View v){
        Intent intent = new Intent(RadioButtons.this, MainActivity.class);
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

}
