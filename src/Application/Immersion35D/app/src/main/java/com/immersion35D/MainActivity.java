package com.immersion35D;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    Button calibrage;
    Button controler;
    Button immersion;
    private BluetoothAdapter bluetoothAdapter;


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
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
        bSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
        mmServerSocket = myBluetooth.listenUsingInsecureRfcommWithServiceRecord("appname", myUUID);
        bSocket.connect();
    }



    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBluetoothState();
        try {
            bluetooth_connect_device();
            if(bSocket.isConnected())
                bSocket.getOutputStream().write("O".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        calibrage = findViewById(R.id.calibre);
        controler = findViewById(R.id.control);
        immersion = findViewById(R.id.immersion);


        calibrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bSocket.isConnected()) {
                    Intent ActivityMain3 = new Intent(getApplicationContext(), ActivityMain3.class);
                    startActivity(ActivityMain3);
                    try {
                        bSocket.getOutputStream().write("C".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                } else{popup();}
            }
        });

        controler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bSocket.isConnected()) {
                    Intent ActivityMain2 = new Intent(getApplicationContext(), ActivityMain2.class);
                    startActivity(ActivityMain2);
                    try {
                        bSocket.getOutputStream().write("T".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }else {popup();}
            }
        });

        immersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bSocket.isConnected()) {
                    try {
                        bSocket.getOutputStream().write("I".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {popup();}
            }
        });

    }

    public void popup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Alerte");
        builder.setPositiveButton("Vous n'êtes pas connecté avec l'équipement",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkBluetoothState()
    {
        if (bluetoothAdapter == null)
        {
            Toast.makeText(this, "Bluetooth pas supporté sur cet appareil", Toast.LENGTH_SHORT).show();
        } else{
            if(bluetoothAdapter.isEnabled())
            {
                    Toast.makeText(this, "Bluetooth est sactivé", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Activez le Bluetooth", Toast.LENGTH_SHORT).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, 1);
            }
        }
    }
}
