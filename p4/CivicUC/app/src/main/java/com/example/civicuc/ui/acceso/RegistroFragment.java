package com.example.civicuc.ui.acceso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.civicuc.databinding.FragmentRegistroBinding;

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
