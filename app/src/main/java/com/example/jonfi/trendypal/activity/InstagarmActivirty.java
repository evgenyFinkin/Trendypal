package com.example.jonfi.trendypal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonfi.trendypal.R;
import com.example.jonfi.trendypal.data_from_instagram.InstagramResponse;
import com.example.jonfi.trendypal.data_from_instagram.RestClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonfi on 20/02/2018.
 */

public class InstagarmActivirty extends AppCompatActivity {


    private TextView myName;
    private ImageView myPic;

    private String access_token = "";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_screen);

        // Get the access_token from the intent extra
        Intent i = this.getIntent();
        access_token = i.getStringExtra("access_token");

        myName = (TextView) findViewById(R.id.myName);
        myPic = (ImageView) findViewById(R.id.myPic);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Set the listview adapter
        fetchData();

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(InstagarmActivirty.this,
                                    "My anonymous firebase user is"
                                    +user.toString(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(InstagarmActivirty.this,
                                    "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void fetchData() {
        Call<InstagramResponse> call = RestClient.getRetrofitService().getUser(access_token);
        call.enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> call, Response<InstagramResponse> response) {

                if (response.body() != null) {

                    myName.setText(response.body().getData().getFull_name());
                    Picasso.with(InstagarmActivirty.this)
                            .load(response.body().getData().getProfile_picture())
                            .resize(800,800)
                            .into(myPic);
                }
            }

            @Override
            public void onFailure(Call<InstagramResponse> call, Throwable t) {
                //Handle failure
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}