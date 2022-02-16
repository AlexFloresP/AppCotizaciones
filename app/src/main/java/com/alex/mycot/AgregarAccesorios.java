package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AgregarAccesorios extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_accesorios);
       // final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxbtn);
        final EditText editarNombreAccesorio=(EditText) findViewById(R.id.editarNombreAccesorio);
        final EditText editarPrecioExtra=(EditText) findViewById(R.id.editarPrecioAccesorio);
        final Button btnAceptar=(Button) findViewById(R.id.btnAceptar);
        final Button btnCrearAccesorio = (Button) findViewById(R.id.btnAgregarAccesorio);
        final String maquina= getIntent().getStringExtra("maquina");
        System.out.println(""+maquina);
        final int obtenerId =new ControladorDB(getApplicationContext()).obtenerId(maquina);
        System.out.println(obtenerId);

       /* final CheckBox caja=(CheckBox) findViewById(R.id.checkBoxbtn);
            caja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(caja.isChecked()){
                        btnCrearAccesorio.setEnabled(false);
                        editarNombreAccesorio.setEnabled(false);
                        editarPrecioExtra.setEnabled(false);
                    }else {
                        btnCrearAccesorio.setEnabled(true);
                        editarNombreAccesorio.setEnabled(true);
                        editarPrecioExtra.setEnabled(true);
                    }
                }
            });*/



            btnCrearAccesorio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Context context = v.getContext();
                    if(editarNombreAccesorio.getText().toString().equals("")||editarPrecioExtra.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{
                        String NombreAccesorio=editarNombreAccesorio.getText().toString();
                        String precio=editarPrecioExtra.getText().toString();
                        int costoExtra=Integer.parseInt(precio);
                        Accesorios accesorios=new Accesorios();
                        accesorios.accesorio=NombreAccesorio;
                        accesorios.idMaquina=obtenerId;
                        accesorios.costoExtra=costoExtra;
                        boolean AccesorioGuardado = new ControladorDB(context).guardarAccesorio(accesorios);
                        if (AccesorioGuardado) {
                            Toast.makeText(getApplicationContext(), "Se agrego un accesorio", Toast.LENGTH_LONG).show();
                            editarNombreAccesorio.setText("");
                            editarPrecioExtra.setText("");

                        } else {
                            Toast.makeText(getApplicationContext(), "No se pudo agregar el accesorio", Toast.LENGTH_LONG).show();
                        }



                    }
                }
            });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });


    }



}