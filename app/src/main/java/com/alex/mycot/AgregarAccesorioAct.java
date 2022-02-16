package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarAccesorioAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_accesorio_act);
        final Button btnAgregarAccesorioAct=findViewById(R.id.btnAgregarAccesorioAct);
        final Button btnAceptarAct=findViewById(R.id.btnAceptarAct);
        final EditText editarNombreAccesorioAct=(EditText) findViewById(R.id.editarNombreAccesorioAct);
        final EditText editarPrecioExtraAct=(EditText) findViewById(R.id.editarPrecioAccesorioAct);
        final String maquina= getIntent().getStringExtra("maquina");
        System.out.println(""+maquina);
        final int obtenerId =new ControladorDB(getApplicationContext()).obtenerId(maquina);
        System.out.println(obtenerId);

        btnAgregarAccesorioAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = view.getContext();
                if(editarNombreAccesorioAct.getText().toString().equals("")||editarPrecioExtraAct.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    String NombreAccesorio=editarNombreAccesorioAct.getText().toString();
                    String precio=editarPrecioExtraAct.getText().toString();
                    int costoExtra=Integer.parseInt(precio);
                    Accesorios accesorios=new Accesorios();
                    accesorios.accesorio=NombreAccesorio;
                    accesorios.idMaquina=obtenerId;
                    accesorios.costoExtra=costoExtra;
                    boolean AccesorioGuardado = new ControladorDB(context).guardarAccesorio(accesorios);
                    if (AccesorioGuardado) {
                        Toast.makeText(getApplicationContext(), "Se agrego un accesorio", Toast.LENGTH_LONG).show();
                        editarNombreAccesorioAct.setText("");
                        editarPrecioExtraAct.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo agregar el accesorio", Toast.LENGTH_LONG).show();
                    }
                    finish();



                }
            }
        });

    }
}