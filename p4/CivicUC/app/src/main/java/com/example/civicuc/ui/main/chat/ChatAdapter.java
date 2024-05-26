package com.example.civicuc.ui.main.chat;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.civicuc.R;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<Mensaje> {

    public ChatAdapter(Context context, ArrayList<Mensaje> users) {
        super(context, R.layout.item_chat, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mensaje mensaje = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat, parent, false);
            Toast.makeText(getContext(), parent.getId() + "", Toast.LENGTH_LONG).show();
        }

        TextView tvEmisor = (TextView) convertView.findViewById(R.id.tvEmisor);
        TextView tvContenido = (TextView) convertView.findViewById(R.id.tvContenido);
        TextView tvFecha = (TextView) convertView.findViewById(R.id.tvMomento);
        tvEmisor.setText(mensaje.getEmisor());
        tvContenido.setText(mensaje.getContenido());
        tvFecha.setText(mensaje.getMomento());

        return convertView;
    }

}
