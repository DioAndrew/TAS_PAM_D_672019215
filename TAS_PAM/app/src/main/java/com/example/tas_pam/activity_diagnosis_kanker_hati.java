package com.example.tas_pam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_diagnosis_kanker_hati extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    String[] question;
    Button btn_diagnosis_hati;
    String output_diagnosis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_kanker_hati);

        listView = findViewById(R.id.listView);
        btn_diagnosis_hati = findViewById(R.id.btn_diagnosis_hati);


        question = getResources().getStringArray(R.array.gejala_kanker_hati);

        adapter = new ArrayAdapter<>(this, R.layout.item, question);
        listView.setAdapter(adapter);

        btn_diagnosis_hati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float gejala_checked = 0;
                float hasil_diagnosis;

                for (int i=0; i < listView.getCount(); i++){
                    if (listView.isItemChecked(i)){
                        gejala_checked += 1;
                    }
                }
                hasil_diagnosis = gejala_checked / listView.getCount();
                hasil_diagnosis = hasil_diagnosis * 100;

                if (hasil_diagnosis >= 60){

                    output_diagnosis = "Anda berpotensi mengidap kanker hati sebesar "+String.format("%.0f", hasil_diagnosis)+"% "+getResources().getString(R.string.hasil_diagnosis_hati_tinggi);
                    Intent intentToHasilDiagnosisTinggi = new Intent(activity_diagnosis_kanker_hati.this, MapsActivity.class);
                    intentToHasilDiagnosisTinggi.putExtra("hasil_diagnosis_kanker_hati_tinggi", output_diagnosis);
                    startActivity(intentToHasilDiagnosisTinggi);

                }
                else {
                   output_diagnosis = "Anda berpotensi mengidap kanker hati sebesar "+String.format("%.0f", hasil_diagnosis)+"% "+getResources().getString(R.string.hasil_diagnosis_hati_rendah);
                   Intent intentToHasilDiagnosisRendah = new Intent(activity_diagnosis_kanker_hati.this, hasil_diagnosis_kurang_60_persen.class);
                   intentToHasilDiagnosisRendah.putExtra("hasil_diagnosis_kanker_hati_rendah", output_diagnosis);
                   startActivity(intentToHasilDiagnosisRendah);
                }
            }
        });

    }


}