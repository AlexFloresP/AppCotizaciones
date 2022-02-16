package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZonaLocal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_local);
        final String maquina= getIntent().getStringExtra("maquina");
        final String IDMaquina=(getIntent().getExtras().getInt("idMaquina")+"");
        final String PrecioBase=(getIntent().getExtras().getInt("costoBase")+"");
        final int costoBase=Integer.parseInt(PrecioBase);
        final int idmaq=Integer.parseInt(IDMaquina);
        Button btnAceptar =findViewById(R.id.btnAceptarFlete);
        final EditText edtKilom=findViewById(R.id.editarKilometrosFlete);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtKilom.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Ingrese los kilometros",Toast.LENGTH_SHORT).show();
                }else{
                    int kilometros=Integer.parseInt(edtKilom.getText().toString());
                    Intent i = new Intent(getApplicationContext(),GenerarPdfFlete.class);
                    i.putExtra("idMaquina",idmaq);
                    i.putExtra("maquina",maquina);
                    i.putExtra("CostoBase",costoBase);
                    i.putExtra("kilometros",kilometros);
                    startActivity(i);
                    finish();
                }
            }
        });


    }
}