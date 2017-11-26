package com.example.lap_lenovo.webviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    Button back, forward, search;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = (Button)findViewById(R.id.back);
        forward = (Button)findViewById(R.id.forward);
        search = (Button)findViewById(R.id.search);
        url = (EditText)findViewById(R.id.url);
        webView=(WebView)findViewById(R.id.webView);
//      webView.loadUrl("http://www.google.com.mx");
        if (webView.isSelected()){
                String urlRefresh = webView.getUrl();
                url.setText(urlRefresh);
        }

        WebSettings configuracion=webView.getSettings();
        configuracion.setAppCacheEnabled(true);
        configuracion.setJavaScriptEnabled(true);
        configuracion.setAllowContentAccess(true);
        configuracion.setBuiltInZoomControls(true);
        configuracion.setSupportZoom(true);
        configuracion.setDisplayZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest resource) {
                return shouldOverrideUrlLoading(view, resource);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()){
                    String urlRefresh = webView.getUrl();
                    url.setText(urlRefresh);
                    back();
                }else{
                    Toast.makeText(MainActivity.this, "Ultima pagina web hacia atras", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoForward()){
                    String urlRefresh = webView.getUrl();
                    url.setText(urlRefresh);
                    forward();
                }else{
                    Toast.makeText(MainActivity.this, "Ultima pagina web hacia adelante", Toast.LENGTH_SHORT).show();
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
                String urlRefresh = webView.getUrl();
                url.setText(urlRefresh);
            }
        });
    }

    public void search(){
        webView.loadUrl("http://" + url.getText().toString());
    }

    public void back(){
        webView.goBack();
    }

    public void forward(){
        webView.goForward();
    }

}
