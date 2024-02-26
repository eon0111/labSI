package com.example.centrocivico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityPanel extends AppCompatActivity implements SensorEventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panel);

		/*
		 * Configuración del sensor de proximidad.
		 */
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

		if(sensorProximidad != null) {
			sensorManager.registerListener((SensorEventListener) this, sensorProximidad,
					SensorManager.SENSOR_DELAY_UI);
		}

		// Initialize and assign variable
		BottomNavigationView bottomNavigationView = findViewById(R.id.panelBarraNavegacion);

		// Set Home selected
		bottomNavigationView.setSelectedItemId(R.id.activityPanel);

		bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				if (item.getTitle().equals("Caídas")) {
					startActivity(new Intent(getApplicationContext(), ActivityCaida.class));
					//overridePendingTransition(0,0);
					return true;
				} else if (item.getTitle().equals("Inicio")) {
					startActivity(new Intent(getApplicationContext(), ActivityInicio.class));
					//overridePendingTransition(0,0);
					return true;
				} else if (item.getTitle().equals("Panel")) {
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Gestiona las actualizaciones del valor de proximidad en la interfaz, cada vez que el sensor
	 * de proximidad notifica una variación en las lecturas de dicho valor.
	 *
	 * @param event El evento empleado en las comunicaciones con el sensor de proximidad
	 */
	public void onSensorChanged(SensorEvent event) {
		if(event.values[0] == 0) {
			visualiza("Sala 1: juego libre Jugger\n" +
					  "Sala 2: torneo de Mario Kart\n" +
					  "Sala 3: clase de inglés\n" +
					  "Sala 4: ensayo de coro juvenil");
		} else{
			visualiza("");
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
