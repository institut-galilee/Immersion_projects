package com.immersion35D;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import static com.immersion35D.MainActivity.*;

public class ActivityMain3 extends AppCompatActivity {

    Button oui;
    Button non;
    Button ok;
    String text = "";
    TextView view;
    ScrollView view_data;
    InputStream tmpIn = null;


    InputStream mmInStream;
    boolean aFini = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        oui = findViewById(R.id.oui);
        non = findViewById(R.id.non);
        ok = findViewById(R.id.ok);
        view_data = findViewById(R.id.cal2);
        view = findViewById(R.id.cal);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {


                byte[] buffer = new byte[2048];  // buffer store for the stream
                int bytes;

                try {
                    tmpIn = bSocket.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mmInStream = tmpIn;
                while (aFini) {
                    try {
                        bytes = mmInStream.read(buffer);
                        String incomingMessage = new String(buffer, 0, bytes);
                        text += incomingMessage;
                        runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  view.setText(text);
                              }
                          });
                    } catch (IOException ignored) {
                    }
                }
            }
        });
        t1.start();

        oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    text = "";
                    bSocket.getOutputStream().write("Y".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    text = "";
                    bSocket.getOutputStream().write("N".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMain4 = new Intent(getApplicationContext(), ActivityMain4.class);
                startActivity(ActivityMain4);
                try {
                    bSocket.getOutputStream().write("O".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                aFini = false;
                text = "";
                finish();
            }
        });
    }
}

