package com.example.tas_pam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class activity_diagnosis_kanker_usus extends AppCompatActivity {

    ListView listView_usus;
    ArrayAdapter<String> adapter;
    String[] question;
    Button btn_diagnosis_usus;
    String output_diagnosis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_kanker_usus);

        listView_usus = findViewById(R.id.listView_usus);
        btn_diagnosis_usus = findViewById(R.id.btn_diagnosis_usus);


        question = getResources().getStringArray(R.array.gejala_kanker_usus);

        adapter = new ArrayAdapter<>(this, R.layout.item, question);
        listView_usus.setAdapter(adapter);

        btn_diagnosis_usus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float gejala_checked = 0;
                float hasil_diagnosis;

                for (int i=0; i < listView_usus.getCount(); i++){
                    if (listView_usus.isItemChecked(i)){
                        gejala_checked += 1;
                    }
                }
                hasil_diagnosis = gejala_checked / listView_usus.getCount();
                hasil_diagnosis = hasil_diagnosis * 100;

                if (hasil_diagnosis >= 60){
                    output_diagnosis = "Anda berpotensi mengidap kanker usus sebesar "+String.format("%.0f", hasil_diagnosis)+"% "+getResources().getString(R.string.hasil_diagnosis_hati_tinggi);
                    Intent intentToHasilDiagnosisTinggi = new Intent(activity_diagnosis_kanker_usus.this, MapsActivity.class);
                    intentToHasilDiagnosisTinggi.putExtra("hasil_diagnosis_kanker_usus_tinggi", output_diagnosis);
                    startActivity(intentToHasilDiagnosisTinggi);

                }
                else {
                    output_diagnosis = "Anda berpotensi mengidap kanker usus sebesar "+String.format("%.0f", hasil_diagnosis)+"% "+getResources().getString(R.string.hasil_diagnosis_hati_rendah);
                    Intent intentToHasilDiagnosisRendah = new Intent(activity_diagnosis_kanker_usus.this, hasil_diagnosis_kurang_60_persen.class);
                    intentToHasilDiagnosisRendah.putExtra("hasil_diagnosis_kanker_usus_rendah", output_diagnosis);
                    startActivity(intentToHasilDiagnosisRendah);
                }
            }
        });

    }

    }
