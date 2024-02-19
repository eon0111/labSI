package com.example.centrocivico;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActivityPanel extends AppCompatActivity implements SensorEventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panel);
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

		if(sensorProximidad != null) {
			sensorManager.registerListener((SensorEventListener) this, sensorProximidad,
					SensorManager.SENSOR_DELAY_UI);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.values[0] == 0) {
			visualiza("cerca");
		} else{
			visualiza("lejos");
		}
	}

	public void visualiza(String val) {
		TextView t = findViewById(R.id.valorSensorProximidad);
		t.setText(val);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
