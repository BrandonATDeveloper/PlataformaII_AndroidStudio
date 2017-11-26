package com.example.brand.app_4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Secundaria extends AppCompatActivity {


    int numTelefonico;
    String numero;
    TextView phoneCall;
    Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        phoneCall = (TextView)findViewById(R.id.textView);
        call = (Button)findViewById(R.id.button2);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNumber();
            }
        });

        numero = getIntent().getExtras().getString("numero_telefonico");
        numTelefonico = getIntent().getExtras().getInt("numero_telefonico_int");
        phoneCall.setText(numero);

    }

    public void callNumber (){
        String numFormat = "tel:"+ numero;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(numFormat));
        startActivity(intent);
    }

}
