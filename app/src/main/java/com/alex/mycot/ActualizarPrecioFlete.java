package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ActualizarPrecioFlete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_precio_flete);
        final int contador=new ControladorDB(this).contadorFlete();
        int idFlete=0;
        final EditText edtbase=findViewById(R.id.editarActualizarPrecioBaseFlete);
        final EditText edtExtra=findViewById(R.id.editarActualizarPrecioExtraFlete);
        final Button btnGuardarPrecioFlete=findViewById(R.id.btnGuardarCostoFlete);
        final List<Flete> fletes=new ControladorDB(this).leerFletes();

        if(contador==0) {
            btnGuardarPrecioFlete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtbase.getText().toString().equals("")&&edtExtra.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{
                        String base=edtbase.getText().toString();
                        String extra=edtExtra.getText().toString();
                        int costobase=Integer.parseInt(base);
                        int costoExtra=Integer.parseInt(extra);
                        Flete flete=new Flete();
                        flete.precioBase=costobase;
                        flete.precioExtra=costoExtra;

                        boolean OperadorGuardado = new ControladorDB(getApplicationContext()).guardarFlete(flete);
                        if (OperadorGuardado) {
                            Toast.makeText(getApplicationContext(), "Costo extra por fletes guardados", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se pudo guardar", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

        }else{
            if (fletes.size()>0) {
                for (Flete flete : fletes) {
                    idFlete = flete.id;

                    int precioBase = flete.precioBase;
                    int precioExtra=flete.precioExtra;

                    edtbase.setText(""+precioBase);
                    edtExtra.setText(""+precioExtra);
                }
            }
            final int finalIdFlete = idFlete;
            btnGuardarPrecioFlete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtbase.getText().toString().equals("")&&edtExtra.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{
                        String precioBase=edtbase.getText().toString();
                        String precioExtra=edtExtra.getText().toString();
                        int precioExt=Integer.parseInt(precioExtra);
                        int precioBas=Integer.parseInt(precioBase);
                        int id=finalIdFlete;
                        Flete flete=new Flete();
                        flete.id=id;
                        flete.precioBase=precioBas;
                        flete.precioExtra=precioExt;
                        if(id != 0){

                            if(new ControladorDB(getApplicationContext()).ActualizarFlete(flete)){

                                Toast.makeText(getApplicationContext(),"Costos extra por flete actualizados",Toast.LENGTH_LONG).show();

                                finish();

                            }else{
                                Toast.makeText(getApplicationContext(),"Costo extra por flete no actualizados",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            });

        }

    }
}