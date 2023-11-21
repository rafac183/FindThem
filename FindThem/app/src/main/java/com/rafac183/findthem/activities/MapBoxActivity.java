package com.rafac183.findthem.activities;

import static com.mapbox.maps.plugin.gestures.GesturesUtils.getGestures;
import static com.mapbox.maps.plugin.locationcomponent.LocationComponentUtils.getLocationComponent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mapbox.android.gestures.MoveGestureDetector;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.extension.style.layers.properties.generated.TextAnchor;
import com.mapbox.maps.plugin.LocationPuck2D;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.OnMoveListener;
import com.mapbox.maps.plugin.locationcomponent.LocationComponentPlugin;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityMapBoxBinding;
import com.rafac183.findthem.model.BitmapUtils;
import com.rafac183.findthem.model.ShareLocation;

import java.io.Console;
import java.lang.ref.WeakReference;

public class MapBoxActivity extends AppCompatActivity {

    private ActivityMapBoxBinding binding;
    private MapView mapView;
    private FloatingActionButton floatingActionButton;
    private Point point;
    private DatabaseReference reference = null;
    private ShareLocation location;
    private MaterialButton shareLocation, exit;
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean o) {
            if (o) {
                Toast.makeText(MapBoxActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MapBoxActivity.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    });
    private final OnIndicatorBearingChangedListener onIndicatorBearingChangedListener = new OnIndicatorBearingChangedListener() {
        @Override
        public void onIndicatorBearingChanged(double v) {
            mapView.getMapboxMap().setCamera(new CameraOptions.Builder().bearing(v).build());
        }
    };
    private final OnIndicatorPositionChangedListener onIndicatorPositionChangedListener = new OnIndicatorPositionChangedListener() {
        @Override
        public void onIndicatorPositionChanged(@NonNull Point point) {
            mapView.getMapboxMap().setCamera(new CameraOptions.Builder().center(point).zoom(17.0).build());
            getGestures(mapView).setFocalPoint(mapView.getMapboxMap().pixelForCoordinate(point));
            MapBoxActivity.this.point = point;
        }
    };
    private OnMoveListener onMoveListener = new OnMoveListener() {
        @Override
        public void onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector) {
            getLocationComponent(mapView).removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener);
            getLocationComponent(mapView).removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener);
            getGestures(mapView).removeOnMoveListener(onMoveListener);
            floatingActionButton.show();
        }
        @Override
        public boolean onMove(@NonNull MoveGestureDetector moveGestureDetector) {
            return false;
        }
        @Override
        public void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector) {}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseApp.initializeApp(this);

        mapView = binding.mapView;
        floatingActionButton = binding.myLocation;
        shareLocation = binding.shareLocation;
        exit = binding.exit;

        location = new ShareLocation();

        /*---------------Methods------------------*/
        Btns();

        /*------------MapBox------------*/
        if (ActivityCompat.checkSelfPermission(MapBoxActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            activityResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        floatingActionButton.hide();
        mapView.getMapboxMap().loadStyleUri(Style.SATELLITE_STREETS, style -> {
            mapView.getMapboxMap().setCamera(new CameraOptions.Builder().zoom(17.0).build());
            LocationComponentPlugin locationComponentPlugin = getLocationComponent(mapView);
            locationComponentPlugin.setEnabled(true);
            LocationPuck2D locationPuck2D = new LocationPuck2D();
            locationPuck2D.setBearingImage(AppCompatResources.getDrawable(MapBoxActivity.this, R.drawable.my_location_icon));
            locationComponentPlugin.setLocationPuck(locationPuck2D);
            locationComponentPlugin.addOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener);
            locationComponentPlugin.addOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener);
            getGestures(mapView).addOnMoveListener(onMoveListener);

            floatingActionButton.setOnClickListener(v -> {
                locationComponentPlugin.addOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener);
                locationComponentPlugin.addOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener);
                getGestures(mapView).addOnMoveListener(onMoveListener);
                floatingActionButton.hide();
            });
            FireBaseAsync fireBaseAsync = new FireBaseAsync(MapBoxActivity.this);
            fireBaseAsync.execute();

            shareLocation.setOnClickListener(v -> {
                if (reference == null){
                    Toast.makeText(MapBoxActivity.this, "Sharing location...", Toast.LENGTH_SHORT).show();
                    reference = FirebaseDatabase.getInstance().getReference().child("sharedLocations").push();
                    location = new ShareLocation();
                    location.setId(reference.getKey());
                    location.setName("User Name");
                    location.setLongitude(point.longitude());
                    location.setLatitude(point.latitude());
                    reference.setValue(location);
                    shareLocation.setText("Stop Sharing");
                } else {
                    Toast.makeText(MapBoxActivity.this, "Stopped sharing location", Toast.LENGTH_SHORT).show();
                    reference.removeValue();
                    reference = null;
                    shareLocation.setText("Share Location");
                }
            });
        });
    }

    private class FireBaseAsync extends AsyncTask<Void, Void, Boolean>{
        PointAnnotationManager pointAnnotationManager;
        private WeakReference<MapBoxActivity> activityRef;

        FireBaseAsync(MapBoxActivity activity) {
            activityRef = new WeakReference<>(activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
            pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, new AnnotationConfig());
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                MapBoxActivity activity = activityRef.get();
                if (activity != null) {
                    FirebaseDatabase.getInstance().getReference().child("sharedLocations").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            pointAnnotationManager.deleteAll();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                snapshot.getChildren().forEach(dataSnapshot -> {
                                    ShareLocation location1 = dataSnapshot.getValue(ShareLocation.class);
                                    if (location1 != null && !location1.getId().equals(activity.location.getId())){
                                        Glide.with(activity).asBitmap().load("https://i.ibb.co/F4B4GZQ/location.png")
                                                .into(new SimpleTarget<Bitmap>() {
                                                    @Override
                                                    public void onResourceReady(@NonNull Bitmap oldResource, @Nullable Transition<? super Bitmap> transition) {
                                                        int newWidth = 100;
                                                        int newHeight = 100;
                                                        Bitmap scaledResource = Bitmap.createScaledBitmap(oldResource, newWidth, newHeight, false);
                                                        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                                                                .withTextAnchor(TextAnchor.CENTER)
                                                                .withIconImage(BitmapUtils.getBitmapFromDrawable(getResources(), new BitmapDrawable(getResources(), scaledResource)))
                                                                .withPoint(Point.fromLngLat(location1.getLongitude(), location1.getLatitude()));
                                                        pointAnnotationManager.create(pointAnnotationOptions);
                                                    }
                                                });
                                    }
                                });
                            }
                            pointAnnotationManager.addClickListener(pointAnnotation -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    snapshot.getChildren().forEach(dataSnapshot -> {
                                        ShareLocation location1 = dataSnapshot.getValue(ShareLocation.class);
                                        if (location1 != null && pointAnnotation.getPoint().longitude() == location1.getLongitude() && pointAnnotation.getPoint().latitude() == location1.getLatitude()){
                                            Toast.makeText(activity, "Clicked" + location1.getId() + " Marker", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                return true;
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
                return true;
            }catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Toast.makeText(activityRef.get(), "Locations Loaded", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
            if(aBoolean){
                Toast.makeText(activityRef.get(), "Locations not Loaded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Btns(){
        exit.setOnClickListener(v -> {
            startActivity(new Intent(MapBoxActivity.this, NavigationActivity.class));
            finish();
        });
    }
}