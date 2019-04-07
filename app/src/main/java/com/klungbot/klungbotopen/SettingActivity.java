package com.klungbot.klungbotopen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultTitle = getResources().getString(R.string.title);
        String defaultIp = getResources().getString(R.string.ip);
        String defaultPort = getResources().getString(R.string.port);

        String title = sharedPref.getString(getString(R.string.title),defaultTitle);
        String ip = sharedPref.getString(getString(R.string.ip),defaultIp);
        String port = sharedPref.getString(getString(R.string.port),defaultPort);

        TextView txtTitle = findViewById(R.id.inpTitle);
        TextView txtIp = findViewById(R.id.inpIp);
        TextView txtPort = findViewById(R.id.inpPort);

        txtTitle.setText(title);
        txtIp.setText(ip);
        txtPort.setText(port);

    }

    public void saveSet(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        TextView inpTitle = findViewById(R.id.inpTitle);
        TextView inpIp = findViewById(R.id.inpIp);
        TextView inpPort = findViewById(R.id.inpPort);

        editor.putString(getString(R.string.title), inpTitle.getText().toString());
        editor.putString(getString(R.string.ip), inpIp.getText().toString());
        editor.putString(getString(R.string.port), inpPort.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved Please Restart App !",
                Toast.LENGTH_LONG).show();
        finish();
    }
}
