package com.example.civicuc.ui.acceso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.civicuc.R;
import com.example.civicuc.databinding.FragmentBienvenidaBinding;

public class BienvenidaFragment extends Fragment {

    private FragmentBienvenidaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBienvenidaBinding.inflate(inflater, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        /* Configura los botones para que redirijan a las interfaces que corresponda */
        binding.fragmentBienvenidaBotonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reemplazaFragment(new LoginFragment());
            }
        });

        binding.fragmentBienvenidaBotonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reemplazaFragment(new RegistroFragment());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void reemplazaFragment(Fragment nuevoFragment) {
        binding.fragmentBienvenidaLogoApp.setVisibility(View.GONE);
        binding.fragmentBienvenidaNombreApp.setVisibility(View.GONE);
        binding.fragmentBienvenidaMensajeBienvenidaTitulo.setVisibility(View.GONE);
        binding.fragmentBienvenidaMensajeBienvenidaContenido.setVisibility(View.GONE);
        binding.fragmentBienvenidaBotonLogin.setVisibility(View.GONE);
        binding.fragmentBienvenidaBotonRegistro.setVisibility(View.GONE);


        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bienvenida, nuevoFragment);
        fragmentTransaction.commit();
    }

}
