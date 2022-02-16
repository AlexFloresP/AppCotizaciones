package com.alex.mycot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SeleccionTiempoOperador extends AppCompatActivity {
    Button btnInicio,btnFinal,btnverpdf;
    EditText edtInicio,edtFianl,edtCombustible;
    int diaI,mesI,anioI,diaF,mesF,anioF,diaslab;
    Date inicio ;
    String in;
    Date fin;
    int operador=0;
    int combustible=0;
    int cantidadCombustible=0;
    List<Date> listaFechasNoLaborables;
    CheckBox oper,combus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_tiempo_operador);
        final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        final String maquina= getIntent().getStringExtra("maquina");
        final String IDMaquina=(getIntent().getExtras().getInt("idMaquina")+"");
        final String PrecioBase=(getIntent().getExtras().getInt("CostoBase")+"");
        final int costoBaseMaq=Integer.parseInt(PrecioBase);
        final String idTipo=(getIntent().getExtras().getInt("Tipo")+"");
        final int idmaqu=Integer.parseInt(IDMaquina);
        final ArrayList lista = (ArrayList) getIntent().getSerializableExtra("miLista");
        for(int index=0; index < lista.size(); index++){
            System.out.println(lista.get(index));

        }

       //final SimpleCalendarFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");


        btnInicio =findViewById(R.id.buttonFechaInicial);
        btnFinal=findViewById(R.id.buttonFechaFinal);
        edtInicio=findViewById(R.id.EdtFechaInicial);
        edtFianl=findViewById(R.id.EdtFechaFinal);
        oper=findViewById(R.id.CheckOperador);
        combus=findViewById(R.id.checkCombustible);
        btnverpdf=findViewById(R.id.buttonVistapdf);
        edtCombustible=findViewById(R.id.EdtCantidadCombustible);
        edtCombustible.setEnabled(false);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar= Calendar.getInstance();
                diaI=calendar.get(Calendar.DAY_OF_MONTH);
                mesI=calendar.get(Calendar.MONTH);
                anioI=calendar.get(Calendar.YEAR);

              //  calendar.add(Calendar.DATE, 1);
                String inActiveDate = null;
              //  inActiveDate = format1.format(dateI);
                //                inicio.getDate();
               // System.out.println("Lafecha es"+inicio);
                final DatePickerDialog datePickerDialog=new DatePickerDialog(SeleccionTiempoOperador.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        edtInicio.setText(day+"-"+(month+1)+"-"+ year);
                       final String fecha=year+"-"+(month+1)+"-"+day;
                        try {
                            inicio=format1.parse(fecha);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        System.out.println(inicio);
                    }
                },anioI,mesI,diaI);
                datePickerDialog.show();


            }
        });


        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar= Calendar.getInstance();
                diaF=calendar.get(Calendar.DAY_OF_MONTH);
                mesF=calendar.get(Calendar.MONTH);
                anioF=calendar.get(Calendar.YEAR);
                calendar.add(Calendar.DATE, 1);

               final Date datef = calendar.getTime();

                String inActiveDate = null;
                inActiveDate = format1.format(datef);

                System.out.println("fecha fin "+inActiveDate );


                DatePickerDialog datePickerDialog=new DatePickerDialog(SeleccionTiempoOperador.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        edtFianl.setText(day+"-"+(month+1)+"-"+ year);
                       final  String fechafin=year+"-"+(month+1)+"-"+day;
                        try {
                            fin=format1.parse(fechafin);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        System.out.println(fin);
                        diaslab=getWorkingDaysBetweenTwoDates(inicio,fin);
                        Toast.makeText(getApplicationContext(),"Dias laborales: "+diaslab,Toast.LENGTH_SHORT).show();
                        System.out.println("dias laborales=" +diaslab);
                    }
                },anioF,mesF,diaI);
                datePickerDialog.show();
            }
        });

        oper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(oper.isChecked()){
                    operador=1;
                }else{
                    operador=0;
                }
            }
        });
        combus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (combus.isChecked()){
                    combustible=1;
                    edtCombustible.setEnabled(true);
                    edtCombustible.setText("");
                }else {
                    combustible=0;
                    edtCombustible.setEnabled(false);
                    edtCombustible.setText("0000");
                }
            }
        });


        btnverpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtCombustible.getText().toString().equals("")||inicio==null||fin==null){
                    Toast.makeText(getApplicationContext(),"No ha seleccionado las fechas o no ha ingresado los litros por dia",Toast.LENGTH_SHORT).show();
                }else {


                    Cotizacion cotizacion = new Cotizacion();
                    cotizacion.idMaquina = idmaqu;
                    cotizacion.costoMaquina = costoBaseMaq;
                    // cotizacion.costoMaquina=

                    // boolean MaquinaGuardada = new ControladorDB(context).guardarMaquina(maquinas);

                    String CanCom = edtCombustible.getText().toString();
                    cantidadCombustible = Integer.parseInt(CanCom);
                    Intent i = new Intent(getApplicationContext(), GenerarPdf.class);
                    i.putExtra("miListaAC", lista);
                    i.putExtra("idMaquina", idmaqu);
                    i.putExtra("maquina", maquina);
                    i.putExtra("CostoBase", costoBaseMaq);
                    i.putExtra("tipo", idTipo);
                    i.putExtra("combustible", combustible);
                    i.putExtra("operador", operador);
                    i.putExtra("fechainicio", edtInicio.getText().toString());
                    i.putExtra("fechafin", edtFianl.getText().toString());
                    i.putExtra("dias", diaslab);
                    i.putExtra("cantidadCombustible", cantidadCombustible);
                    startActivity(i);

                    for (int index = 0; index < lista.size(); index++) {
                        System.out.println(lista.get(index));

                    }
                    System.out.println("combustible " + combustible + "Operador: " + operador);
                    finish();
                }

            }
        });




    }

    public int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
     startCal.setTime(startDate);
     Calendar endCal = Calendar.getInstance();
     endCal.setTime(endDate);
     int workDays = 1; //Return 0 if start and end are the same
         if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
             return 1;
          } if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
             startCal.setTime(endDate);
             endCal.setTime(startDate);
         } do { //excluding start date
              startCal.add(Calendar.DAY_OF_MONTH, 1);
              if ( startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                  ++workDays;
              }
         } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
         return workDays;
         }




    public int diasHabiles(Calendar fechaInicial, Calendar fechaFinal, List<Date> listaFechasNoLaborables) {
        int diffDays = 0;
        boolean diaHabil = false;
        //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {

            if (!listaFechasNoLaborables.isEmpty()) {
                for (Date date : listaFechasNoLaborables) {
                    Date fechaNoLaborablecalendar = fechaInicial.getTime();
                    //si el dia de la semana de la fecha minima es diferente de sabado o domingo
                    if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && !fechaNoLaborablecalendar.equals(date)) {
                        //se aumentan los dias de diferencia entre min y max
                        diaHabil = true;
                    } else {
                        diaHabil = false;
                        break;
                    }
                }
            } else {
                if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    //se aumentan los dias de diferencia entre min y max
                    diffDays++;
                }
            }
            if (diaHabil == true) {
                diffDays++;
            }
            //se suma 1 dia para hacer la validacion del siguiente dia.
            fechaInicial.add(Calendar.DATE, 1);
        }
        return diffDays;
    }

    }
