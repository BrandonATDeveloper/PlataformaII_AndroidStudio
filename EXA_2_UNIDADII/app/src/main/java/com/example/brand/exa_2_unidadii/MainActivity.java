package com.example.brand.exa_2_unidadii;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    String datoButton = "?", datoRadio = "?", datoSeek = "?";
    ListView list;
    Button verificar;
    NotificationManager manager;
    NotificationCompat.Builder notificacion;//Generador de Notificaciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        list = (ListView) findViewById(R.id.list);
        verificar = (Button) findViewById(R.id.verificar);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                seleccion(position);
            }
        });

        try{
            datoButton = getIntent().getExtras().getString("datoButton");
            datoRadio = getIntent().getExtras().getString("datoRadio");
            datoSeek = getIntent().getExtras().getString("datoSeek");
        }catch (Exception e){}
    }

    public void seleccion(int position) {
        switch (position) {
            case 0: {
                Intent intent = new Intent(MainActivity.this, Buttons.class);
                if (datoButton != ""){
                    datoButton = "No hubo selección";
                    intent.putExtra("datoButton",datoButton);
                }
                if (datoRadio != ""){
                    intent.putExtra("datoRadio",datoRadio);
                }
                if (datoSeek != ""){
                    intent.putExtra("datoSeek",datoSeek);
                }
                startActivity(intent);
                finish();
            }
            break;
            case 1: {
                Intent intent = new Intent(MainActivity.this, RadioButtons.class);
                if (datoButton != ""){
                    intent.putExtra("datoButton",datoButton);
                }
                if (datoRadio != ""){
                    datoRadio = "No hubo selección";
                    intent.putExtra("datoRadio",datoRadio);
                }
                if (datoSeek != ""){
                    intent.putExtra("datoSeek",datoSeek);
                }
                startActivity(intent);
                finish();
            }
            break;
            case 2: {
                Intent intent = new Intent(MainActivity.this, Slider.class);
                if (datoButton != ""){
                    intent.putExtra("datoButton",datoButton);
                }
                if (datoRadio != ""){
                    intent.putExtra("datoRadio",datoRadio);
                }
                if (datoSeek != ""){
                    datoSeek = "No hubo selección";
                    intent.putExtra("datoSeek",datoSeek);
                }
                startActivity(intent);
                finish();
            }
            break;
        }
    }

    public void verificar(View v) {
        notificacion = new NotificationCompat.Builder(getApplicationContext());
        notificacion.setContentTitle("Elecciones");
        notificacion.setContentText("Uno: " + datoButton + " - Dos: " + datoRadio + " - Tres: " + datoSeek);
        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //Correr notificacion
        manager.notify(10, notificacion.build());
    }

}
