package com.example.jonfi.trendypal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jonfi.trendypal.R;
import com.example.jonfi.trendypal.dialog.AuthenticationDialog;
import com.example.jonfi.trendypal.listeners.AuthenticationListener;

public class MainActivity extends AppCompatActivity implements AuthenticationListener {

    private AuthenticationDialog auth_dialog;
    private Button btn_get_access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get_access_token = (Button) findViewById(R.id.btn_get_access_token);

        btn_get_access_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth_dialog = new AuthenticationDialog(MainActivity.this, MainActivity.this);
                auth_dialog.setCancelable(true);
                auth_dialog.show();
            }
        });
    }


    @Override
    public void onCodeReceived(String access_token) {
        if (access_token == null) {
            auth_dialog.dismiss();
        }

        Intent i = new Intent(MainActivity.this, InstagarmActivirty.class);
        i.putExtra("access_token", access_token);
        startActivity(i);

    }

}