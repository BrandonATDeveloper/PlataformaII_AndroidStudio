package com.example.brand.practica2_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;

public class Ordenar extends AppCompatActivity {

    Button order, antUno, sigDos;
    EditText valorSe;
    TextView resSe;
    int i,j,temp,num[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenar);
        order = (Button)findViewById(R.id.ordenar);
        antUno = (Button)findViewById(R.id.anteUno);
        sigDos = (Button)findViewById(R.id.sigDos);
        valorSe = (EditText)findViewById(R.id.valorSe);
        resSe = (TextView)findViewById(R.id.resSe);
        antUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(Ordenar.this, MainActivity.class);
                startActivity(actividad);
                finish();
            }
        });
        sigDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(Ordenar.this, SerieT.class);
                startActivity(actividad);
                finish();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BubbleSort();
            }
        });
    }
    public void BubbleSort() {
        try{//prevenir que el usuario intriduzca letras u otros caracteres
            String dato = valorSe.getText().toString(); //capturar los datos

            String[] num = dato.split(" ");//Introduzir los datos a un arreglo de strings
            int[] num2 = new int[num.length];//arreglo auxiliar

            for(int i = 0; i < num.length; i++){//Convertir los datos strings a int del arreglo del arreglo original al auxiliar
                num2[i] = Integer.parseInt(num[i]);
            }
            int temp = 0;
            for (i = 0; i < num2.length-1; i++) { // metodo Burbuja
                for (j = 0; j < num2.length-1; j++) {
                    if (num2[j] > num2[j+1]) {
                        temp = num2[j];
                        num2[j] = num2[j+1];
                        num2[j+1] = temp;
                    }
                }
            }

            String result = "";
            for (int i = 0; i < num2.length; i++){//Mostrar los datos
                result += num2[i] + " ";
            }
            resSe.setText(result);//Muestra el resultado
        }catch (Exception e){
            Toast.makeText(Ordenar.this, "Favor de introducir una serie de numeros valida", Toast.LENGTH_SHORT).show();
        }
    }
}
