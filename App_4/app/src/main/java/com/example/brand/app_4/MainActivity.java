package com.example.brand.app_4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText numPhone;
    Button numCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numPhone = (EditText)findViewById(R.id.editText);
        numCall = (Button)findViewById(R.id.button);
    }

    public void changeActivity (View v){
        Intent intent = new Intent(MainActivity.this, Secundaria.class);
        String numero = numPhone.getText().toString();
        intent.putExtra("numero_telefonico",numero);
        //int numeroInt = Integer.parseInt(numero);
        //intent.putExtra("numero_telefonico_int",numeroInt);
        startActivity(intent);
    }
}
