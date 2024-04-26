package com.example.centrocivico;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityListado extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String usuario;
    private UbicacionAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_caidas);
    }

    private void registraOyenteNodos() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) { }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                try {
                    adaptador.add(dataSnapshot.getValue(UbicacionCaida.class));
                    //Toast.makeText(getApplicationContext(), "Cambio: " + dataSnapshot.getValue(UbicacionCaida.class).toString(), Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        };

        mDatabase.getRef().child(usuario).child("caidas").addChildEventListener(childEventListener);
        ListView listOfMessages = (ListView) findViewById(R.id.lista);
        ArrayList<UbicacionCaida> lista = new ArrayList<UbicacionCaida>();
        adaptador = new UbicacionAdapter(this, lista);
        listOfMessages.setAdapter(adaptador);
    }

}
