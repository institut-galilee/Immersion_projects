package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import android.bluetooth.*;
import android.util.Log;
import android.widget.TextView;

import static com.example.myapplication.MainActivity.bSocket;

public class ActivityMain3 extends AppCompatActivity {


    private Button oui;
    private Button non;


    String address = null , name=null;
    BluetoothAdapter myBluetooth = null;
    Set<BluetoothDevice> pairedDevices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //based on java.util.UUID
    // The local server socket
    private BluetoothServerSocket mmServerSocket;

    // based on android.bluetooth.BluetoothAdapter
    private BluetoothAdapter mAdapter;
    private BluetoothDevice remoteDevice;
    TextView text;

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

        oui = findViewById(R.id.oui);
        non = findViewById(R.id.non);

        text = (TextView) findViewById(R.id.textView6);

        BluetoothSocket socket = null;
        mAdapter = BluetoothAdapter.getDefaultAdapter();

        // Listen to the server socket if we're not connected
        // while (true) {

        try {
            // Create a new listening server socket
            Log.d((String) this.getTitle(), ".....Initializing RFCOMM SERVER....");

            // MY_UUID is the UUID you want to use for communication
            mmServerSocket = mAdapter.listenUsingRfcommWithServiceRecord("MyService",    myUUID);
            //mmServerSocket =  mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID); // you can also try  using In Secure connection...

            // This is a blocking call and will only return on a
            // successful connection or an exception
            socket = mmServerSocket.accept();

        } catch (Exception e) {

        }

        byte[] buffer = new byte[256];  // buffer store for the stream
        int bytes; // bytes returned from read()
        try {
            Log.d((String) this.getTitle(), "Closing Server Socket.....");
            mmServerSocket.close();

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams

            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();

            DataInputStream mmInStream = new DataInputStream(tmpIn);
            DataOutputStream mmOutStream = new DataOutputStream(tmpOut);
            // here you can use the Input Stream to take the string from the client  whoever is connecting
            //similarly use the output stream to send the data to the client

            // Read from the InputStream
            bytes = mmInStream.read(buffer);
            String readMessage = new String(buffer, 0, bytes);
            // Send the obtained bytes to the UI Activity


            text.setText(readMessage);
        } catch (Exception e) {
            //catch your exception here
        }
        // }

        oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMain2 = new Intent(getApplicationContext(), ActivityMain2.class);
                startActivity(ActivityMain2);
                try {
                    bSocket.getOutputStream().write("Y".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMain2 = new Intent(getApplicationContext(), ActivityMain2.class);
                startActivity(ActivityMain2);
                try {
                    bSocket.getOutputStream().write("N".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }

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
        bSocket.connect();
        //try { t1.setText("BT Name: "+name+"\nBT Address: "+address); }
        //catch(Exception e){}
    }
}
