package com.example.brand.aplicacion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText Celsius, Farenheit;
    Button btnC, btnF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Celsius = (EditText) findViewById(R.id.Celsius);
        Farenheit = (EditText) findViewById(R.id.Farenheit);
        btnC = (Button) findViewById(R.id.buttonC);
        btnF = (Button) findViewById(R.id.buttonF);
    }

    public void conversion(View v) {
        try {
            if (v.getId() == btnC.getId()) {
                double valor = Double.parseDouble(Celsius.getText().toString());
                double res = valor * 1.8 + 32;
                Farenheit.setText("" + res);
            }
            if (v.getId() == btnF.getId()) {
                double valor = Double.parseDouble(Farenheit.getText().toString());
                double res = valor / 1.8 - 32;
                Celsius.setText("" + res);
            }
        }
        catch (Exception e) {
            Toast mensaje = Toast.makeText(getApplicationContext(), "El valor en la caja de texto es incorrecto", Toast.LENGTH_SHORT);
            mensaje.show();
        }
    }
}
