package com.example.brand.aplicacion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button myButton, Act;
    TextView myTextView;
    EditText boxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Act = (Button)findViewById(R.id.button);
        myButton = (Button)findViewById(R.id.myB);
        myTextView = (TextView)findViewById(R.id.myText);
        boxText = (EditText)findViewById(R.id.box);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = boxText.getText().toString();
                myTextView.setText(Text);
            }
        });

        Act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(actividad);
                finish();
            }
        });
    }
}
