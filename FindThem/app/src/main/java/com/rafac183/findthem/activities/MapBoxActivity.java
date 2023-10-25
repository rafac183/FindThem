package com.rafac183.findthem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.rafac183.findthem.databinding.ActivityMapBoxBinding;

public class MapBoxActivity extends AppCompatActivity {

    private ActivityMapBoxBinding binding;
    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView = binding.mapView;
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
    }
}