package com.example.brand.practica2_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SerieT extends AppCompatActivity {

    Button seDos, antDos;
    EditText valorT;
    TextView resT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_t);
        seDos = (Button)findViewById(R.id.serieT);
        antDos = (Button)findViewById(R.id.antDos);
        valorT = (EditText)findViewById(R.id.valorT);
        resT = (TextView)findViewById(R.id.num);
        antDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(SerieT.this, Ordenar.class);
                startActivity(actividad);
                finish();
            }
        });
        seDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{//prevenir que el usuario intriduzca letras u otros caracteres
                    int valor = Integer.parseInt(valorT.getText().toString());//capturar datos
                    int var = 1;
                    for(int i = 1; i <= valor; i++){//ciclo para realizar a serie
                        if(i == 1){
                            //PA que jale el ejemplo del profe aunque la 8  la tienen mal
                        }
                        else{
                            var += (i%2 == 0)? 1 : 2;//si es impar suma 2 si es par suma 1
                        }
                    }
                    resT.setText(""+var);//Muestra el resultado
                }catch (Exception e){
                    Toast.makeText(SerieT.this, "Favor de intruducir un numero valido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
