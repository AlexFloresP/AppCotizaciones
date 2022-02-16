package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarMaquina extends AppCompatActivity {
    Spinner opciones;
    private ListView lista;
    private List<Maquinas> listaMaquinas;
    private List<String> listaMaquinas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_maquina);
        lista=findViewById(R.id.listaSeleccionar);
        opciones=(Spinner) findViewById(R.id.spinnerSeleccionar);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opciones.setAdapter(adapter);
        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos=adapterView.getSelectedItemPosition();
                if(pos==0){
                    lista.setSelection(0);
                }else if(pos==2){
                    mostrar(2);
                }else{
                    mostrar(1);
                }
                System.out.println(pos);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Maquinas maquinas=listaMaquinas.get(position);
                        System.out.println(position);
                        int idtipo=maquinas.getIdTipoMaquina();
                        if(idtipo==1){
                            Intent intent = new Intent(getApplicationContext(), SeleccionarAccesorios.class);
                            intent.putExtra("idMaquina",maquinas.getIdMaquina());
                            intent.putExtra("maquina",maquinas.getMaquina());
                            intent.putExtra("costoBase",maquinas.getPrecioBase());
                            intent.putExtra("idTipo",maquinas.getIdTipoMaquina());
                            System.out.println("tipo maquina"+maquinas.getIdTipoMaquina());
                            startActivity(intent);
                            finish();
                        }else if(idtipo==2){
                            Intent intent = new Intent(getApplicationContext(), ZonaLocal.class);
                            intent.putExtra("idMaquina",maquinas.getIdMaquina());
                            intent.putExtra("maquina",maquinas.getMaquina());
                            intent.putExtra("costoBase",maquinas.getPrecioBase());
                            startActivity(intent);
                            finish();
                        }



                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void mostrar(int idTipo) {
        lista = (ListView) findViewById(R.id.listaSeleccionar);
        listaMaquinas = new ControladorDB(this).leerSeleccion(idTipo);
        listaMaquinas2 = new ArrayList<String>();
        for (int i = 0; i < listaMaquinas.size(); i++) {

            listaMaquinas2.add(listaMaquinas.get(i).getIdMaquina()+"");


          /*  Maquinas maqu = new Maquinas(idMaquina, maquina,precioBase);

            listaMaquinas.add(maqu);
            listaMaquinas2.add(maquina.toUpperCase());*/

        }
        MyAdapterActualizar myAdapter = new MyAdapterActualizar(this, R.layout.list_seleccionar, listaMaquinas);
        lista.setAdapter(myAdapter);

    }
}