package com.example.centrocivico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ActivityInicio extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);

		/* Muestra todos los sensores accesibles en el dispositivo, en el cuadro de texto de la
		 * actividad principal */
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

		for(Sensor sensor: listaSensores) {
			visualiza(sensor.getName());
		}

		// Initialize and assign variable
		BottomNavigationView bottomNavigationView = findViewById(R.id.inicioBarraNavegacion);

		// Set Home selected
		bottomNavigationView.setSelectedItemId(R.id.activityInicio);

		bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				if (item.getTitle().equals("Caídas")) {
					startActivity(new Intent(getApplicationContext(), ActivityCaida.class));
					overridePendingTransition(0,0);
					return true;
				} else if (item.getTitle().equals("Inicio")) {
					return true;
				} else if (item.getTitle().equals("Panel")) {
					startActivity(new Intent(getApplicationContext(), ActivityPanel.class));
					overridePendingTransition(0,0);
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Implementa la funcionalidad del botón por medio del cual se accede al panel de detección de
	 * caídas.
	 *
	 */
	public void botonCaidas(View v) {
		Intent i = new Intent(this, ActivityCaida.class);
		startActivity(i);
	}

	/**
	 * Implementa la funcionalidad del botón por medio del cual se accede al panel de detección del
	 * usuario frente al panel.
	 *
	 */
	public void botonPanel(View v) {
		Intent i = new Intent(this, ActivityPanel.class);
		startActivity(i);
	}

	/**
	 * Muestra un texto en el cuadro de texto de la vista principal.
	 *
	 * @param texto El texto a mostrar en el cuadro
	 */
	public void visualiza(String texto) {
		TextView t = findViewById(R.id.cuadroTexto);
		t.setText((t.getText() == "") ? texto : t.getText() + "\n" + texto);
	}
}