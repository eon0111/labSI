package com.example.civicuc.ui.main.caida;

import static android.content.Context.SENSOR_SERVICE;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.civicuc.MainActivity;
import com.example.civicuc.R;
import com.example.civicuc.databinding.FragmentCaidaBinding;
import com.google.firebase.auth.FirebaseAuth;

public class CaidaFragment extends Fragment implements SensorEventListener, LocationListener {

    private boolean caido;

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

    private FragmentCaidaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCaidaBinding.inflate(inflater, container, false);

        /*
         * Configuración del sensor de aceleración.
         */
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (acelerometro != null) {
            sensorManager.registerListener((SensorEventListener) this, acelerometro,
                    SensorManager.SENSOR_DELAY_UI);
        }

        /*
         * Configuración del GPS.
         */
        LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        /* Comprobación de permisos */
        if (ActivityCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.getContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        /* Actualización de la posición del usuario en la interfaz con su posición inicial */
        loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (loc != null) {
            binding.caidasLatitudUsuario.setText(Double.toString(loc.getLatitude()));
            binding.caidasLongitudUsuario.setText(Double.toString(loc.getLongitude()));
        }

        /* Configuración del GPS para que notifique las actualizaciones de la posición cada 5 ms o
         * cuando el terminal se desplaza 1 metro */
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, this);

        /*
         * Creación del canal de comunicación que permitirá al aplicativo mostrar notificaciones en
         * el área de notificación de la interfaz del dispositivo.
         */
        crearCanalComunicacion();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Gestiona las actualizaciones del valor de aceleración en cada uno de los tres ejes (X, Y, Z)
     * en la interfaz, cada vez que el sensor de aceleración notifica una variación en alguna de las
     * lecturas.
     *
     * @param event El evento empleado en las comunicaciones con el sensor de aceleración
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (binding != null) {
            float accel_x = event.values[ACCEL_DATA_X];
            float accel_y = event.values[ACCEL_DATA_Y];
            float accel_z = event.values[ACCEL_DATA_Z];

            binding.caidasValorSensorX.setText(String.format("%.3f", accel_x));
            binding.caidasValorSensorY.setText(String.format("%.3f", accel_y));
            binding.caidasValorSensorZ.setText(String.format("%.3f", accel_z));

            if (!caido && (accel_x > ACCEL_TRIGGER || accel_y > ACCEL_TRIGGER || accel_z > ACCEL_TRIGGER)) {
                caido = true;
                binding.caidasEstadoUsuario.setText("CAÍDA");
                crearNotificacion();

                /* Registra la caída en la base de datos */
                ((MainActivity)getActivity()).writeNewUbicacion(binding.caidasLatitudUsuario
                                                                .getText().toString(),
                                                                binding.caidasLongitudUsuario
                                                                .getText().toString());
            }
        }
    }

    /**
     * Gestiona las actualizaciones de la posición del usuario en la interfaz cada vez que el GPS
     * notifica una actualización.
     *
     * @param location La localización actualizada del usuario
     */
    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (binding != null) {
            binding.caidasLatitudUsuario.setText(Double.toString(location.getLatitude()));
            binding.caidasLongitudUsuario.setText(Double.toString(location.getLongitude()));
        }
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

            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Muestra una notificación en la barra de notificación de la interfaz del dispositivo.
     *
     */
    private void crearNotificacion() {
        Intent intent = new Intent(this.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        /* Construcción de la notificación (se muestra la ubicación del usuario) */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getContext(), "96")
                .setSmallIcon(R.drawable.ic_caida)
                .setContentTitle("Usuario accidentado")
                .setContentText("Localización: " + loc.getLatitude() + " " + loc.getLongitude())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.getContext());

        /* Comprobación del permiso para mostrar notificaciones */
        if (ActivityCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        /* Muestra la notificación */
        notificationManager.notify(96, builder.build());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
