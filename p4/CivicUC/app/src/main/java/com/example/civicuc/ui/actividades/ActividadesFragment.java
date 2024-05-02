package com.example.civicuc.ui.actividades;

import static android.content.Context.*;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.civicuc.databinding.FragmentActividadesBinding;

public class ActividadesFragment extends Fragment implements SensorEventListener {

    private FragmentActividadesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActividadesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ActividadesViewModel.class);

        binding = FragmentActividadesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
         * Configuración del sensor de proximidad.
         */
        SensorManager sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensorProximidad != null) {
            sensorManager.registerListener((SensorEventListener) this, sensorProximidad,
                    SensorManager.SENSOR_DELAY_UI);
        }

        // final TextView textView = binding.textActividades;
        // dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
        binding.valorSensorProximidad.setText(val);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}