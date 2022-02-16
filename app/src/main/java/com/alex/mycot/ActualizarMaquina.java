package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActualizarMaquina extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        final EditText ActMaquina=findViewById(R.id.editarActualizarMaquina);
        final EditText ActPrecio=findViewById(R.id.editarActualizarPrecio);
        final Button btnActualizar=findViewById(R.id.btnActualizarDatosMaquina);
        final Button btnAceptar=findViewById(R.id.btnMain);
        final Button btnAccesorios=findViewById(R.id.btnActualizarAccesorios);
        final Button btnEliminarMaquina=findViewById(R.id.btnEliminarMaquina);
        final Button btnAgregarAcc=findViewById(R.id.btnActAgregarAccesorio);
        final String ID=(getIntent().getExtras().getInt("idMaquina")+"");
        final String posicion=(getIntent().getExtras().getInt("idTipo")+"");
        int pos=Integer.parseInt(posicion);
        System.out.println(pos);
        int id=Integer.parseInt(ID);
        final String maquina= getIntent().getStringExtra("maquina");
        final String CostoBase= getIntent().getStringExtra("costoBase");
        final int contador=new ControladorDB(this).contadorAccesorios(id);
        Spinner opcion;

        opcion=(Spinner) findViewById(R.id.spinnerAct);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcion.setAdapter(adapter);


        ActMaquina.setText(getIntent().getExtras().getString("maquina"));
        ActPrecio.setText(getIntent().getExtras().getInt("costoBase")+"");
        opcion.setSelection(pos);

        opcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos=adapterView.getSelectedItemPosition();
                btnActualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ActMaquina.getText().toString().equals("")||ActPrecio.getText().toString().equals("")||pos==0){
                            Toast.makeText(ActualizarMaquina.this, "Debe seleccionar el tipo de maquinaria y llenar todos los campos", Toast.LENGTH_SHORT).show();
                        }else{
                            String NombreMaquina=ActMaquina.getText().toString();
                            String precio=ActPrecio.getText().toString();
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

        btnAgregarAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maq=maquina;
                Intent intent = new Intent(getApplicationContext(), AgregarAccesorioAct.class);
                System.out.println(maq);
                intent.putExtra("maquina",maq );
                startActivityForResult(intent,0);
                finish();
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

        if(contador==0){
            Toast.makeText(getApplicationContext(),"La maquina no cuenta con accesorios",Toast.LENGTH_LONG).show();
            btnAccesorios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"La maquina no cuenta con accesorios",Toast.LENGTH_LONG).show();
                }
            });
        }else {


        btnAccesorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Listar_accesorios.class);
                int id=Integer.parseInt(ID);
                intent.putExtra("idMaquina",id);
                startActivity(intent);

            }
        });

      }
        btnEliminarMaquina.setOnClickListener(new View.OnClickListener() {
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

    }
}