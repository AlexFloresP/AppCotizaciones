package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActualizarCombustible extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_combustible);
        final int contador=new ControladorDB(this).contador();
        int idCom = 0;
        final Button btnActCom=findViewById(R.id.btnGuardarActualizarCombustible);
        final EditText edtAcTCom=findViewById(R.id.editarActualizarPrecioCombustible);
        final EditText edtActComb=findViewById(R.id.editarActualizarCombustible);
        final List<Combustible> combustibles=new ControladorDB(this).leerCombustible();



        if(contador==0){
            edtActComb.setText("Diesel");
            edtActComb.setEnabled(false);
            btnActCom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtAcTCom.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{


                        String precio=edtAcTCom.getText().toString();
                        int costo=Integer.parseInt(precio);
                        Combustible combustible=new Combustible();
                        combustible.precioCombustible=costo;

                        boolean CombustibleGuardado = new ControladorDB(getApplicationContext()).guardarCombustible(combustible);
                        if (CombustibleGuardado) {
                            Toast.makeText(getApplicationContext(), "Costo extra por combustible guardado", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Costo extra por combustible no guardado", Toast.LENGTH_LONG).show();
                        }
                    }


                }
            });
        }else{

            if (combustibles.size()>0) {
                for (Combustible combustible : combustibles) {
                    idCom = combustible.idCombustible;
                    String combustibl = combustible.combustible;
                    int precio = combustible.precioCombustible;

                    edtActComb.setText(combustibl);

                    edtAcTCom.setText(""+precio);
                }
            }
            final int finalIdCom = idCom;
            btnActCom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtAcTCom.getText().toString().equals("")||edtActComb.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }else{
                        String NombreComb=edtActComb.getText().toString();
                        String precioExtra=edtAcTCom.getText().toString();
                        int precio=Integer.parseInt(precioExtra);
                        int id=finalIdCom;
                        Combustible combustible=new Combustible();
                        combustible.idCombustible=id;
                        combustible.combustible=NombreComb;
                        combustible.precioCombustible=precio;

                        if(id != 0){

                            if(new ControladorDB(getApplicationContext()).ActualizarCombustible(combustible)){

                                Toast.makeText(getApplicationContext(),"Costo extra por combustible actualizado",Toast.LENGTH_LONG).show();

                                finish();

                            }else{
                                Toast.makeText(getApplicationContext(),"Costo extra por combustible no actualizado",Toast.LENGTH_LONG).show();
                            }
                        }
                    }



                }
            });
            }



    }
}