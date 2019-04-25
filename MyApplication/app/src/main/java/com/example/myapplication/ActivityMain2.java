package com.example.myapplication;

import android.annotation.SuppressLint;
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
import static com.example.myapplication.MainActivity.bSocket;

public class ActivityMain2 extends AppCompatActivity {

    //Valeur du rouge, vert, bleu et luminosité respectivement
    private int r, v, b, l;
    //Jauges pour la gestion du rouge, vert, bleu et de la luminosité respactivement
    private SeekBar sr, sv, sb, sl;
    //Affichage du rouge, vert, bleu et luminosité respectivement
    private TextView tr, tv, tb, tl;
    //


    String r1, v1, b1, l1, couleurs;
    //Menu des themes
    Spinner themes;
    Button retour;
    Button allumer;


    String etat = "A";

    @SuppressLint("HardwareIds")
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
                if(a==4) {
                    couleurs = l1;
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
                    case "Blanc":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(128,128,128,50);
                        break;
                    case "Rouge":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(255,0,0,50);
                        break;
                    case "Vert":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(0,255,0,50);
                        break;
                    case "Bleu":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(0,0,255,50);
                        break;
                    case "Jaune":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(255,255,0,50);
                        break;
                    case "Magenta":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(255,0,255,50);
                        break;
                    case "Cyan":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(0,255,255,50);
                        break;
                    case "Orange":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(255,50,0,50);
                        break;
                    case "Rose":
                        sr.setEnabled(true);
                        sv.setEnabled(true);
                        sb.setEnabled(true);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("T".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        theme(255,0,40,50);
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
                    case "Fondu":
                        sr.setEnabled(false);
                        sv.setEnabled(false);
                        sb.setEnabled(false);
                        sl.setEnabled(true);
                        try {
                            bSocket.getOutputStream().write("F".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                etat = "A";
                for (int i=1; i<=4; i++)
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
                l1="l"+progress+";";
                l = progress;
                tl.setText(""+progress+"");
                etat = "A";
                envoiCouleurs(4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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

    public void theme(int rd,int vt,int be, int lm){
        this.r = rd;
        this.r1= ""+this.r;
        this.v = vt;
        this.v1= ""+this.v;
        this.b = be;
        this.b1= ""+this.b;
        this.l = lm;
        this.l1= ""+this.l;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
        sl.setProgress(this.l);

    }


}
