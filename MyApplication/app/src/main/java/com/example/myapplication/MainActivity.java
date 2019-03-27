package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 0;
    private static final long SCAN_PERIOD = 10000;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    private static BluetoothAdapter bluetoothAdapter;

    private String address = null, name = null;
    private BluetoothSocket btSocket = null;
    private Set<BluetoothDevice> pairedDevices;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ArrayAdapter<String> listAdapter;
    private ArrayAdapter<BluetoothDevice> listDevices;
    private ListView devicesList;
    private Spinner menu;
    private Button recherche;
    private Button connexion;

    private boolean mScanning;
    private Handler handler;


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        menu = findViewById(R.id.menu);
        // devicesList = findViewById(R.id.devicesList);
        recherche = findViewById(R.id.recherche);
        connexion = findViewById(R.id.button);

        //+
        address = bluetoothAdapter.getAddress();

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listDevices = new ArrayAdapter<BluetoothDevice>(this, android.R.layout.simple_list_item_1);
        menu.setAdapter(listAdapter);
        checkBluetoothState();


        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                    if (checkCoarseLocationPermission()) {
                        listAdapter.clear();
                        listDevices.clear();
                        bluetoothAdapter.startDiscovery();
                    }
                } else {
                    checkBluetoothState();
                }
            }
        });
        checkCoarseLocationPermission();

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });*/
                Intent ActivityMain2 = new Intent(getApplicationContext(), ActivityMain2.class);
                startActivity(ActivityMain2);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(devicesFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(devicesFoundReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
        registerReceiver(devicesFoundReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(devicesFoundReceiver);
    }

    private boolean checkCoarseLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
            return false;
        } else {
            return true;
        }
    }

    private void checkBluetoothState() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth pas supporté sur cet appareil", Toast.LENGTH_SHORT).show();
        } else {
            if (bluetoothAdapter.isEnabled()) {
                if (bluetoothAdapter.isDiscovering()) {
                    Toast.makeText(this, "Recherche d'appareils...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Bluetooth est activé", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Activez le Bluetooth", Toast.LENGTH_SHORT).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            checkBluetoothState();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Autorisé", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Interdit", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private final BroadcastReceiver devicesFoundReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                listAdapter.add(device.getName() + "\n" + device.getAddress());
                listDevices.add(device);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                recherche.setText("recherche");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                recherche.setText("En cours");
            }
        }
    };
}

   /* private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    bluetoothAdapter.stopLeScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            bluetoothAdapter.startLeScan(leScanCallback);
        } else {
            mScanning = false;
            bluetoothAdapter.stopLeScan(leScanCallback);
        }
    }*/
