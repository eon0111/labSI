package com.example.civicuc.ui.acceso.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.civicuc.MainActivity;
import com.example.civicuc.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    /**
     * Referencia a la base de datos de Firebase
     */
    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;

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

        /* Conexión a la base de datos */
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Autentica un usuario.
     * @param email el email del usuario
     * @param contrasenha la contrasenha del usuario
     */
    public void autenticarUsuario(String email, String contrasenha) {
        if (email == null || contrasenha == null) {
            binding.fragmentLoginTextoErrLogin.setVisibility(View.VISIBLE);
        } else {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, contrasenha).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);

                        /* Le pasa el usuario y la contrasenha a la activida principal para loggear
                         * desde ahí al usuario y que pueda operar con la BD */
                        intent.putExtra("email", binding.fragmentLoginInputEmail.getText().toString());
                        intent.putExtra("contrasenha", binding.fragmentLoginInputContrasenha.getText().toString());
                        startActivity(intent);

                        /* Se desconecta de la BD para reconectarse desde la actividad princpal */
                        FirebaseDatabase.getInstance().goOffline();
                    } else {
                        binding.fragmentLoginTextoErrLogin.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}