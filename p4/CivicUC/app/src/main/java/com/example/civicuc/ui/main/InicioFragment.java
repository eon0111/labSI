package com.example.civicuc.ui.main;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.civicuc.R;
import com.example.civicuc.databinding.FragmentInicioBinding;
import com.example.civicuc.ui.main.caida.CaidaFragment;

import java.util.List;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);

        /* Muestra todos los sensores accesibles en el dispositivo, en el cuadro de texto de la
         * actividad principal */
        SensorManager sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensores = new StringBuilder();

        for(Sensor sensor: listaSensores) sensores.append(sensor.getName()).append("\n");

        binding.cuadroTexto.setText(sensores.toString());

        /* Configura el comportamiento de los botones para que redirijan a la interfaz que corresponda */
        binding.botonMonitorizarCaidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_inicio, new CaidaFragment());
                fragmentTransaction.commit();
            }
        });

        binding.botonMonitorizarPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_inicio, new ActividadesFragment());
                fragmentTransaction.commit();
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
