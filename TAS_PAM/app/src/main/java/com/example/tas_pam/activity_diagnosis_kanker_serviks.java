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

public class activity_diagnosis_kanker_serviks extends AppCompatActivity {

    ListView listView_serviks;
    ArrayAdapter<String> adapter;
    String[] question;
    Button btn_diagnosis_serviks;
    String output_diagnosis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_kanker_serviks);

        listView_serviks = findViewById(R.id.listView_serviks);
        btn_diagnosis_serviks = findViewById(R.id.btn_diagnosis_serviks);


        question = getResources().getStringArray(R.array.gejala_kanker_serviks);

        adapter = new ArrayAdapter<>(this, R.layout.item, question);
        listView_serviks.setAdapter(adapter);

        btn_diagnosis_serviks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float gejala_checked = 0;
                float hasil_diagnosis;

                for (int i=0; i < listView_serviks.getCount(); i++){
                    if (listView_serviks.isItemChecked(i)){
                        gejala_checked += 1;
                    }
                }
                hasil_diagnosis = gejala_checked / listView_serviks.getCount();
                hasil_diagnosis = hasil_diagnosis * 100;

                if (hasil_diagnosis >= 60){
                    output_diagnosis = "Anda berpotensi mengidap kanker serviks sebesar "+String.format("%.0f", hasil_diagnosis)+"% "+getResources().getString(R.string.hasil_diagnosis_hati_tinggi);
                    Intent intentToHasilDiagnosisTinggi = new Intent(activity_diagnosis_kanker_serviks.this, MapsActivity.class);
                    intentToHasilDiagnosisTinggi.putExtra("hasil_diagnosis_kanker_serviks_tinggi", output_diagnosis);
                    startActivity(intentToHasilDiagnosisTinggi);

                }
                else {
                    output_diagnosis = "Anda berpotensi mengidap kanker serviks sebesar "+String.format("%.0f", hasil_diagnosis)+"% "+getResources().getString(R.string.hasil_diagnosis_hati_rendah);
                    Intent intentToHasilDiagnosisRendah = new Intent(activity_diagnosis_kanker_serviks.this, hasil_diagnosis_kurang_60_persen.class);
                    intentToHasilDiagnosisRendah.putExtra("hasil_diagnosis_kanker_serviks_rendah", output_diagnosis);
                    startActivity(intentToHasilDiagnosisRendah);
                }
            }
        });

    }
    }
