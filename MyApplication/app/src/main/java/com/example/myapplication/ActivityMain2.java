package com.example.myapplication;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import static com.example.myapplication.MainActivity.bSocket;

public class ActivityMain2 extends AppCompatActivity {

    //Valeur du rouge, vert, bleu et luminosité respectivement
    private int r, v, b, l;
    //Jauges pour la gestion du rouge, vert, bleu et de la luminosité respactivement
    private SeekBar sr, sv, sb, sl;
    //Affichage du rouge, vert, bleu et luminosité respectivement
    private TextView tr, tv, tb, tl;
    //


    String r1, v1, b1, couleurs;
    //Menu des themes
    private Spinner themes;
    private Button retour;
    private Button allumer;


    String etat = "A";

    //@SuppressLint("ClickableViewAccessibility")

    /*
    private void setw() throws IOException
    {
        bluetooth_connect_device();
        connexion.setOnTouchListener(new View.OnTouchListener()
        {   @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN) {led_on_off("f");}
            if(event.getAction() == MotionEvent.ACTION_UP){led_on_off("b");}
            return true;}
        });*/

    @SuppressLint("HardwareIds")
    /*private void bluetooth_connect_device() throws IOException
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
                    address=bt.getAddress();
                    name = bt.getName();
                    Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception ignored){}
        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
        btSocket.connect();
        //try { t1.setText("BT Name: "+name+"\nBT Address: "+address); }
        //catch(Exception e){}
    }*/


    private void envoiCouleurs(int a)
    {
        try
        {
            if (bSocket!=null)
            {
                if(a==1) {
                    couleurs = r1;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if(a==2) {
                    couleurs = v1;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if(a==3) {
                    couleurs = b1;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
               // btSocket.getOutputStream().write(this.r);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onOff()
    {
        try
        {
            if (bSocket!=null)
            {
                if (etat.equals("E")) {
                    etat = "A";
                }
                else if (etat.equals("A")){
                    etat = "E";
                }
                bSocket.getOutputStream().write(etat.getBytes());
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*try {
            bluetooth_connect_device();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // try {setw();} catch (Exception e) {}
        themes = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Theme, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        themes.setAdapter(adapter);


        sr = findViewById(R.id.rouge);
        sv = findViewById(R.id.vert);
        sb = findViewById(R.id.bleu);
        sl = findViewById(R.id.luminosite);

        tr = findViewById(R.id.tr);
        tv = findViewById(R.id.tv);
        tb = findViewById(R.id.tb);
        tl = findViewById(R.id.tl);
        tr.setText(""+r+"");
        tv.setText(""+v+"");
        tb.setText(""+b+"");
        tl.setText(""+l+"");

        retour = findViewById(R.id.retour);
        allumer = findViewById(R.id.allumer);

        themes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence themeSelectionne = (CharSequence) parent.getItemAtPosition(position);
                String s = themeSelectionne.toString();
                switch (s){
                    case "Theme 1":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme1();
                        break;
                    case "Theme 2":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme2();
                        break;
                    case "Theme 3":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme3();
                        break;
                    case "Theme 4":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme4();
                        break;
                    case "Theme 5":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme5();
                        break;
                    case "Guirelande":
                        sr.setEnabled(false);
                        sv.setEnabled(false);
                        sb.setEnabled(false);
                        sl.setEnabled(false);
                        try {
                            bSocket.getOutputStream().write("G".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                etat = "A";
                for (int i=1; i<=3; i++)
                {
                    envoiCouleurs(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //Gestion de l'affichage des valeurs en instantané
        sr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r1="r"+progress+";";
                r = progress;
                tr.setText(""+progress+"");
                etat = "A";
                envoiCouleurs(1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sv.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                v1="v"+progress+";";
                v = progress;
                tv.setText(""+progress+"");
                etat = "A";
                envoiCouleurs(2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                b1="b"+progress+";";
                b = progress;
                tb.setText(""+progress+"");
                etat = "A";
                envoiCouleurs(3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                l = progress;
                tl.setText(""+progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*r = sr.getProgress();
        v = sv.getProgress();
        b = sb.getProgress()*/
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMain4 = new Intent(getApplicationContext(), ActivityMain4.class);
                startActivity(ActivityMain4);
                finish();
            }
        });

        allumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOff();
            }
        });


    }

    public void theme1(){
        this.r = 44;
        this.r1= ""+this.r;
        this.v = 120;
        this.v1= ""+this.v;
        this.b = 78;
        this.b1= ""+this.b;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme2(){
        this.r = 112;
        this.r1= ""+this.r;
        this.v = 0;
        this.v1= ""+this.v;
        this.b = 255;
        this.b1= ""+this.b;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme3(){
        this.r = 12;
        this.r1= ""+this.r;
        this.v = 10;
        this.v1= ""+this.v;
        this.b = 25;
        this.b1= ""+this.b;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme4(){
        this.r = 200;
        this.r1= ""+this.r;
        this.v = 218;
        this.v1= ""+this.v;
        this.b = 255;
        this.b1= ""+this.b;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme5(){
        this.r = 6;
        this.r1= ""+this.r;
        this.v = 31;
        this.v1= ""+this.v;
        this.b = 99;
        this.b1= ""+this.b;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }




}
