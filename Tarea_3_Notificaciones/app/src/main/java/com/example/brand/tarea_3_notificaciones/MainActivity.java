package com.example.brand.tarea_3_notificaciones;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    NotificationManager manager;
    NotificationCompat.Builder notificacion;//generador de notificaciones
    int time, idNotification = 0;
    RadioGroup timerOption;
    RadioButton cinco, quince , treinta, sesenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//proveedor de servicios de notififcaciones
        timerOption = (RadioGroup)findViewById(R.id.timerOption);
        cinco = (RadioButton)findViewById(R.id.cinco);
        quince = (RadioButton)findViewById(R.id.quince);
        treinta = (RadioButton)findViewById(R.id.treinta);
        sesenta = (RadioButton)findViewById(R.id.sesenta);
        timerOption.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        if(i == R.id.cinco){
            time = 5000;
        }else if(i == R.id.quince){
            time = 15000;
        }else if(i == R.id.treinta){
            time = 30000;
        }else if(i == R.id.sesenta){
            time = 60000;
        }
    }


    public void exeNotification(View v){
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                //Construir la notificacion
                notificacion = new NotificationCompat.Builder(getApplicationContext());
                notificacion.setContentTitle("Notificacion");
                notificacion.setContentText("Tiempo esperado: " + time/1000 + " segundos");
                notificacion.setSmallIcon(R.mipmap.ic_launcher);
                notificacion.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                notificacion.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                //Correr notificacion
                Intent mismaActivity = new Intent(MainActivity.this, MainActivity.class);
                manager.notify(idNotification++, notificacion.build());
                cancel();
            }
        };
        Timer aux = new Timer();
        aux.schedule(tt,time);
    }

}
