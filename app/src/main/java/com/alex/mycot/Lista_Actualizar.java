package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Lista_Actualizar extends AppCompatActivity {
    private ListView lista;
    private List<Maquinas> listaMaquinas;
    private List<String> listaMaquinas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__actualizar);
        lista=findViewById(R.id.lista);
        final int contador=new ControladorDB(this).contadorMaquinas();

        mostrar();
        if(contador==0){
            Toast.makeText(getApplicationContext(), "No se han registrado maquinas", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getApplicationContext(), contador+" maquinas encomtradas", Toast.LENGTH_LONG).show();

        }


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Maquinas maquinas=listaMaquinas.get(position);
                int tipo;
                tipo=maquinas.getIdTipoMaquina();
                if(tipo==1){
                System.out.println(position);
                Intent intent = new Intent(getApplicationContext(), ActualizarMaquina.class);
                intent.putExtra("idMaquina",maquinas.getIdMaquina());
                intent.putExtra("maquina",maquinas.getMaquina());
                intent.putExtra("costoBase",maquinas.getPrecioBase());
                intent.putExtra("idTipo",maquinas.getIdTipoMaquina());
                System.out.println("tipo maquina"+maquinas.getIdTipoMaquina());

                startActivity(intent);
            }else{
                    Intent intent = new Intent(getApplicationContext(), ActualizarCamion.class);
                    intent.putExtra("idMaquina",maquinas.getIdMaquina());
                    intent.putExtra("maquina",maquinas.getMaquina());
                    intent.putExtra("costoBase",maquinas.getPrecioBase());
                    intent.putExtra("idTipo",maquinas.getIdTipoMaquina());
                    System.out.println("tipo maquina"+maquinas.getIdTipoMaquina());
                    startActivity(intent);
                }

            }

        });
}
    private void mostrar() {
        lista = (ListView) findViewById(R.id.lista);
        listaMaquinas = new ControladorDB(this).leer();
        listaMaquinas2 = new ArrayList<String>();
        for (int i = 0; i < listaMaquinas.size(); i++) {

            listaMaquinas2.add(listaMaquinas.get(i).getIdMaquina()+"");


          /*  Maquinas maqu = new Maquinas(idMaquina, maquina,precioBase);

            listaMaquinas.add(maqu);
            listaMaquinas2.add(maquina.toUpperCase());*/

        }
        MyAdapterActualizar myAdapter = new MyAdapterActualizar(this, R.layout.list_item, listaMaquinas);
        lista.setAdapter(myAdapter);

    }
    }


