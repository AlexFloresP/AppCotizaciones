package com.alex.mycot;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarAccesorios extends AppCompatActivity {
    private ListView lista;
    private List<Accesorios> listaAccesoriosSelec;
    private List<String> listaAccesoriosSelec2;
    ArrayList listamandar =new ArrayList();
    String accesoriosSeleccionados="";
    String precio ;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_accesorios);
        final Button btnAcp=findViewById(R.id.buttonAgregarAccesorios);
        final String maquina= getIntent().getStringExtra("maquina");
        final String IDMaquina=(getIntent().getExtras().getInt("idMaquina")+"");
        final String PrecioBase=(getIntent().getExtras().getInt("costoBase")+"");
        final int costoBase=Integer.parseInt(PrecioBase);
        final String idTipo=(getIntent().getExtras().getInt("idTipo")+"");
        final int idmaq=Integer.parseInt(IDMaquina);
        final TextView tvMaquina=findViewById(R.id.TVMaquinaAccesorios);
        tvMaquina.setText(maquina+"");
        lista=findViewById(R.id.listaAccesoriosSeleccionar);
        final int contador=new ControladorDB(this).contadorAccesorios(idmaq);
        mostrar(idmaq);
        if(contador==0){
            Toast.makeText(getApplicationContext(), "No se encontraron accesorios", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), contador+" accesorios encontradas", Toast.LENGTH_SHORT).show();

        }
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Accesorios accesorios=listaAccesoriosSelec.get(position);
                int id=accesorios.getIdAccesorio();
                String acc=accesorios.getAccesorio();
                int precio=accesorios.getCostoExtra();
                System.out.println(id);
                accesorios.getAccesorio();
                accesorios.getCostoExtra();
                listamandar.add(id);
                accesoriosSeleccionados=accesoriosSeleccionados+","+ acc;
                Toast.makeText(getApplicationContext(),accesorios.getAccesorio()+" Seleccionado",Toast.LENGTH_SHORT).show();

            }
        });
        btnAcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),SeleccionTiempoOperador.class);
                i.putExtra("idMaquina",idmaq);
                i.putExtra("miLista", listamandar);
                i.putExtra("maquina",maquina);
                i.putExtra("CostoBase",costoBase);
                i.putExtra("idTipo",idTipo);
                startActivity(i);
                finish();


                for(int index=0; index < listamandar.size(); index++){
                    System.out.println(listamandar.get(index));

            }
            }
        });



}
    private void mostrar(int id) {
        lista=findViewById(R.id.listaAccesoriosSeleccionar);
        listaAccesoriosSelec = new ControladorDB(this).leerAccesorios(id);
        listaAccesoriosSelec2 = new ArrayList<String>();

        for (int i = 0; i < listaAccesoriosSelec.size(); i++) {
            listaAccesoriosSelec2.add(listaAccesoriosSelec.get(i).getIdAccesorio()+"");
        }
        MyAdapterAccesorios myAdapter = new MyAdapterAccesorios(this, R.layout.support_simple_spinner_dropdown_item, listaAccesoriosSelec);
        lista.setAdapter(myAdapter);

    }

}