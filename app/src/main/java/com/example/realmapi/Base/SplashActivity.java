package com.example.realmapi.Base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.realmapi.Room.RoomActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent i = new Intent(SplashActivity.this, RoomActivity.class);
        startActivity(i);
        finish();
    }
}