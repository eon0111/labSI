package com.example.civicuc.ui.acceso.registro;

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

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}