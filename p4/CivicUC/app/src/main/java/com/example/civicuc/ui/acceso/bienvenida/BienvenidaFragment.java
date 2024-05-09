package com.example.civicuc.ui.acceso.bienvenida;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.civicuc.R;
import com.example.civicuc.databinding.FragmentBienvenidaBinding;
import com.example.civicuc.ui.acceso.login.LoginFragment;
import com.example.civicuc.ui.acceso.registro.RegistroFragment;

public class BienvenidaFragment extends Fragment {

    private FragmentBienvenidaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBienvenidaBinding.inflate(inflater, container, false);

        /* Configura los botones para que redirijan a las interfaces que corresponda */
        Button boton_login = binding.fragmentBienvenidaBotonLogin;
        boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_bienvenida, new LoginFragment());
                fragmentTransaction.commit();
            }
        });

        Button boton_registro = binding.fragmentBienvenidaBotonRegistro;
        boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_bienvenida, new RegistroFragment());
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
