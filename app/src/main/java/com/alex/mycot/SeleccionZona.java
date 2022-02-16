package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeleccionZona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_zona);

        Button btnZona=findViewById(R.id.btnFleteEnZona);
        Button btnFueraZona=findViewById(R.id.btnFleteFueraZona);

        final String maquina= getIntent().getStringExtra("maquina");
        final String IDMaquina=(getIntent().getExtras().getInt("idMaquina")+"");
        final String PrecioBase=(getIntent().getExtras().getInt("costoBase")+"");
        final int costoBase=Integer.parseInt(PrecioBase);
        final int idmaq=Integer.parseInt(IDMaquina);
btnZona.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ZonaLocal.class);
        intent.putExtra("idMaquina",idmaq);
        intent.putExtra("maquina",maquina);
        intent.putExtra("costoBase",costoBase);
        startActivity(intent);
    }
});
btnFueraZona.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FueraZona.class);
        startActivity(intent);
    }
});

    }
}