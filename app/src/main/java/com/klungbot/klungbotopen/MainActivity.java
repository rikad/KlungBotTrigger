package com.klungbot.klungbotopen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultTitle = getResources().getString(R.string.title);
        String defaultIp = getResources().getString(R.string.ip);
        String defaultPort = getResources().getString(R.string.port);

        String title = sharedPref.getString(getString(R.string.title),defaultTitle);
        String ip = sharedPref.getString(getString(R.string.ip),defaultIp);
        String port = sharedPref.getString(getString(R.string.port),defaultPort);
        final String addr = "http://"+ip+":"+port+"/100/60/80/";

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        ImageButton btnPlay = findViewById(R.id.btnStart);

        btnPlay.setOnLongClickListener(
            new Button.OnLongClickListener() {
                public boolean onLongClick (View v) {
                    return goPlay(v,addr);
                }
            }
        );
    }

    public void goSet(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public boolean goPlay(View view,String addr) {

        final TextView textView = (TextView) findViewById(R.id.text);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, addr,

            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    displayToast("Playing ... :)");
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    displayToast(error.getMessage());
                }
            }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return true;
    }

    public void displayToast(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_LONG).show();
    }
}
