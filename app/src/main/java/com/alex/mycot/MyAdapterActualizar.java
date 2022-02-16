package com.alex.mycot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapterActualizar extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Maquinas> maquinas;

    public MyAdapterActualizar(Context context, int layout, List<Maquinas> maquinas) {
        this.context = context;
        this.layout = layout;
        this.maquinas = maquinas;
    }
    @Override
    public int getCount() {
        return this.maquinas.size();
    }

    @Override
    public Object getItem(int posotion) {
        return this.maquinas.get(posotion);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item, null);


        Maquinas a = this.maquinas.get(position);
        int idMaquina = a.getIdMaquina();
        String id=String.valueOf(idMaquina);
        String nombre = a.getMaquina();
        int precio=a.getPrecioBase();
        String precioBase=String.valueOf(precio);
        TextView textMatricula = v.findViewById(R.id.idMaquina);
        TextView textNombre = v.findViewById(R.id.nombreMaquina);
        TextView textprecio = v.findViewById(R.id.Costo_base);

        textMatricula.setText(id);
        textNombre.setText(nombre);
       // textMatricula.setText(idMaquina);
        textprecio.setText(precioBase);
        return v;
    }
}
