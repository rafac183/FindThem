package com.rafac183.findthem.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class ProximityService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private PowerManager powerManager;
    private WakeLock wakeLock;

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializa el Sensor de Proximidad
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Inicializa el WakeLock con un nombre único
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "myapp:proximitylocktag");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Registra el listener del sensor de proximidad
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Detiene el listener del sensor de proximidad y libera el WakeLock
        sensorManager.unregisterListener(this);
        if (wakeLock.isHeld()) {
            wakeLock.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Maneja el cambio en el sensor de proximidad
        float distance = event.values[0];
        if (distance == 0) {
            // Si el objeto está cerca, activa el WakeLock
            wakeLock.acquire(10*60*1000L /*10 minutes*/);
        } else {
            // Si no hay objetos cerca, libera el WakeLock
            if (wakeLock.isHeld()) {
                wakeLock.release();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No es necesario implementar esta función, pero debe estar presente
    }
}

