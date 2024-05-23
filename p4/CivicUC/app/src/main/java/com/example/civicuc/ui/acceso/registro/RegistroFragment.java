package com.example.civicuc.ui.acceso.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.civicuc.LoginActivity;
import com.example.civicuc.MainActivity;
import com.example.civicuc.databinding.FragmentRegistroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroFragment extends Fragment {

    private FragmentRegistroBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        binding.fragmentRegistroBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.fragmentRegistroContrasenha
                           .getText().toString()
                           .equals(binding.fragmentRegistroRepiteContrasenha
                                          .getText().toString())) {
                    binding.fragmentRegistroErrorContrasenha.setVisibility(View.INVISIBLE);

                    /* Se da de alta al usuario en el servicio de autenticación de Firebase y arranca
                     * la actividad principal */
                    ((LoginActivity) getActivity()).nuevoUsuario(
                            binding.fragmentRegistroEmail.getText().toString(),
                            binding.fragmentRegistroContrasenha.getText().toString());
                } else {
                    binding.fragmentRegistroErrorContrasenha.setVisibility(View.VISIBLE);
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
