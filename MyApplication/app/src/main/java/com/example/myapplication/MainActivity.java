package com.example.myapplication;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {





    private Button calibrage;
    private Button controler;
    private Button immersion;


    static BluetoothSocket bSocket = null;

    String address = null , name=null;
    BluetoothAdapter myBluetooth = null;
    Set<BluetoothDevice> pairedDevices;
    BluetoothServerSocket mmServerSocket;

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @SuppressLint("HardwareIds")
    private void bluetooth_connect_device() throws IOException
    {
        try
        {
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            address = myBluetooth.getAddress();
            pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size()>0)
            {
                for(BluetoothDevice bt : pairedDevices)
                {
                    address=bt.getAddress() ;name = bt.getName();
                    Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception ignored){}
        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        bSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
        mmServerSocket = myBluetooth.listenUsingInsecureRfcommWithServiceRecord("appname", myUUID);
        bSocket.connect();
        //try { t1.setText("BT Name: "+name+"\nBT Address: "+address); }
        //catch(Exception e){}
    }


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            bluetooth_connect_device();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
