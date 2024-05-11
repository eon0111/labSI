package com.example.civicuc.ui.main.listado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.civicuc.MainActivity;
import com.example.civicuc.databinding.FragmentListadoBinding;
import com.example.civicuc.ui.main.caida.UbicacionCaida;

import java.util.ArrayList;

public class ListadoFragment extends Fragment {

    private FragmentListadoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListadoBinding.inflate(inflater, container, false);

        /* Genera una lista con todas las caídas sin atender registradas en la base de datos */
        // TODO:

        ListView listadoCaidasPendientes = binding.fragmentListadoCaidasPendientes;
        ListView listadoCaidasAtendidas = binding.fragmentListadoCaidasAtendidas;
        ArrayList<UbicacionCaida> caidasBD = ((MainActivity)getActivity()).getDatabase();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /* TODO:
    private void registraOyenteNodos() {
        ((MainActivity)getActivity()).getDatabase().getRef().child()
    }
    */

}