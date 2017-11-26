package com.example.brand.app_5_notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //lista de cosas globales
    NotificationManager manager;
    NotificationCompat.Builder notificacionUno;//Generador de Notificaciones
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//Encargado del servicios de las notificaciones
        list = (ListView)findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarNotificacion(position);
            }
        });
    }

    public void mostrarNotificacion (int position){
        //Notification miNotificacion = new Notification(R.mipmap.ic_launcher,"Hola Mundo", Notification.PRIORITY_HIGH);ya no se usa morro
        switch(position){
            case 0: {
                //Construir la notificacion
                notificacionUno = new NotificationCompat.Builder(getApplicationContext());
                notificacionUno.setContentTitle("Titulo de Notificacion Uno");
                notificacionUno.setContentText("Hola Mundo Notificacion Uno");
                notificacionUno.setSmallIcon(R.mipmap.ic_launcher);
                notificacionUno.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                notificacionUno.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificacionUno.setTicker("Texto Uno");
                //Correr notificacion
                Intent mismaActivity = new Intent(MainActivity.this, MainActivity.class);
                NotificationCompat.BigTextStyle estilo = new NotificationCompat.BigTextStyle(notificacionUno);
                estilo.setSummaryText("Esto es un texto largo que se puede repetir");
                estilo.setBigContentTitle("Este es un titulo");
                estilo.bigText("Esto es un texto largo que se puede repetir"
                        + "Esto es un texto largo que se puede repetir"
                        +"Esto es un texto largo que se puede repetir");



                manager.notify(10, estilo.build());
            }break;
            case 1: {
                //Construir la notificacion
                notificacionUno = new NotificationCompat.Builder(getApplicationContext());
                notificacionUno.setContentTitle("Titulo de Notificacion Dos");
                notificacionUno.setContentText("Hola Mundo Notificacion Dos");
                notificacionUno.setSmallIcon(R.mipmap.ic_launcher);
                notificacionUno.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song));
                notificacionUno.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificacionUno.setTicker("Texto Dos");
                //Correr notificacion
                Intent mismaActivity = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 1, mismaActivity, PendingIntent.FLAG_ONE_SHOT);
                notificacionUno.setContentIntent(intent);
                manager.notify(20, notificacionUno.build());
            }break;
            case 2:{
                notificacionUno = new NotificationCompat.Builder(getApplicationContext());
                notificacionUno.setContentTitle("Titulo de Notificacion Tres");
                notificacionUno.setContentText("Hola Mundo Notificacion Tres");
                notificacionUno.setSmallIcon(R.mipmap.ic_launcher_round);
                notificacionUno.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song));
                notificacionUno.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificacionUno.setTicker("Texto Tres");
                //Correr notificacion
                Intent mismaActivity = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 1, mismaActivity, PendingIntent.FLAG_ONE_SHOT);
                notificacionUno.setContentIntent(intent);
                notificacionUno.setAutoCancel(true);
                NotificationCompat.BigPictureStyle estilo = new NotificationCompat.BigPictureStyle(notificacionUno);
                Bitmap imagen = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                estilo.bigPicture(imagen);
                estilo.setBigContentTitle("Titulo de la notificacion Tres");
                estilo.setSummaryText("Resumen");
                estilo.bigLargeIcon(imagen);
                manager.notify(30, notificacionUno.build());
            }break;
            case 3: {
                //Construir la notificacion
                notificacionUno = new NotificationCompat.Builder(getApplicationContext());
                notificacionUno.setContentTitle("Titulo de Notificacion Cuatro");
                notificacionUno.setContentText("Hola Mundo Notificacion Cuatro");
                notificacionUno.setSmallIcon(R.mipmap.ic_launcher);
                notificacionUno.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.song));
                notificacionUno.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificacionUno.setTicker("Texto Dos");
                //Correr notificacion
                Intent mismaActivity = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 1, mismaActivity, PendingIntent.FLAG_ONE_SHOT);
                notificacionUno.addAction(android.R.drawable.ic_input_delete,"misma activity",intent);
                Intent nuevaActivity = new Intent(MainActivity.this, Secundaria.class);
                PendingIntent intent2 = PendingIntent.getActivity(getApplicationContext(), 1, nuevaActivity, PendingIntent.FLAG_ONE_SHOT);
                notificacionUno.addAction(android.R.drawable.ic_input_add, "nueva activity",intent2);
                notificacionUno.setContentIntent(intent);
                manager.notify(40, notificacionUno.build());
            }break;
        }
    }

}
