package com.repidot.slythmclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerGameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.getBaseContext();

        Intent intent = new Intent(MainActivity.this, UnityPlayerGameActivity.class);
        startActivity(intent);
    }
}