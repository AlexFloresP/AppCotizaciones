package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActualizarCamion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_camion);
        final EditText ActMaquinaCam=findViewById(R.id.editarActualizarMaquinaCam);
        final EditText ActPrecioCam=findViewById(R.id.editarActualizarPrecioCam);
        final Button btnActualizar=findViewById(R.id.btnActCamion);
        final Button btnAceptar=findViewById(R.id.botonMain);
        final Button btnEliminarCam=findViewById(R.id.btnElimCam);
        final String ID=(getIntent().getExtras().getInt("idMaquina")+"");
        final String posicion=(getIntent().getExtras().getInt("idTipo")+"");
        int pos=Integer.parseInt(posicion);
        System.out.println(pos);
        int id=Integer.parseInt(ID);
        final String maquina= getIntent().getStringExtra("maquina");
        final String CostoBase= getIntent().getStringExtra("costoBase");
        final int contador=new ControladorDB(this).contadorAccesorios(id);
        Spinner opcion;

        opcion=(Spinner) findViewById(R.id.spinnerActCam);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcion.setAdapter(adapter);


        ActMaquinaCam.setText(getIntent().getExtras().getString("maquina"));
        ActPrecioCam.setText(getIntent().getExtras().getInt("costoBase")+"");
        opcion.setSelection(pos);

        opcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos=adapterView.getSelectedItemPosition();
                btnActualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ActMaquinaCam.getText().toString().equals("")||ActPrecioCam.getText().toString().equals("")||pos==0){
                            Toast.makeText(ActualizarCamion.this, "Debe seleccionar el tipo de maquinaria y llenar todos los campos", Toast.LENGTH_SHORT).show();
                        }else{
                            String NombreMaquina=ActMaquinaCam.getText().toString();
                            String precio=ActPrecioCam.getText().toString();
                            int precioBase=Integer.parseInt(precio);
                            int i=Integer.parseInt(ID);
                            Maquinas maquinas=new Maquinas();
                            maquinas.maquina=NombreMaquina;
                            maquinas.precioBase=precioBase;
                            maquinas.idMaquina =i;
                            maquinas.idTipoMaquina=pos;

                            if(i != 0){

                                if(new ControladorDB(getApplicationContext()).ActualizarMaquina(maquinas)){

                                    Toast.makeText(getApplicationContext(),"MAQUINA ACTUALIZADA",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"MAQUINA NO ACTUALIZADA",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnEliminarCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idmaq=Integer.parseInt(ID);
                boolean MaquinaEliminada = new ControladorDB(getApplicationContext()).eliminarMaquina(idmaq);
                if (MaquinaEliminada) {
                    Toast.makeText(getApplicationContext(), "Se elimino la maquina", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo elimnar la maquina", Toast.LENGTH_LONG).show();
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