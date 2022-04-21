package com.example.tas_pam;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tas_pam.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static  final  int REQ_CODE = 101;
    private double lat,lng;
    TextView txt_hasil_lebih_60;
    String output_hati, output_paru_paru, output_payudara, output_prostat, output_darah, output_usus, output_seviks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
        txt_hasil_lebih_60 = findViewById(R.id.txt_hasil_lebih_60);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        output_hati = intent.getStringExtra("hasil_diagnosis_kanker_hati_tinggi");
        output_paru_paru = intent.getStringExtra("hasil_diagnosis_kanker_paru_paru_tinggi");
        output_payudara = intent.getStringExtra("hasil_diagnosis_kanker_payudara_tinggi");
        output_prostat = intent.getStringExtra("hasil_diagnosis_kanker_prostat_tinggi");
        output_darah = intent.getStringExtra("hasil_diagnosis_kanker_darah_tinggi");
        output_usus = intent.getStringExtra("hasil_diagnosis_kanker_usus_tinggi");
        output_seviks = intent.getStringExtra("hasil_diagnosis_kanker_serviks_tinggi");


        if (output_hati != null){
            txt_hasil_lebih_60.setText(output_hati);
        }
        else if (output_paru_paru != null){
            txt_hasil_lebih_60.setText(output_paru_paru);
        }
        else if (output_payudara != null){
            txt_hasil_lebih_60.setText(output_payudara);
        }
        else if (output_prostat != null){
            txt_hasil_lebih_60.setText(output_prostat);
        }
        else if (output_darah != null){
            txt_hasil_lebih_60.setText(output_darah);
        }
        else if (output_usus != null){
            txt_hasil_lebih_60.setText(output_usus);
        }
        else if (output_seviks != null){
            txt_hasil_lebih_60.setText(output_seviks);
        }


        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location=" + lat + "%" + lng);
        stringBuilder.append("&radius=1000");
        stringBuilder.append("&type=hospital");
        stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

        String url = stringBuilder.toString();
        Object dataFetch[] = new Object[2];
        dataFetch[0] = mMap;
        dataFetch[1] = url;

        NearPlace nearPlace = new NearPlace();
        nearPlace.execute(dataFetch);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       getCurrentLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (REQ_CODE){

            case REQ_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   getCurrentLocation();
                }
        }
    }

    private void getCurrentLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQ_CODE);
            return;

        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(6000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        LocationCallback locationCallback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if (locationRequest == null){
                    Toast.makeText(getApplicationContext(), "Location not found", Toast.LENGTH_SHORT).show();

                    return;

                }

            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null);

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null){
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    LatLng latLng = new LatLng(lat,lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Posisi anda saat ini"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));

                }

            }
        });

    }

}