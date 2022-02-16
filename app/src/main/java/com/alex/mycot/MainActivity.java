package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity {
    private FloatingActionsMenu famMenu;
    private FloatingActionButton acercaDe,ActualizarMaquina,CostoGas,CostoOperador,CostoFlete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final Button agregar = findViewById(R.id.agregar);
       final Button cotizar=findViewById(R.id.cotizar);
       final Button flete=findViewById(R.id.cotizarFletefuera);
        famMenu=findViewById(R.id.famMenu);
        acercaDe=findViewById(R.id.btnAcercaDe);
        ActualizarMaquina=findViewById(R.id.btnActualizarMaquina);
        CostoGas=findViewById(R.id.btnActualizarCostoGas);
        CostoOperador=findViewById(R.id.btnActualizarCostoOperador);
        CostoFlete=findViewById(R.id.btnActualizarCostoFletes);



        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Acerca_de.class);
                startActivity(intent);
                famMenu.collapseImmediately();

            }
        });
        ActualizarMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lista_Actualizar.class);
                startActivity(intent);
                famMenu.collapseImmediately();
            }
        });
        CostoGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActualizarCombustible.class);
                startActivity(intent);
                famMenu.collapseImmediately();
            }
        });
        CostoOperador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActualizarSueldoOperador.class);
                startActivity(intent);
                famMenu.collapseImmediately();

            }
        });
        CostoFlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActualizarPrecioFlete.class);
                startActivity(intent);
                famMenu.collapseImmediately();
            }
        });


         agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), AgregarMaquina.class);
                startActivityForResult(intent, 0);
            }
            });
         cotizar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent (view.getContext(), SeleccionarMaquina.class);
                 startActivityForResult(intent, 0);
             }
         });
         flete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent (getApplicationContext(), CotizarFlete.class);
                 startActivityForResult(intent, 0);
             }
         });


    }
}