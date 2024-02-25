package com.example.centrocivico;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityCaida extends AppCompatActivity implements SensorEventListener, LocationListener {

	/**
	 * Constantes para acceder al array de datos del sensor de aceleración.
	 */
	private static final int ACCEL_DATA_X = 0;
	private static final int ACCEL_DATA_Y = 1;
	private static final int ACCEL_DATA_Z = 2;

	/**
	 * Las lecturas de un valor de aceleración superiores al valor de la cte. ACCEL_TRIGGER en
	 * cualquiera de los tres ejes (X, Y, Z) son interpretadas como una caída del usuario.
	 */
	private static final double ACCEL_TRIGGER = 20;

	/**
	 * La localización del usuario.
	 */
	private Location loc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_caidas);

		/*
		 * Configuración del sensor de aceleración.
		 */
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		if (acelerometro != null) {
			sensorManager.registerListener((SensorEventListener) this, acelerometro,
					SensorManager.SENSOR_DELAY_UI);
		}

		/*
		 * Configuración del GPS.
		 */
		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		/* Comprobación de permisos */
		if (ActivityCompat.checkSelfPermission(this,
				android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
				ActivityCompat.checkSelfPermission(this,
						android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}

		/* Actualización de la posición del usuario en la interfaz con su posición inicial */
		loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		TextView textoLatitud = findViewById(R.id.caidasLatitudUsuario);
		TextView textoLongitud = findViewById(R.id.caidasLongitudUsuario);

		if (loc != null) {
			textoLatitud.setText(Double.toString(loc.getLatitude()));
			textoLongitud.setText(Double.toString(loc.getLongitude()));
		}

		/* Configuración del GPS para que notifique las actualizaciones de la posición cada 5 ms o
		 * cuando el terminal se desplaza 1 metro */
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, this);

		/*
		 * Creación del canal de comunicación que permitirá al aplicativo mostrar notificaciones en
		 * el área de notificación de la interfaz del dispositivo.
		 */
		crearCanalComunicacion();

		// Initialize and assign variable
		BottomNavigationView bottomNavigationView = findViewById(R.id.caidasBarraNavegacion);

		// Set Home selected
		bottomNavigationView.setSelectedItemId(R.id.activityCaidas);

		bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				if (item.getTitle().equals("Caídas")) {
					return true;
				} else if (item.getTitle().equals("Inicio")) {
					startActivity(new Intent(getApplicationContext(), ActivityInicio.class));
					//overridePendingTransition(0,0);
					return true;
				} else if (item.getTitle().equals("Panel")) {
					startActivity(new Intent(getApplicationContext(), ActivityPanel.class));
					//overridePendingTransition(0,0);
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Gestiona las actualizaciones del valor de aceleración en cada uno de los tres ejes (X, Y, Z)
	 * en la interfaz, cada vez que el sensor de aceleración notifica una variación en alguna de las
	 * lecturas.
	 *
	 * @param event El evento empleado en las comunicaciones con el sensor de aceleración
	 */
	@SuppressLint({"SetTextI18n", "DefaultLocale"})
	@Override
	public void onSensorChanged(SensorEvent event) {
		TextView txt_val_accel_x = (TextView) findViewById(R.id.caidasValorSensorX);
		TextView txt_val_accel_y = (TextView) findViewById(R.id.caidasValorSensorY);
		TextView txt_val_accel_z = (TextView) findViewById(R.id.caidasValorSensorZ);
		TextView texto_estado_usuario = (TextView) findViewById(R.id.caidasEstadoUsuario);

		float accel_x = event.values[ACCEL_DATA_X];
		float accel_y = event.values[ACCEL_DATA_Y];
		float accel_z = event.values[ACCEL_DATA_Z];

		txt_val_accel_x.setText(String.format("%.3f", accel_x));
		txt_val_accel_y.setText(String.format("%.3f", accel_y));
		txt_val_accel_z.setText(String.format("%.3f", accel_z));

		if (accel_x > ACCEL_TRIGGER || accel_y > ACCEL_TRIGGER || accel_z > ACCEL_TRIGGER) {
			texto_estado_usuario.setText("CAÍDA");
			crearNotificacion();
		}
	}

	/**
	 * Gestiona las actualizaciones de la posición del usuario en la interfaz cada vez que el GPS
	 * notifica una actualización.
	 *
	 * @param location La localización actualizada del usuario
	 */
	@SuppressLint("SetTextI18n")
	@Override
	public void onLocationChanged(@NonNull Location location) {
		TextView textoLatitud = findViewById(R.id.caidasLatitudUsuario);
		TextView textoLongitud = findViewById(R.id.caidasLongitudUsuario);

		textoLatitud.setText(Double.toString(location.getLatitude()));
		textoLongitud.setText(Double.toString(location.getLongitude()));
	}

	/**
	 * Crea un canal de comunicaciones para que la aplicación pueda mostrar notificaciones en el
	 * área de notificación del dispositivo.
	 *
	 */
	public void crearCanalComunicacion() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "Notificacion caidas";
			String description = "El canal empleado en la notificacion de las caidas del usuario";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel("96", name, importance);
			channel.setDescription(description);

			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
	}

	/**
	 * Muestra una notificación en la barra de notificación de la interfaz del dispositivo.
	 *
	 */
	private void crearNotificacion() {
		Intent intent = new Intent(this, ActivityInicio.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

		/* Construcción de la notificación (se muestra la ubicación del usuario) */
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "96")
				.setSmallIcon(R.drawable.ic_notificacion_caida)
				.setContentTitle("Usuario accidentado")
				.setContentText("Localización: " + loc.getLatitude() + " " + loc.getLongitude())
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setContentIntent(pendingIntent)
				.setAutoCancel(true);
		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

		/* Comprobación del permiso para mostrar notificaciones */
		if (ActivityCompat.checkSelfPermission(this,
				android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
			return;
		}

		/* Muestra la notificación */
		notificationManager.notify(96, builder.build());
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
