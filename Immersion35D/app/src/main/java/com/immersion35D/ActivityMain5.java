package com.immersion35D;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.immersion35D.MainActivity.bSocket;

public class ActivityMain5 extends AppCompatActivity {


    int valr1, valr2, valv1, valv2, valb1, valb2, lumin;
    //Jauges pour la gestion du rouge, vert, bleu des deux couleurs et de la luminosité respactivement
    SeekBar svalr1, svalr2, svalv1, svalv2, svalb1, svalb2, svallum;
    //Affichage du rouge, vert, bleu des deux couleurs et luminosité respectivement
    TextView tvalr1, tvalr2, tvalv1, tvalv2, tvalb1, tvalb2, tvallum;
    String r1, v1, b1, r2, v2, b2, l, couleurs;
    Button retour2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        tvalr1 = findViewById(R.id.valr1);
        tvalv1 = findViewById(R.id.valv1);
        tvalb1 = findViewById(R.id.valb1);
        tvalr2 = findViewById(R.id.valr2);
        tvalv2 = findViewById(R.id.valv2);
        tvalb2 = findViewById(R.id.valb2);
        tvallum = findViewById(R.id.vallum);

        svalr1 = findViewById(R.id.rouge1);
        svalv1 = findViewById(R.id.vert1);
        svalb1 = findViewById(R.id.bleu1);
        svalr2 = findViewById(R.id.rouge2);
        svalv2 = findViewById(R.id.vert2);
        svalb2 = findViewById(R.id.bleu2);
        svallum = findViewById(R.id.lum);

        retour2 = findViewById(R.id.dRetour);


        svalr1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r1="r"+progress+";";
                valr1 = progress;
                tvalr1.setText(""+progress+"");
                envoiCouleurs2(1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        svalv1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                v1="v"+progress+";";
                valv1 = progress;
                tvalv1.setText(""+progress+"");
                envoiCouleurs2(2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        svalb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                b1="b"+progress+";";
                valb1 = progress;
                tvalb1.setText(""+progress+"");
                envoiCouleurs2(3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        svalr2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r2="d"+progress+";";
                valr2 = progress;
                tvalr2.setText(""+progress+"");
                envoiCouleurs2(4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        svalv2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                v2="g"+progress+";";
                valv2 = progress;
                tvalv2.setText(""+progress+"");
                envoiCouleurs2(5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        svalb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                b2="e"+progress+";";
                valb2 = progress;
                tvalb2.setText(""+progress+"");
                envoiCouleurs2(6);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        svallum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                l="l"+progress+";";
                lumin = progress;
                tvallum.setText(""+progress+"");
                envoiCouleurs2(7);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        retour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bSocket.getOutputStream().write("T".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent ActivityMain2 = new Intent(getApplicationContext(), ActivityMain2.class);
                startActivity(ActivityMain2);
                finish();
            }
        });





    }

    private void envoiCouleurs2(int a) {
        try {
            if (bSocket != null) {
                if (a == 1) {
                    couleurs = r1;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if (a == 2) {
                    couleurs = v1;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if (a == 3) {
                    couleurs = b1;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if (a == 4) {
                    couleurs = r2;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if (a == 5) {
                    couleurs = v2;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if (a == 6) {
                    couleurs = b2;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
                if (a == 7) {
                    couleurs = l;
                    for (char e : couleurs.toCharArray()) {
                        bSocket.getOutputStream().write(e);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}