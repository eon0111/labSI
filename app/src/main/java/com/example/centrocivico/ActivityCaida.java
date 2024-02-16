package com.example.centrocivico;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityCaida extends AppCompatActivity implements SensorEventListener {
	private static final int ACCEL_DATA_X = 0;
	private static final int ACCEL_DATA_Y = 1;
	private static final int ACCEL_DATA_Z = 2;
	private static final double ACCEL_TRIGGER = 1.5;	// TODO: hacer pruebas para configurar un buen valor umbral para la aceleración

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_caidas);

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		if(acelerometro != null) {
			sensorManager.registerListener((SensorEventListener) this, acelerometro,
					SensorManager.SENSOR_DELAY_UI);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		TextView texto_valor_sensor = (TextView) findViewById(R.id.caidasValorSensores);
		TextView texto_estado_usuario = (TextView) findViewById(R.id.caidasEstadoUsuario);

		float accel_x = event.values[ACCEL_DATA_X];
		float accel_y = event.values[ACCEL_DATA_Y];
		float accel_z = event.values[ACCEL_DATA_Z];

		texto_valor_sensor.setText("X: " + accel_x + "Y: " + accel_y + "Z: " + accel_z);

		if (accel_x > ACCEL_TRIGGER || accel_y > ACCEL_TRIGGER ||accel_z > ACCEL_TRIGGER) {
			texto_estado_usuario.setText("CAÍDA");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
