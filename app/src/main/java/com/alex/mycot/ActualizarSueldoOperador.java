package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ActualizarSueldoOperador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_sueldo_operador);
        final int contador=new ControladorDB(this).contadorOperador();
        int idOperador=0;
        final EditText edtSueldo=findViewById(R.id.editarActualizarPrecioOperador);
        final Button btnActualizar=findViewById(R.id.btnGuardarActualizarOperador);
        final List<Operador> operador=new ControladorDB(this).leerOperador();
        if(contador==0) {
            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtSueldo.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{
                        String sueldo=edtSueldo.getText().toString();
                        int costo=Integer.parseInt(sueldo);
                        Operador operador=new Operador();
                        operador.sueldo=costo;

                        boolean OperadorGuardado = new ControladorDB(getApplicationContext()).guardarOperador(operador);
                        if (OperadorGuardado) {
                            Toast.makeText(getApplicationContext(), "Costo extra por operador guardado", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se pudo guardar", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

        }else{
            if (operador.size()>0) {
                for (Operador operado : operador) {
                     idOperador = operado.id;

                    int precio = operado.sueldo;

                    edtSueldo.setText(""+precio);
                }
            }
            final int finalIdOp = idOperador;
            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtSueldo.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{
                        String precioExtra=edtSueldo.getText().toString();
                        int precio=Integer.parseInt(precioExtra);
                        int id=finalIdOp;
                        Operador operador=new Operador();
                        operador.id=id;
                        operador.sueldo=precio;

                        if(id != 0){

                            if(new ControladorDB(getApplicationContext()).ActualizarOperador(operador)){

                                Toast.makeText(getApplicationContext(),"Costo extra por operador actualizado",Toast.LENGTH_LONG).show();

                                finish();

                            }else{
                                Toast.makeText(getApplicationContext(),"Costo extra por operador no actualizado",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            });



        }
    }
}