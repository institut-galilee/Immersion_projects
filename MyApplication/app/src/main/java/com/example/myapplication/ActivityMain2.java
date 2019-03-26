package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityMain2 extends AppCompatActivity {

    //Valeur du rouge, vert, bleu et luminosité respectivement
    private int r, v, b, l;
    //Jauges pour la gestion du rouge, vert, bleu et de la luminosité respactivement
    private SeekBar sr, sv, sb, sl;
    //Affichage du rouge, vert, bleu et luminosité respectivement
    private TextView tr, tv, tb, tl;
    //Menu des themes
    private Spinner themes;
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        themes = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Theme, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        themes.setAdapter(adapter);


        sr = (SeekBar) findViewById(R.id.rouge);
        sv = (SeekBar) findViewById(R.id.vert);
        sb = (SeekBar) findViewById(R.id.bleu);
        sl = (SeekBar) findViewById(R.id.luminosite);

        tr = (TextView) findViewById(R.id.tr);
        tv = (TextView) findViewById(R.id.tv);
        tb = (TextView) findViewById(R.id.tb);
        tb = (TextView) findViewById(R.id.tl);
        tr.setText(""+r+"");
        tv.setText(""+v+"");
        tb.setText(""+b+"");
        tl.setText(""+l+"");

        retour = (Button) findViewById(R.id.retour);

        themes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence themeSelectionne = (CharSequence) parent.getItemAtPosition(position);
                String s = themeSelectionne.toString();
                switch (s){
                    case "Theme 1":
                        theme1();
                        break;
                    case "Theme 2":
                        theme2();
                        break;
                    case "Theme 3":
                        theme3();
                        break;
                    case "Theme 4":
                        theme4();
                        break;
                    case "Theme 5":
                        theme5();
                        break;
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
                r = progress;
                tr.setText(""+progress+"");
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
                v = progress;
                tv.setText(""+progress+"");
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
                b = progress;
                tb.setText(""+progress+"");
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
                Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity);
                finish();
            }
        });


    }

    public void theme1(){
        this.r = 44;
        this.v = 120;
        this.b = 78;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme2(){
        this.r = 112;
        this.v = 0;
        this.b = 255;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme3(){
        this.r = 12;
        this.v = 10;
        this.b = 25;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme4(){
        this.r = 200;
        this.v = 218;
        this.b = 255;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }

    public void theme5(){
        this.r = 6;
        this.v = 31;
        this.b = 99;
        sr.setProgress(this.r);
        sv.setProgress(this.v);
        sb.setProgress(this.b);
    }




}
