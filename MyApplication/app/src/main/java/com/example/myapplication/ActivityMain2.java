package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityMain2 extends AppCompatActivity {

    private int r, v, b;
    private SeekBar sr, sv, sb;
    private TextView tr, tv, tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Theme, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        sr = (SeekBar) findViewById(R.id.rouge);
        sv = (SeekBar) findViewById(R.id.vert);
        sb = (SeekBar) findViewById(R.id.bleu);

        tr = (TextView) findViewById(R.id.tr);
        tv = (TextView) findViewById(R.id.tv);
        tb = (TextView) findViewById(R.id.tb);

        sr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r = progress;
                tr.setText(r);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }





}
