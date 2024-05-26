package com.example.civicuc.ui.main.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.civicuc.MainActivity;
import com.example.civicuc.R;
import com.example.civicuc.databinding.FragmentChatBinding;
import com.example.civicuc.ui.main.caida.UbicacionCaida;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter adaptador;

    private ArrayList<String> historicoMensajes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentChatBinding.inflate(inflater, container, false);

        historicoMensajes = new ArrayList<String>();

        ArrayAdapter<String> adaptador_mensajes = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                                           android.R.layout.simple_list_item_1,
                                                                           historicoMensajes);
        binding.fragmentChatHistoricoMensajes.setAdapter(adaptador_mensajes);

        // Obtiene todos los mensajes del chat
        ((MainActivity) getActivity()).getDatabase().child("mensajes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    if (d != null) {
                        historicoMensajes.add(d.getValue(Mensaje.class).toString());
                        adaptador_mensajes.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        // Configura el oyente del nodo de mensajes
        ((MainActivity) getActivity()).getDatabase().child("mensajes").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                historicoMensajes.add(snapshot.getValue(Mensaje.class).toString());
                adaptador_mensajes.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        // Configura el comportamiento del botón de envío
        binding.fragmentChatBotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.fragmentChatInputMensaje.equals("")) {
                    Mensaje m = new Mensaje(((MainActivity) getActivity()).getEmailUsuario(),
                                            binding.fragmentChatInputMensaje.getText().toString(),
                                            LocalDateTime.now().toString());
                    String key = ((MainActivity) getActivity()).getDatabase().child("mensajes").push().getKey();
                    ((MainActivity) getActivity()).getDatabase().child("mensajes").child(key).setValue(m);
                    binding.fragmentChatInputMensaje.setText("");
                }
            }
        });

        // registraOyenteMensajes();

        return binding.getRoot();
    }

    private void registraOyenteMensajes() {
        DatabaseReference mDatabase = ((MainActivity) getActivity()).getDatabase();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // try {
                    adaptador.insert(dataSnapshot.getValue(Mensaje.class), 0);
                // } catch (Exception e) {
                //     Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                // }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        };

        mDatabase.getRef().child("mensajes").addChildEventListener(childEventListener);
        adaptador = new ChatAdapter(getActivity().getApplicationContext(), new ArrayList<Mensaje>());
        binding.fragmentChatHistoricoMensajes.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
