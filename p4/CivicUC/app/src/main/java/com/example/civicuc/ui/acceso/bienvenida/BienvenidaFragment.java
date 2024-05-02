package com.example.civicuc.ui.acceso.bienvenida;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.civicuc.databinding.FragmentBienvenidaBinding;
import com.example.civicuc.databinding.FragmentWelcomeBinding;

public class BienvenidaFragment extends Fragment {

    private FragmentBienvenidaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BienvenidaViewModel bienvenidaViewModel =
                new ViewModelProvider(this).get(BienvenidaViewModel.class);

        binding = FragmentBienvenidaBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
