package com.example.brand.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    RadioButton r1, r2, r3;
    CheckBox c1, c2;
    SeekBar sk;
    Button guardar, cargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        c1 = (CheckBox)findViewById(R.id.checkBox);
        c2 = (CheckBox)findViewById(R.id.checkBox2);
        sk = (SeekBar)findViewById(R.id.seekBar);

        guardar = (Button)findViewById(R.id.button);
        cargar = (Button)findViewById(R.id.button2);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargar();
            }
        });
    }

    public void guardar(){
        SharedPreferences sp = getSharedPreferences("archivo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("ck1",c1.isChecked());
        editor.putBoolean("ck2",c2.isChecked());
        editor.putBoolean("rd1",r1.isChecked());
        editor.putBoolean("rd2",r2.isChecked());
        editor.putBoolean("rd3",r3.isChecked());
        editor.putInt("sb",sk.getProgress());
        //apply genera un segundo hilo para guardar los datos (Recomendar su uso)
        editor.apply();
        //Es mejor commit virtualmente habando por que forza el guardado (Puede generar errores al archivo)
        //editor.commit();
    }

    public void cargar(){
        SharedPreferences sp = getSharedPreferences("archivo",MODE_PRIVATE);
        c1.setChecked(sp.getBoolean("ck1",false));
        c2.setChecked(sp.getBoolean("ck2",false));
        r1.setChecked(sp.getBoolean("rd1",false));
        r2.setChecked(sp.getBoolean("rd2",false));
        r3.setChecked(sp.getBoolean("rd3",false));
        sk.setProgress(sp.getInt("sb",0));
    }

    @Override
    protected void onStop() {
        super.onStop();
        guardar();
    }

}
