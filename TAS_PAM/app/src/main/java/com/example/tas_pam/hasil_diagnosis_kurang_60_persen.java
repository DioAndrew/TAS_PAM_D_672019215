package com.example.tas_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class hasil_diagnosis_kurang_60_persen extends AppCompatActivity {

    TextView txt_hasil_kurang_60;
    ListView listView_cara_mencegah_kanker;
    String[] rekomendasi_terhindar_kanker;
    ArrayAdapter<String> adapter;
    String output_hati, output_paru_paru, output_payudara, output_prostat, output_darah, output_usus, output_seviks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosis_kurang60_persen);

        txt_hasil_kurang_60 = findViewById(R.id.txt_hasil_kurang_60);
        listView_cara_mencegah_kanker = findViewById(R.id.listView_cara_mencegah_kanker);
        
        Intent intent = getIntent();
        
        output_hati = intent.getStringExtra("hasil_diagnosis_kanker_hati_rendah");
        output_paru_paru = intent.getStringExtra("hasil_diagnosis_kanker_paru_paru_rendah");
        output_payudara = intent.getStringExtra("hasil_diagnosis_kanker_payudara_rendah");
        output_prostat = intent.getStringExtra("hasil_diagnosis_kanker_prostat_rendah");
        output_darah = intent.getStringExtra("hasil_diagnosis_kanker_darah_rendah");
        output_usus = intent.getStringExtra("hasil_diagnosis_kanker_usus_rendah");
        output_seviks = intent.getStringExtra("hasil_diagnosis_kanker_serviks_rendah");


        if (output_hati != null){
            txt_hasil_kurang_60.setText(output_hati);
        }
        else if (output_paru_paru != null){
            txt_hasil_kurang_60.setText(output_paru_paru);
        }
        else if (output_payudara != null){
            txt_hasil_kurang_60.setText(output_payudara);
        }
        else if (output_prostat != null){
            txt_hasil_kurang_60.setText(output_prostat);
        }
        else if (output_darah != null){
            txt_hasil_kurang_60.setText(output_darah);
        }
        else if (output_usus != null){
            txt_hasil_kurang_60.setText(output_usus);
        }
        else if (output_seviks != null){
            txt_hasil_kurang_60.setText(output_seviks);
        }

        rekomendasi_terhindar_kanker = getResources().getStringArray(R.array.cara_terhindar_dari_kanker);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rekomendasi_terhindar_kanker);
        listView_cara_mencegah_kanker.setAdapter(adapter);

        

    }
}