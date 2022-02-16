package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Listar_accesorios extends AppCompatActivity {
    private ListView listaAcces;
    private List<Accesorios> listaAccesorios;
    private List<String> listaAccesorios2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_accesorios);
        listaAcces=findViewById(R.id.listaAccesorios);
        final String ID=(getIntent().getExtras().getInt("idMaquina")+"");
        final int idMaquina=Integer.parseInt(ID);
        System.out.println("ID maquina recivida"+idMaquina);
        mostrar(idMaquina);
        listaAcces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Accesorios accesorios=listaAccesorios.get(position);
                System.out.println(position);
                Intent intent = new Intent(getApplicationContext(), Actualizar_Accesorios.class);
                intent.putExtra("idAccesorio",accesorios.getIdAccesorio());
                intent.putExtra("idMaquina",idMaquina);
                System.out.println(accesorios.idAccesorio);
                intent.putExtra("accesorio",accesorios.getAccesorio());
                intent.putExtra("costoExtra",accesorios.getCostoExtra());
                startActivity(intent);
                finish();


            }

        });

    }


    private void mostrar(int id) {
        listaAcces = (ListView) findViewById(R.id.listaAccesorios);
        listaAccesorios = new ControladorDB(this).leerAccesorios(id);
        listaAccesorios2 = new ArrayList<String>();
        for (int i = 0; i < listaAccesorios.size(); i++) {

            listaAccesorios2.add(listaAccesorios.get(i).getIdAccesorio()+"");


          /*  Maquinas maqu = new Maquinas(idMaquina, maquina,precioBase);

            listaMaquinas.add(maqu);
            listaMaquinas2.add(maquina.toUpperCase());*/

        }
        MyAdapterAccesorios myAdapter = new MyAdapterAccesorios(this, R.layout.list_item_accesorios, listaAccesorios);
        listaAcces.setAdapter(myAdapter);

    }
}
