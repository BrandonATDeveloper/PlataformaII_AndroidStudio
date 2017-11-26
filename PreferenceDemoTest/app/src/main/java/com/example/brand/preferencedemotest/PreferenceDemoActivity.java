package com.example.brand.preferencedemotest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreferenceDemoActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_demo);
        Button btnPrefs = (Button) findViewById(R.id.btnPrefs);
        Button btnGetPrefs = (Button) findViewById(R.id.btnGetPreferences);
        textView = (TextView) findViewById(R.id.txtPrefs);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnPrefs:
                        Intent intent = new Intent(PreferenceDemoActivity.this,
                                PrefsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.btnGetPreferences:
                        displaySharedPreferences();
                        break;

                    default:
                        break;
                }
            }
        };
        btnPrefs.setOnClickListener(listener);
        btnGetPrefs.setOnClickListener(listener);
    }


    private void displaySharedPreferences() {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(PreferenceDemoActivity.this);

        String username = prefs.getString("username", "Default NickName");
        String passw = prefs.getString("password", "Default Password");
        boolean checkBox = prefs.getBoolean("checkBox", false);
        String listPrefs = prefs.getString("listpref", "Default list prefs");

        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Password: " + passw + "\n");
        builder.append("Keep me logged in: " + String.valueOf(checkBox) + "\n");
        builder.append("List preference: " + listPrefs);

        textView.setText(builder.toString());
    }
}
