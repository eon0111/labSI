package com.example.civicuc.ui.inicio;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.civicuc.databinding.FragmentInicioBinding;
import com.example.civicuc.ui.caida.CaidaFragment;

import java.util.List;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioViewModel inicioViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);

        /* Muestra todos los sensores accesibles en el dispositivo, en el cuadro de texto de la
         * actividad principal */
        SensorManager sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensores = new StringBuilder();

        for(Sensor sensor: listaSensores) {
            sensores.append(sensor.getName()).append("\n");
        }

        binding.cuadroTexto.setText(sensores.toString());

        /* TODO: Configura la funcionalidad de los botones */
        binding.botonMonitorizarCaidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO:
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
