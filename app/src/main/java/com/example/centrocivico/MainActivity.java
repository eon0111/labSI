package com.example.centrocivico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

		for(Sensor sensor: listaSensores) {
			visualiza(sensor.getName());
		}
	}

	public void botonCaidas(View v) {
		Intent i = new Intent(this, ActivityCaida.class);
		startActivity(i);
	}

	public void botonPanel(View v) {
		Intent i = new Intent(this, ActivityPanel.class);
		startActivity(i);
	}

	public void visualiza(String texto) {
		TextView t = findViewById(R.id.cuadroTexto);
		t.setText((t.getText() == "") ? texto : t.getText() + "\n" + texto);
	}
}