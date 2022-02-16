package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GenerarPdf extends AppCompatActivity {
    private ListView listaver, listaTotales;
    private List<Accesorios> listaAccesoriosTotales;
    private List<String> listaAccesoriosTotales2;
    private List<Accesorios> listaAccesoriosGenerar;
    private List<String> listaAccesoriosGenerar2;
    ArrayList precios = new ArrayList();
    CheckBox opera, com;
    String NOMBRE_DIRECTORIO = "MisCotizaciones";

    Button GenerarPdf;
    //Valores Recibidos por intent
    String maquinapdf;
    String preciobasepdf;
    int totalCotizacion=0;
    int diaslaboralespdf = 0;
    int costoTotalMaquina = 0;
    int costoTotalAccesorios=0;
    int operadorpdf=0;
    int combustiblepdf=0,CantidadCombustible=0,CostoCombustible=0;
    int sueldoOperador=0;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strDate = sdf.format(c.getTime());
    String NOMBRE_DOCUMENTO = "cotizacion" + strDate + ".pdf";
    private File file;
    private FileOutputStream ficheroPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generar_pdf);
        final ArrayList lista = (ArrayList) getIntent().getSerializableExtra("miListaAC");
        final String maquina = getIntent().getStringExtra("maquina");
        maquinapdf = maquina;
        final String PrecioBase = (getIntent().getExtras().getInt("CostoBase") + "");
        final int costobasema = Integer.parseInt(PrecioBase);
        preciobasepdf = PrecioBase;
        final String fecha = getIntent().getStringExtra("fechainicio");
        System.out.println("pdf fecha" + fecha);
        final String IDMaquina = (getIntent().getExtras().getInt("idMaquina") + "");
        final String idTipo = (getIntent().getExtras().getInt("tipo") + "");
        final String dias = (getIntent().getExtras().getInt("dias") + "");
        final int diaslab = Integer.parseInt(dias);
        diaslaboralespdf = diaslab;
        final String CantComb = (getIntent().getExtras().getInt("cantidadCombustible") + "");
        final int CantCo = Integer.parseInt(CantComb);
        CantidadCombustible=CantCo;
        final int idmaqu = Integer.parseInt(IDMaquina);
        final String operador = (getIntent().getExtras().getInt("operador") + "");
        int oper = Integer.parseInt(operador);

        operadorpdf=oper;

        final String combustible = (getIntent().getExtras().getInt("combustible") + "");
        int combus = Integer.parseInt(combustible);
        combustiblepdf=combus;


        costoTotalMaquina = costobasema * diaslaboralespdf;
        totalCotizacion=totalCotizacion+costoTotalMaquina;
        TextView TVmaquina = findViewById(R.id.maquinapdf);
        TextView TVprecio = findViewById(R.id.preciomaquinapdf);
        TextView txtop = findViewById(R.id.TVSueldoOperador);
        TextView txcom = findViewById(R.id.TVCostoCombustible);
        TextView TVdias = findViewById(R.id.TVDias);
        TextView TVdiasMaquina=findViewById(R.id.TVDiasMaquina);
        TextView TVTotalAcc=findViewById(R.id.TVTotalAcc);
        TextView TvSueldo=findViewById(R.id.textView19);
        TextView TVLitros=findViewById(R.id.TVLitros);
        TextView TVTotalCotizacion=findViewById(R.id.TVTotalCot);
        com = findViewById(R.id.checkCombustiblePdf);
        opera = findViewById(R.id.checkOperadorPdf);
        GenerarPdf = findViewById(R.id.btnGenerarPdf);
        TVmaquina.setText(maquina);
        TVdiasMaquina.setText(diaslaboralespdf+"");
        TVprecio.setText(costoTotalMaquina + "");
        TVdias.setText(diaslaboralespdf + "");
        TVLitros.setText(CantCo+"");




        int precio = new ControladorDB(this).CostosAccesorios(lista);
        System.out.println("pecio de accesorios=" + (precio * diaslaboralespdf));
        costoTotalAccesorios=precio*diaslaboralespdf;
        TVTotalAcc.setText(costoTotalAccesorios+"");
        totalCotizacion=totalCotizacion+costoTotalAccesorios;
        Toast.makeText(getApplicationContext(), "precio de accseorios" +costoTotalAccesorios, Toast.LENGTH_LONG).show();
        if (oper == 1) {
            opera.setChecked(true);
            opera.setEnabled(false);
            int sueldo = new ControladorDB(this).precioOperador();
            int dia = sueldo / 6;
            int total = dia * diaslab;
            sueldoOperador=total;
            totalCotizacion=totalCotizacion+total;
            txtop.setText("" + total);
        } else {
            opera.setEnabled(false);
            TvSueldo.setCursorVisible(false);
        }
        if (combus == 1) {
            com.setChecked(true);
            com.setEnabled(false);
            int costocom = new ControladorDB(this).CostoCombustible();
            int total = CantCo * costocom;
            CostoCombustible=total;
            totalCotizacion=totalCotizacion+total;
            txcom.setText("" + total);
        } else {
            com.setEnabled(false);
        }
        mostrar(lista);
        mostrarTotales(lista, diaslaboralespdf);
        TVTotalCotizacion.setText(totalCotizacion+"");
        if (lista.size() != 0) {
            int id = 0;
            for (int index = 0; index < lista.size(); index++) {
                System.out.println(lista.get(index));
                id = (int) lista.get(index);
            }
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1000);
        }
        GenerarPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPDF();
                Toast.makeText(getApplicationContext(), "Se genero la cotizacion", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void crearPDF(){
        crearFichero(NOMBRE_DIRECTORIO);
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString(), "PDF");
        String targetPdf = folder+NOMBRE_DOCUMENTO;
        File filePath = new File(targetPdf);
        Document documento =new Document();
        try {
            // file= crearFichero(NOMBRE_DOCUMENTO);
           //  ficheroPDF = new FileOutputStream(filePath);
             PdfWriter.getInstance(documento, new FileOutputStream(filePath));
           // PdfWriter.getInstance(document, new FileOutputStream("c:/sample.pdf"));
            documento.open();
           // documento.add(new Chunk(""));
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.encabezado);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image imagen = Image.getInstance(stream.toByteArray());
            imagen.setAlignment(Element.ALIGN_CENTER);
            imagen.scaleAbsoluteHeight(100);
            imagen.scaleAbsoluteWidth(550);
            documento.add(imagen);
            Paragraph paragraph =new Paragraph();

            documento.add(paragraph);
            documento.add(new Paragraph("\n Renta de "+maquinapdf +"\n\n"));
            PdfPTable tabla1 = new PdfPTable(4);
            tabla1.addCell("Maquina");
            tabla1.addCell("Costo base");
            tabla1.addCell("Dias");
            tabla1.addCell("Costo total");
            tabla1.addCell(maquinapdf);
            tabla1.addCell(preciobasepdf);
            tabla1.addCell(diaslaboralespdf+"");
            tabla1.addCell(costoTotalMaquina+"");
            documento.add(tabla1);
            documento.add(new Paragraph( "\n\n"));
            // Insertamos una tabla
            if(listaAccesoriosGenerar.isEmpty()){
                documento.add(new Paragraph("\nHerramientas de "+maquinapdf+" por rentar " +(listaAccesoriosGenerar.size())+"\n"));
            }else{

            if(listaAccesoriosGenerar.size()==1){
                PdfPTable tabla = new PdfPTable(4);
                documento.add(new Paragraph("\nHerramientas de "+maquinapdf+" por rentar " +(listaAccesoriosGenerar.size())+"\n\n"));
                tabla.addCell("Accesorio");
                tabla.addCell("Costo extra");
                tabla.addCell("Dias");
                tabla.addCell("Total");
                tabla.addCell(listaAccesoriosGenerar.get(0).getAccesorio());
                tabla.addCell(listaAccesoriosGenerar.get(0).getCostoExtra()+"");
                tabla.addCell(diaslaboralespdf+"");
                tabla.addCell((listaAccesoriosGenerar.get(0).getCostoExtra()*diaslaboralespdf)+"");
                documento.add(tabla);
            }else{
                documento.add(new Paragraph("\nHerramientas de "+maquinapdf+" por rentar " +(listaAccesoriosGenerar.size())+"\n\n"));
                PdfPTable tablaA = new PdfPTable(4);
                tablaA.addCell("Accesorio");
                tablaA.addCell("Costo extra");
                tablaA.addCell("Dias");
                tablaA.addCell("Total");
            for (int i = 0; i < listaAccesoriosGenerar.size(); i++) {
                listaAccesoriosGenerar2.add(listaAccesoriosGenerar.get(i).getIdAccesorio()+"");
                tablaA.addCell(listaAccesoriosGenerar.get(i).getAccesorio());
                tablaA.addCell(listaAccesoriosGenerar.get(i).getCostoExtra()+"");
                tablaA.addCell(diaslaboralespdf+"");
                tablaA.addCell((listaAccesoriosGenerar.get(i).getCostoExtra()*diaslaboralespdf)+"");
            }
            Paragraph paragraph1=new Paragraph();
            paragraph1.add("Total por herrmientas de "+maquinapdf+" :"+costoTotalAccesorios);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);
                documento.add(tablaA);
                documento.add(paragraph1);
            }
            }
            if (operadorpdf == 1) {
                documento.add(new Paragraph("\nSueldo de operador: "+sueldoOperador+"\n\n"));
            }
            if(combustiblepdf==1){
                documento.add(new Paragraph("\nCombustible      litros: "+CantidadCombustible+"\n"));
                documento.add(new Paragraph("\nCosto por combustible:  "+CostoCombustible+"\n"));
            }

            documento.add(new Paragraph("\nTotal:  "+totalCotizacion+"\n"));

            Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.pie_pagina);
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
            Image footer = Image.getInstance(stream2.toByteArray());
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setAbsolutePosition(50,0);
            footer.scaleAbsoluteHeight(100);
            footer.scaleAbsoluteWidth(550);
            documento.add(footer);
            imagen.setAbsolutePosition(150f, 650f);
        } catch(DocumentException e) {
        } catch(IOException e) {
        } finally {
            documento.close();
        }
    }
    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();
        File fichero = null;
        if(ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }
        return fichero;
    }

    public File getRuta() {
        File ruta = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), NOMBRE_DIRECTORIO);
            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                        return null;
                    }
                }
            }
        }
        return ruta;
    }

    private void mostrar(ArrayList id) {
        listaver = findViewById(R.id.listaAccesoriosGenerar);
        listaAccesoriosGenerar = new ControladorDB(this).leerAccesorio(id);
        listaAccesoriosGenerar2 = new ArrayList<String>();

        for (int i = 0; i < listaAccesoriosGenerar.size(); i++) {
            listaAccesoriosGenerar2.add(listaAccesoriosGenerar.get(i).getIdAccesorio() + "");
            //precios.add(listaAccesoriosGenerar.getCostoExtra()+"");
        }
        MyadapterAccesoriosPdf myAdapter = new MyadapterAccesoriosPdf(this, R.layout.support_simple_spinner_dropdown_item, listaAccesoriosGenerar);
        listaver.setAdapter(myAdapter);

    }

    private void mostrarTotales(ArrayList id, int dias) {
        listaTotales = findViewById(R.id.listaAccesoriosGenerarPrecios);
        listaAccesoriosTotales = new ControladorDB(this).leerPreciosAccesorio(id, dias);
        listaAccesoriosTotales2 = new ArrayList<String>();

        for (int i = 0; i < listaAccesoriosTotales.size(); i++) {
            listaAccesoriosTotales2.add(listaAccesoriosTotales.get(i).getIdAccesorio() + "");
            //precios.add(listaAccesoriosGenerar.getCostoExtra()+"");
        }
        MyadapterTotalesAccesorios myAdapter = new MyadapterTotalesAccesorios(this, R.layout.support_simple_spinner_dropdown_item, listaAccesoriosTotales);
        listaTotales.setAdapter(myAdapter);

    }
}

