package com.repidot.slythmclient;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlarmStarter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_start);

        SeekBar unlock = findViewById(R.id.seekBarUnlock);
        startService(new Intent(getApplicationContext(), AlarmService.class));

        unlock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int per = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                per = i;
                Log.d("percent", String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(per == 100) {
                    stopService(new Intent(getApplicationContext(), AlarmService.class));
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        seekBar.setProgress(0, true);
                    } else {
                        seekBar.setProgress(0);
                    }
                }
            }
        });
    }
}