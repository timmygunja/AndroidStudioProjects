package com.example.app_4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mainButton;
    final String TAG = "States";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnStart = findViewById(R.id.button_start);
        final Button btnStop = findViewById(R.id.button_stop);

// запуск службы
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// используем явный вызов службы
                startService(
                        new Intent(MainActivity.this, PlayService.class));
            }
        });

// остановка службы
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(
                        new Intent(MainActivity.this, PlayService.class));
            }
        });
    }

}