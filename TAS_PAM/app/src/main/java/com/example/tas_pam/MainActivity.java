package com.example.tas_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btnHati, btnParuParu, btnPayudara, btnProstat, btnDarah, btnUsus, btnServiks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHati = findViewById(R.id.btnHati);
        btnParuParu = findViewById(R.id.btnParuParu);
        btnPayudara = findViewById(R.id.btnPayudara);
        btnProstat = findViewById(R.id.btnProstat);
        btnDarah = findViewById(R.id.btnDarah);
        btnUsus = findViewById(R.id.btnUsus);
        btnServiks = findViewById(R.id.btnServiks);


        btnHati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToHati = new Intent(MainActivity.this, activity_diagnosis_kanker_hati.class);
                startActivity(intentToHati);
            }
        });

        btnParuParu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToParuParu = new Intent(MainActivity.this, activity_diagnosis_kanker_paru_paru.class);
                startActivity(intentToParuParu);
            }
        });

        btnPayudara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToPayudara = new Intent(MainActivity.this, activity_diagnosis_kanker_payudara.class);
                startActivity(intentToPayudara);
            }
        });

        btnProstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToProstat = new Intent(MainActivity.this, activity_diagnosis_kanker_prostat.class);
                startActivity(intentToProstat);
            }
        });

        btnDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToDarah = new Intent(MainActivity.this, activity_diagnosis_kanker_darah.class);
                startActivity(intentToDarah);
            }
        });

        btnUsus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToUsus = new Intent(MainActivity.this, activity_diagnosis_kanker_usus.class);
                startActivity(intentToUsus);
            }
        });

        btnServiks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToServiks = new Intent(MainActivity.this, activity_diagnosis_kanker_serviks.class);
                startActivity(intentToServiks);
            }
        });

    }
}