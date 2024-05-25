package com.example.civicuc.ui.main.listado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.civicuc.MainActivity;
import com.example.civicuc.databinding.FragmentListadoBinding;
import com.example.civicuc.ui.main.caida.UbicacionCaida;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListadoFragment extends Fragment {

    private FragmentListadoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListadoBinding.inflate(inflater, container, false);

        ListView listadoCaidasPendientes = binding.fragmentListadoCaidasPendientes;
        ListView listadoCaidasAtendidas = binding.fragmentListadoCaidasAtendidas;

        HashMap<String, UbicacionCaida> caidasPendientes = new HashMap<String, UbicacionCaida>();
        HashMap<String, UbicacionCaida> caidasAtendidas = new HashMap<String, UbicacionCaida>();

        ArrayList<String> caidasPendientesStrings = new ArrayList<String>();
        ArrayList<String> caidasAtendidasStrings = new ArrayList<String>();

        // Se crea el adaptador que gestionará las entradas de la lista de caídas pendientes
        ArrayAdapter<String> adaptador_pendientes = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                                             android.R.layout.simple_list_item_1,
                                                                             caidasPendientesStrings);
        listadoCaidasPendientes.setAdapter(adaptador_pendientes);

        // Se crea el adaptador que gestionará las entradas de la lista de caídas atendidas
        ArrayAdapter<String> adaptador_atendidas = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                                            android.R.layout.simple_list_item_1,
                                                                            caidasAtendidasStrings);
        listadoCaidasAtendidas.setAdapter(adaptador_atendidas);

        // Obtiene todas las caídas registradas como pendientes y genera el listado en la vista
        ((MainActivity) getActivity()).getDatabase().child("pendientes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    if (d != null) {
                        caidasPendientes.put(d.getKey(), d.getValue(UbicacionCaida.class));
                        caidasPendientesStrings.add(d.getValue(UbicacionCaida.class).toString());
                        adaptador_pendientes.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        // Obtiene todas las caídas registradas como atendidas y genera el listado en la vista
        ((MainActivity) getActivity()).getDatabase().child("atendidas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    if (d != null) {
                        caidasAtendidas.put(d.getKey(), d.getValue(UbicacionCaida.class));
                        caidasAtendidasStrings.add(d.getValue(UbicacionCaida.class).toString());
                        adaptador_atendidas.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        // Configura el oyente del nodo de caídas pendientes
        ((MainActivity) getActivity()).getDatabase().child("pendientes").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                caidasPendientes.put(snapshot.getKey(), snapshot.getValue(UbicacionCaida.class));
                caidasPendientesStrings.add(snapshot.getKey());
                adaptador_pendientes.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }

        });

        // Configura el oyente del nodo de caídas atendidas
        ((MainActivity) getActivity()).getDatabase().child("atendidas").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                caidasAtendidas.put(snapshot.getKey(), snapshot.getValue(UbicacionCaida.class));
                caidasAtendidasStrings.add(snapshot.getKey());
                adaptador_atendidas.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }

        });

        // Configura el comportamiento del botón
        binding.fragmentListadoBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!caidasPendientes.values().isEmpty()) {
                    ArrayList<String> pendientesKeys = new ArrayList<String>(caidasPendientes.keySet());
                    String key = pendientesKeys.get(0);
                    UbicacionCaida val = caidasPendientes.get(key);

                    // Elimina la caída pendiente
                    caidasPendientes.remove(key);
                    caidasPendientesStrings.remove(pendientesKeys.size() - 1);
                    adaptador_pendientes.notifyDataSetChanged();
                    ((MainActivity) getActivity()).getDatabase().child("pendientes").child(key).removeValue();

                    // Registra la caída como atendida
                    caidasAtendidas.put(key, val);
                    caidasAtendidasStrings.add(key);
                    adaptador_atendidas.notifyDataSetChanged();
                    String nuevaClave = ((MainActivity) getActivity()).getDatabase().child("atendidas").push().getKey();
                    ((MainActivity) getActivity()).getDatabase().child("atendidas").child(nuevaClave).setValue(caidasAtendidas.get(key));
                }
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
