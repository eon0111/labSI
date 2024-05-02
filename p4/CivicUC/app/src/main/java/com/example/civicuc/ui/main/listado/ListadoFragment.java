package com.example.civicuc.ui.main.listado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.civicuc.databinding.FragmentListadoBinding;

public class ListadoFragment extends Fragment {

    private FragmentListadoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListadoBinding.inflate(inflater, container, false);

        /* Genera una lista con todas las caídas sin atender registradas en la bas de datos */
        // TODO:
        /*
        ListView listadoCaidas = binding.fragmentListadoCaidas;
        ArrayList<UbicacionCaida> caidasBD = ((MainActivity)getActivity()).getmDatabase().getDatabase().
        */

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