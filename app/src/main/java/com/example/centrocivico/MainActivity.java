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

		/* Muestra todos los sensores accesibles en el dispositivo, en el cuadro de texto de la
		 * actividad principal */
		for(Sensor sensor: listaSensores) {
			visualiza(sensor.getName());
		}
	}

	/**
	 * Implementa la funcionalidad del botón por medio del cual se accede al panel de detección de
	 * caídas.
	 * @param v
	 */
	public void botonCaidas(View v) {
		Intent i = new Intent(this, ActivityCaida.class);
		startActivity(i);
	}

	/**
	 * Implementa la funcionalidad del botón por medio del cual se accede al panel de detección del
	 * usuario frente al panel.
	 * @param v
	 */
	public void botonPanel(View v) {
		Intent i = new Intent(this, ActivityPanel.class);
		startActivity(i);
	}

	/**
	 * Muestra un texto en el cuadro de texto de la vista principal.
	 * @param texto El texto a mostrar en el cuadro
	 */
	public void visualiza(String texto) {
		TextView t = findViewById(R.id.cuadroTexto);
		t.setText((t.getText() == "") ? texto : t.getText() + "\n" + texto);
	}
}