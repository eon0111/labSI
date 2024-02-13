package com.example.centrocivico;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener { //, SensorEventListener {

	/* Botones */
	private Button botonMonitorizarCaidas;
	private Button botonMonitorizarPanel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		botonMonitorizarCaidas = (Button) findViewById(R.id.botonMonitorizarCaidas);
		botonMonitorizarPanel = (Button) findViewById(R.id.botonMonitorizarPanel);

		botonMonitorizarCaidas.setOnClickListener(this);
		botonMonitorizarPanel.setOnClickListener(this);

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

		for(Sensor sensor: listaSensores) {
			visualiza(sensor.getName());
		}
	}

	@Override
	public void onClick(View v) {
		if (v == botonMonitorizarPanel) {
			setContentView(R.layout.activity_panel);
		} else if (v == botonMonitorizarCaidas) {
			setContentView(R.layout.activity_caidas);
		}
	}

	public void visualiza(String texto) {
		TextView t = findViewById(R.id.cuadroTexto);
		t.setText((t.getText() == "") ? texto : t.getText() + "\n" + texto);
	}
}