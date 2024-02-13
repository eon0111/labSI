package com.example.centrocivico;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class AcitivityPanel extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensor != null) {
            sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0] ==0) {
            visualiza("cerca");
        } else{
            visualiza("lejos");
        }
    }

    public void visualiza(String texto) {
        TextView t = findViewById(R.id.cuadroTexto);
        t.setText(t.getText() + "\n" + texto);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
