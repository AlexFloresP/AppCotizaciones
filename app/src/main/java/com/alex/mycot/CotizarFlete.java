package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CotizarFlete extends AppCompatActivity {
    String texto,precio;
    String NOMBRE_DIRECTORIO = "MisCotizaciones";
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strDate = sdf.format(c.getTime());
    String NOMBRE_DOCUMENTO = "cotizacionFleteFueraZona" + strDate + ".pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar_flete);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1000);
        }
        final EditText EdtfleteFuera=findViewById(R.id.EdtFleteFueraZona);
        final EditText EdfCostoFlete=findViewById(R.id.EdtCostoFleteFueraZona);
        Button btnFueraZona=findViewById(R.id.btnCotizacionFleteFueraZona);


        btnFueraZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EdtfleteFuera.getText().toString().equals("")||EdfCostoFlete.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    precio=EdfCostoFlete.getText().toString();
                    texto=EdtfleteFuera.getText().toString();
                    crearPDF();
                    Toast.makeText(getApplicationContext(),"Cotizacion generada",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
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
            documento.add(new Paragraph("\n Fecha:   "+strDate));
            documento.add(new Paragraph("\n Cotizacion de flete por concepto de:\n"));
            documento.add(new Paragraph("\n"+texto+"\n"));
            documento.add(new Paragraph("\nCosto total:  "+precio+"\n"));
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
}