package com.example.brand.ejercicio2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox red, green, blue;
    SeekBar redB, greenB, blueB;
    ConstraintLayout fondo;
    Button abrirPag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo = (ConstraintLayout)findViewById(R.id.fondo);

        red = (CheckBox)findViewById(R.id.rojo);
        green = (CheckBox)findViewById(R.id.verde);
        blue = (CheckBox)findViewById(R.id.azul);

        redB = (SeekBar)findViewById(R.id.seek1);
        greenB = (SeekBar)findViewById(R.id.seek2);
        blueB = (SeekBar)findViewById(R.id.seek3);
        abrirPag = (Button)findViewById(R.id.button);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cambiarColor(seekBar);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        redB.setOnSeekBarChangeListener(listener);
        greenB.setOnSeekBarChangeListener(listener);
        blueB.setOnSeekBarChangeListener(listener);
    }

    public void cambiarColor(View c){
        int rojo = red.isChecked() ? redB.getProgress() : 255;
        int verde = green.isChecked() ? greenB.getProgress() : 255;
        int azul = blue.isChecked() ? blueB.getProgress() : 255;
        fondo.setBackgroundColor(Color.argb(255, rojo, verde, azul));
    }

    public void abrirPagWeb(View v){
        /*//este es el codigo para cuando se requiere la pagina web
        String uriURL = getResources().getString(R.string.paginaWeb);
        Intent desplegarPag = new Intent(Intent.ACTION_VIEW, Uri.parse(uriURL));
        startActivity(desplegarPag);*/

        //codigo para la lista de strings
        String[] lista = getResources().getStringArray(R.array.lista);
        for(String e : lista){//recorre la lista uno por un, de manera temporal
            Toast.makeText(getApplicationContext(),e,Toast.LENGTH_SHORT).show();

        }
    }
}
