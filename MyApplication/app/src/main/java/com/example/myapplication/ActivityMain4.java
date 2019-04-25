package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import static com.example.myapplication.MainActivity.bSocket;


public class ActivityMain4 extends AppCompatActivity {

    Button calibrage;
    Button controler;
    Button immersion;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calibrage = findViewById(R.id.calibre);
        controler = findViewById(R.id.control);
        immersion = findViewById(R.id.immersion);


        calibrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMain3 = new Intent(getApplicationContext(), ActivityMain3.class);
                startActivity(ActivityMain3);
                try {
                    bSocket.getOutputStream().write("C".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });



        controler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMain2 = new Intent(getApplicationContext(), ActivityMain2.class);
                startActivity(ActivityMain2);
                try {
                    bSocket.getOutputStream().write("T".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        immersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bSocket.getOutputStream().write("I".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
