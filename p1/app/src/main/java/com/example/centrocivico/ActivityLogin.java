package com.example.centrocivico;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Dar de alta un nuevo usuario a través de su correo y una password:
    private void nuevoUsuario(String usuario, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(usuario,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Nuevo usuario", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al dar de alta al usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Autenticar un nuevo usuario a través de su correo y una password:
    private void autenticarUsuario(String usuario, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(usuario,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

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

    //Utilización de los métodos de la FirebaseUser para actualizar el correo del usuario:
    private void actualizaCorreo(String correo){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Correo actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al actualizar correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //UserProfileChangeRequest:
    private void actualizaPerfil(String usuario, String foto){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(usuario).setPhotoUri(Uri.parse(foto)).build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Perfil actualizado " + FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Cerrar la sesión de un usuario
    public void desconectar(View v) {
        FirebaseAuth.getInstance().signOut();
    }
}