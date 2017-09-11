package com.example.brand.practica2_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sigUno, serieF;
    EditText valorF;
    TextView resF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sigUno = (Button)findViewById(R.id.sigUno);
        serieF = (Button)findViewById(R.id.serieF);
        valorF = (EditText)findViewById(R.id.valor);
        resF = (TextView)findViewById(R.id.resF);
        sigUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Metodo boton siguiente
                Intent actividad = new Intent(MainActivity.this, Ordenar.class);
                startActivity(actividad);
                finish();
            }
        });
        serieF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{//prevenir que el usuario intriduzca letras u otros caracteres
                    int aux = Integer.parseInt(valorF.getText().toString());//capturar texto y convertirlo a int
                    int sef1 = 0;
                    int sef2 = 1;
                    for(int i = 1; i <= aux; i++){//serie de febunacci
                        sef2 = sef1 + sef2;
                        sef1 = sef2 - sef1;
                    }
                    resF.setText(""+sef1);//Muestra el resultado
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Favor de intruducir un numero valido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
