package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;
import android.bluetooth.*;
import android.util.Log;
import android.widget.TextView;

import static com.example.myapplication.MainActivity.*;
import static java.lang.Thread.sleep;

public class ActivityMain3 extends AppCompatActivity {

    Button oui;
    Button non;
    Button ok;
    String text = "";

    InputStream tmpIn = null;
    String TAG = "MainActivity";
    TextView view_data;
    InputStream mmInStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        oui = findViewById(R.id.oui);
        non = findViewById(R.id.non);
        ok = findViewById(R.id.ok);

        view_data = (TextView) findViewById(R.id.cal);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.cal).setSelected(true);
                sendMess();
            }
        });
        t1.start();
        System.out.println("la");

        oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bSocket.getOutputStream().write("Y".getBytes());
                    text = "";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
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
                finish();
            }
        });



    }

    public void sendMess()
    {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes;
        Log.d(TAG, "ConnectedThread: Starting.");

            try {
                tmpIn = bSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mmInStream = tmpIn;
        while (true) {
            try {
                bytes = mmInStream.read(buffer);
                String incomingMessage = new String(buffer, 0, bytes);
                text += incomingMessage;
                System.out.println(text);

                view_data.setText(text);


            } catch (IOException e) {
            }

        }

    }

}

