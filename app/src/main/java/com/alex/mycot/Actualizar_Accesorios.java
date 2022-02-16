package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Actualizar_Accesorios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar__accesorios);
        final EditText ActAccesorio=findViewById(R.id.editarActualizarAccesorio);
        final EditText ActAccesorioPrecio=findViewById(R.id.editarActualizarPrecioAccesorio);
        final Button EliminarAcc=findViewById(R.id.btnEliminarAccesorio);
        final Button btnActualizarAccesorio=findViewById(R.id.btnGuardarActualizar);
        final String IDMaquina=(getIntent().getExtras().getInt("idMaquina")+"");
        final String IDAccesorio=(getIntent().getExtras().getInt("idAccesorio")+"");
        final int i=Integer.parseInt(IDMaquina);

        ActAccesorio.setText(getIntent().getExtras().getString("accesorio"));
        ActAccesorioPrecio.setText(getIntent().getExtras().getInt("costoExtra")+"");

        btnActualizarAccesorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActAccesorio.getText().toString().equals("")||ActAccesorioPrecio.getText().toString().equals("")){
                    Toast.makeText(Actualizar_Accesorios.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    String NombreAccesorio=ActAccesorio.getText().toString();
                    String precioExtra=ActAccesorioPrecio.getText().toString();
                    int precioBase=Integer.parseInt(precioExtra);

                    int idAcces=Integer.parseInt(IDAccesorio);
                    System.out.println(i);
                    Accesorios accesorios=new Accesorios();
                    accesorios.accesorio=NombreAccesorio;
                    accesorios.costoExtra=precioBase;
                    accesorios.idAccesorio =idAcces;

                    if(i != 0){

                        if(new ControladorDB(getApplicationContext()).ActualizarAccesorio(accesorios)){

                            Toast.makeText(getApplicationContext(),"Accesorio actualizado",Toast.LENGTH_LONG).show();
                            int idMaq=Integer.parseInt(IDMaquina);
                            System.out.println("Id maquina Accesorio a listar accesorios"+idMaq);
                            Intent intent = new Intent(getApplicationContext(), Listar_accesorios.class);
                            intent.putExtra("idMaquina",idMaq);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(),"Accesorio no actualizado",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        EliminarAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idAcc=Integer.parseInt(IDAccesorio);
                boolean AccesorioEliminado = new ControladorDB(getApplicationContext()).eliminarAccesorio(idAcc);
                if (AccesorioEliminado) {

                    Toast.makeText(getApplicationContext(), "Se elimino el accesorio", Toast.LENGTH_LONG).show();
                    int idMaq=Integer.parseInt(IDMaquina);
                    Intent intent = new Intent(getApplicationContext(), Listar_accesorios.class);
                    intent.putExtra("idMaquina",idMaq);
                    startActivity(intent);
                    finish();



                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo elimnar el accesorio", Toast.LENGTH_LONG).show();

                }



            }
        });

    }
}