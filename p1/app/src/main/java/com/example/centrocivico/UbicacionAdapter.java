package com.example.centrocivico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class UbicacionAdapter extends ArrayAdapter<UbicacionCaida> {

    public UbicacionAdapter(Context context, ArrayList<UbicacionCaida> users) {
        super(context, 0, users);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        UbicacionCaida ubicacion = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_lista_caidas, parent, false);
        }

        TextView tvUbicacion = (TextView) convertView.findViewById(R.id.tvUbicacion);
        tvUbicacion.setText(ubicacion.toString());
        return convertView;
    }

}
