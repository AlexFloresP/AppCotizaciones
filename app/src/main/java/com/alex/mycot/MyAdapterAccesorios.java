package com.alex.mycot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapterAccesorios extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Accesorios> accesorios;
    public MyAdapterAccesorios(Context context, int layout, List<Accesorios> accesorios) {
        this.context = context;
        this.layout = layout;
        this.accesorios = accesorios;
    }
    @Override
    public int getCount() {
        return this.accesorios.size();
    }

    @Override
    public Object getItem(int position) {
        return this.accesorios.get(position);

    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item_accesorios, null);


        Accesorios a = this.accesorios.get(position);
        int idAccesorio = a.getIdAccesorio();
        String id=String.valueOf(idAccesorio);
        String nombre = a.getAccesorio();
        int precio=a.getCostoExtra();
        String precioExtra=String.valueOf(precio);
        TextView textMatricula = v.findViewById(R.id.idAccesorio);
        TextView textNombre = v.findViewById(R.id.nombreAccesorio);
        TextView textprecio = v.findViewById(R.id.Costo_Extra);

        textMatricula.setText(id);
        textNombre.setText(nombre);
        // textMatricula.setText(idMaquina);
        textprecio.setText(precioExtra);
        return v;
    }
}
