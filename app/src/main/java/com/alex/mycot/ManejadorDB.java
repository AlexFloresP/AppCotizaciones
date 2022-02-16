package com.alex.mycot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManejadorDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    protected static final String DATABASE_NAME="cotizacion";


    public ManejadorDB(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TablaMaquinas="CREATE TABLE Maquinas"+
                "(idMaquina INTEGER PRIMARY KEY AUTOINCREMENT,maquina TEXT,precioBase INT,idTipoMaquina INT," +
                "foreign key(idTipoMaquina)references Tipomaquina(idTipoMaquina))";
        String TablaCotizacion="CREATE TABLE cotizacion"+
                "(idCotizacion INTEGER PRIMARY KEY AUTOINCREMENT,idMaquina INT,costoMaquina INT,accesorios TEXT, totalAccesorios INT," +
                "totalDias INT,operador INT,sueldoOperador INT,combustible INT, litrosCombustible INT,costoCombustible int, totalCotizacio INT," +
                "foreign key(idMaquina)references Maquinas(idMaquina))";
        String TablaAccesorios="CREATE TABLE Accesorios"+
                "(idAccesorios INTEGER PRIMARY KEY AUTOINCREMENT,accesorio TEXT,idMaquina INT," +
                "costoExtra INT,foreign key(idMaquina)references Maquinas (idMaquinas))";
        String TablaCostosOperador="CREATE TABLE Operador"+
                "(idOperador INTEGER PRIMARY KEY AUTOINCREMENT,operador TEXT,sueldo INT)";
        String TablaCostoCombustible="CREATE TABLE Combustible"+
                "(idCombustible INTEGER PRIMARY KEY AUTOINCREMENT,combustible TEXT,precio INT)";
        String TablaFletes="CREATE TABLE Fletes"+
                "(idFlete INTEGER PRIMARY KEY AUTOINCREMENT,Flete TEXT,precioBase INT,costoExtra INT)";


        String TablaTiempos="CREATE TABLE Tiempos"+
                "(idTiempo INTEGER PRIMARY KEY AUTOINCREMENT,tiempo TEXT)";
        String TablaTipoMaquina="CREATE TABLE TipoMaquina"+
                "(idTipoMaquina INTEGER PRIMARY KEY AUTOINCREMENT,tipo TEXT)";
        String insertar="insert into Tipomaquina values(1,'maquinaria para construccion'),(2,'fletes y viajes')";

        sqLiteDatabase.execSQL(TablaFletes);
        sqLiteDatabase.execSQL(TablaMaquinas);
        sqLiteDatabase.execSQL(TablaCotizacion);
        sqLiteDatabase.execSQL(TablaAccesorios);
        sqLiteDatabase.execSQL(TablaCostoCombustible);
        sqLiteDatabase.execSQL(TablaCostosOperador);
        sqLiteDatabase.execSQL(TablaTiempos);
        sqLiteDatabase.execSQL(TablaTipoMaquina);
        sqLiteDatabase.execSQL(insertar);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String sql ="DROP TABLE IF EXISTS Maquinas";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
