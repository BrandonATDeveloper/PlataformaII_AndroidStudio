package com.example.brand.exa_2_unidadii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Slider extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    String datoButton = "", datoRadio = "", datoSeek = "";
    SeekBar opciones;
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        opciones = (SeekBar)findViewById(R.id.seekBar);
        volver = (Button)findViewById(R.id.volverSeek);
        opciones.setOnSeekBarChangeListener(this);
        try{
            datoButton = getIntent().getExtras().getString("datoButton");
            datoRadio = getIntent().getExtras().getString("datoRadio");
            datoSeek = getIntent().getExtras().getString("datoSeek");
        }catch (Exception e){}
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        capturaSeek();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void capturaSeek(){
        datoSeek = String.valueOf(opciones.getProgress());

    }

    public void volverSeek(View v){
        Intent intent = new Intent(Slider.this, MainActivity.class);
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
