package com.example.brand.hilos;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn , btn2;
    ProgressBar progressBar, progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subProceso();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TareaAsync tarea = new TareaAsync();
                tarea.execute();

            }
        });
    }

    class TareaAsync2 extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar2.setBackgroundColor(Color.GRAY);
            progressBar2.setMax(100);
            progressBar2.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar2.setProgress(0);
            progressBar2.setBackgroundColor(Color.WHITE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 11; i++){
                try{
                    Thread.sleep(1000);
                    update2(i*10);
                }catch (Exception e){}
            }
            return null;
        }
    }

    class TareaAsync extends AsyncTask<Integer, Integer, Void>{
        public TareaAsync() { //contructor
            super();
        }

        @Override
        protected void onPreExecute() { //asignar prioridades antes de que se ejecute el Hilo
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) { //despues de que el hilo termino, ¿que tarea realizar?
            super.onPostExecute(aVoid);
            TareaAsync2 tarea2 = new TareaAsync2();
            tarea2.execute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //cuando existe un cambio relevante en el hilo, ya no se usa mucho pero se puede usar
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Integer... integers) { //aqui va la accion que el hilo realizara
            for (int i = 0; i < 11; i++)
                try{
                    Thread.sleep(1000);
                    update2(i * 10);
                }catch (InterruptedException ie){}

            return null;
        }
    }

    void update2 (int i){
        progressBar.setProgress(i);
    }

    void update (int i){
        progressBar.setProgress(i);
    }

    void subProceso(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++)
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ie){
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Tiempo Terminado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

    }

}
