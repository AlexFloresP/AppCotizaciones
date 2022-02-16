package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarMaquina extends AppCompatActivity {
    Spinner opciones;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_maquina);


        final EditText editarNombreMaquina =findViewById(R.id.editarNombreMaquina);
        final EditText editarPrecio  = findViewById(R.id.editarPrecio);
       // final int tipoMaquina=0;
        opciones=(Spinner) findViewById(R.id.spinnerOpciones);

        ArrayAdapter <CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opciones.setAdapter(adapter);
       final Button continuar=findViewById(R.id.btnContinuar);


       // final int pos=get+1;

        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos=adapterView.getSelectedItemPosition();
                System.out.println(pos);
               // tipoMaquina=pos;
                if(pos==2){
                    continuar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Context context = view.getContext();
                            if (editarNombreMaquina.getText().toString().equals("") || editarPrecio.getText().toString().equals("") || pos == 0) {
                                Toast.makeText(AgregarMaquina.this, "Debe Seleccionar el tipo de maquinaria y llenar todos los campos", Toast.LENGTH_SHORT).show();
                            } else {
                                String NombreMaquina = editarNombreMaquina.getText().toString();
                                String precio = editarPrecio.getText().toString();
                                int precioBase = Integer.parseInt(precio);
                                int tipomaq = pos;
                                Maquinas maquinas = new Maquinas();
                                maquinas.maquina = NombreMaquina;
                                maquinas.precioBase = precioBase;
                                maquinas.idTipoMaquina = tipomaq;
                                boolean MaquinaGuardada = new ControladorDB(context).guardarMaquina(maquinas);

                                if (MaquinaGuardada) {
                                    Toast.makeText(context, "Se agrego una maquina", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(context, "No se pudo agregar la maquina", Toast.LENGTH_LONG).show();
                                }

                                //int idMaquina=new ControladorDB(context).obtenerId(maquinas.maquina);

                                Intent intent = new Intent(AgregarMaquina.this, MainActivity.class);

                                startActivityForResult(intent, 0);
                                finish();
                            }
                        }

                    });
                }else {


                    continuar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Context context = view.getContext();
                            if (editarNombreMaquina.getText().toString().equals("") || editarPrecio.getText().toString().equals("") || pos == 0) {
                                Toast.makeText(AgregarMaquina.this, "Debe Seleccionar el tipo de maquinaria y llenar todos los campos", Toast.LENGTH_SHORT).show();
                            } else {
                                String NombreMaquina = editarNombreMaquina.getText().toString();
                                String precio = editarPrecio.getText().toString();
                                int precioBase = Integer.parseInt(precio);
                                int tipomaq = pos;
                                Maquinas maquinas = new Maquinas();
                                maquinas.maquina = NombreMaquina;
                                maquinas.precioBase = precioBase;
                                maquinas.idTipoMaquina = tipomaq;
                                boolean MaquinaGuardada = new ControladorDB(context).guardarMaquina(maquinas);

                                if (MaquinaGuardada) {
                                    Toast.makeText(context, "Se agrego una maquina", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(context, "No se pudo agregar la maquina", Toast.LENGTH_LONG).show();
                                }

                                //int idMaquina=new ControladorDB(context).obtenerId(maquinas.maquina);
                                String maq = NombreMaquina;
                                Intent intent = new Intent(AgregarMaquina.this, AgregarAccesorios.class);
                                System.out.println(maq);
                                intent.putExtra("maquina", maq);
                                startActivityForResult(intent, 0);
                                finish();
                            }
                        }

                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }
}