package com.example.civicuc;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.civicuc.databinding.ActivityLoginBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    /**
     * Referencia a la base de datos de Firebase
     */
    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_bienvenida, R.id.navigation_login, R.id.navigation_registro)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_login);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /* Conexión a la base de datos */
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    /**
     * Retorna la referencia a la base de datos de Firebase empleada por la app.
     * @return la referencia a la base de datos de Firebase empleada por la app
     */
    public DatabaseReference getDatabase() {
        return mDatabase;
    }

    /**
     * Desconecta el aplicativo de la base de datos de Firebase.
     */
    public void disconnectDatabase() {
        FirebaseDatabase.getInstance().goOffline();
    }

    /**
     * Autentica un usuario.
     * @param email el email del usuario
     * @param contrasenha la contrasenha del usuario
     */
    public void autenticarUsuario(String email, String contrasenha) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, contrasenha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Autenticado con exito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al autenticar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
