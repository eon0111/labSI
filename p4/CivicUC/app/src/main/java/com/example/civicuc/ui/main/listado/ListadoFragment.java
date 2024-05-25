package com.example.civicuc.ui.main.listado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.civicuc.MainActivity;
import com.example.civicuc.databinding.FragmentListadoBinding;
import com.example.civicuc.ui.main.caida.UbicacionCaida;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListadoFragment extends Fragment {

    private FragmentListadoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListadoBinding.inflate(inflater, container, false);

        ListView listadoCaidasPendientes = binding.fragmentListadoCaidasPendientes;
        ListView listadoCaidasAtendidas = binding.fragmentListadoCaidasAtendidas;

        ArrayList<String> caidasAtendidas = new ArrayList<>();
        ArrayList<String> caidasPendientes = new ArrayList<>();

        // Se crea el adaptador que gestionará las entradas de la lista de caídas pendientes
        ArrayAdapter<String> adaptador_pendientes = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                                             android.R.layout.simple_list_item_1,
                                                                             caidasPendientes);
        listadoCaidasPendientes.setAdapter(adaptador_pendientes);

        // Se crea el adaptador que gestionará las entradas de la lista de caídas atendidas
        ArrayAdapter<String> adaptador_atendidas = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                                            android.R.layout.simple_list_item_1,
                                                                            caidasPendientes);
        listadoCaidasAtendidas.setAdapter(adaptador_atendidas);

        /* Obtiene todas las caídas registradas como pendientes y genera el listado en la vista */
        ((MainActivity) getActivity()).getDatabase().child("pendientes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    if (d != null) {
                        caidasPendientes.add(d.getValue(UbicacionCaida.class).toString());
                        adaptador_pendientes.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /* Obtiene todas las caídas registradas como atendidas y genera el listado en la vista */
        ((MainActivity) getActivity()).getDatabase().child("atendidas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    if (d != null) {
                        caidasAtendidas.add(d.getValue(UbicacionCaida.class).toString());
                        adaptador_atendidas.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /* Configura el comportamiento del botón */
        binding.fragmentListadoBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listadoCaidasPendientes.getSelectedItem().toString();

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
