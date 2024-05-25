package com.example.civicuc.ui.main.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.Date;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentChatBinding.inflate(inflater, container, false);

        // Obtiene todos los mensajes del chat
        /*
        ((MainActivity) getActivity()).getDatabase().child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    if (d != null) {
                        caidasPendientes.put(d.getKey(), d.getValue(UbicacionCaida.class));
                        caidasPendientesStrings.add(d.getValue(UbicacionCaida.class).toString());
                        adaptador.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        */

        binding.fragmentChatBotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.fragmentChatInputMensaje.equals("")) {
                    Mensaje m = new Mensaje(((MainActivity) getActivity()).getEmailUsuario(),
                                            binding.fragmentChatInputMensaje.getText().toString(),
                                            Date.from(Instant.now()));
                    String key = ((MainActivity) getActivity()).getDatabase().child("mensajes").push().getKey();
                    ((MainActivity) getActivity()).getDatabase().child("mensajes").child(key).setValue(m);
                    binding.fragmentChatInputMensaje.setText("");
                }
            }
        });

        return binding.getRoot();
    }

    private void registraOyenteMensajes(String claveGrupo) {
        DatabaseReference mDatabase = ((MainActivity) getActivity()).getDatabase();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                try {
                    adaptador.insert(dataSnapshot.getValue(Mensaje.class),0);
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
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

        mDatabase.getRef().child("chat").addChildEventListener(childEventListener);
        adaptador = new ChatAdapter(getActivity().getApplicationContext(), new ArrayList<Mensaje>());
        binding.fragmentChatHistoricoMensajes.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
