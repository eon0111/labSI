package com.example.civicuc.ui.acceso.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.civicuc.LoginActivity;
import com.example.civicuc.databinding.FragmentCaidaBinding;
import com.example.civicuc.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        binding.fragmentLoginBotonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticarUsuario(binding.fragmentLoginInputEmail.getText().toString(),
                                  binding.fragmentLoginInputContrasenha.getText().toString());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void autenticarUsuario(String nombre, String contrasenha) {
        ((LoginActivity)getActivity()).autenticarUsuario(nombre, contrasenha);
    }
}