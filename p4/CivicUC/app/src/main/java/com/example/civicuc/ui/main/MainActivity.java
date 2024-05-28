package com.example.civicuc.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.civicuc.R;
import com.example.civicuc.ui.main.caida.UbicacionCaida;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.civicuc.databinding.ActivityMainBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    /**
     * Referencia a la base de datos de Firebase
     */
    private DatabaseReference mDatabase;

    /**
     * Referencia al gestor de autenticación
     */
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_inicio, R.id.navigation_chat, R.id.navigation_listado,
                R.id.navigation_caida, R.id.navigation_actividades)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        /* Conexión a la base de datos */
        FirebaseDatabase.getInstance().goOnline();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        /* Conexión al gestor de autenticación */
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(getIntent().getStringExtra("email"),
                                         getIntent().getStringExtra("contrasenha"));
    }

    /**
     * Retorna la referencia a la base de datos de Firebase empleada por la app.
     * @return la referencia a la base de datos de Firebase empleada por la app
     */
    public DatabaseReference getDatabase() {
        return mDatabase;
    }

    /**
     * Retorna el email del usuario.
     * @return el email del usuario
     */
    public String getEmailUsuario() { return mAuth.getCurrentUser().getEmail(); }

    /**
     * Registra una nueva caída en la base de datos.
     * @param latitud el valor de latitud de su ubicación
     * @param longitud el valor de longitud de su ubicación
     */
    public void writeNewUbicacion (String latitud, String longitud, String email) {
        UbicacionCaida ubicacion = new UbicacionCaida(latitud, longitud, email);
        String key = mDatabase.child("pendientes")
                              .push()
                              .getKey();
        mDatabase.child("pendientes")
                 .child(key)
                 .setValue(ubicacion);
    }

}
