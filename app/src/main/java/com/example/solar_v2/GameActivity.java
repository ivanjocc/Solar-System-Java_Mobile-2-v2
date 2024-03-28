package com.example.solar_v2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends Activity {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private AlienSolarSystem alienSolarSystemView;

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];

            alienSolarSystemView.setNaveX(alienSolarSystemView.getNaveX() - x * 5);
            alienSolarSystemView.setNaveY(alienSolarSystemView.getNaveY() + y * 5);
            alienSolarSystemView.invalidate();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // nothing here
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        alienSolarSystemView = new AlienSolarSystem(this);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT);
        alienSolarSystemView.setLayoutParams(params);

        mainLayout.addView(alienSolarSystemView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}
