package com.alex.mycot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ControladorDB extends ManejadorDB {
    public ControladorDB(Context context) {
        super(context);
    }
    public boolean guardarMaquina(Maquinas maquinas){
        ContentValues values = new ContentValues();
        values.put("maquina",maquinas.maquina);
        values.put("precioBase",maquinas.precioBase);
        values.put("idTipoMaquina",maquinas.idTipoMaquina);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean MaquinaGuardada=db.insert("Maquinas",null,values)>0;
        db.close();
        return MaquinaGuardada;
    }
    public boolean guardarCotizacion(Cotizacion cotizacion){
      //  (idCotizacion INTEGER PRIMARY KEY AUTOINCREMENT,idMaquina INT,costoMaquina INT,accesorios TEXT, totalAccesorios INT," +
        //"totalDias INT,operador INT,sueldoOperador INT,combustible INT, litrosCombustible INT,costoCombustible int, totalCotizacio INT
        ContentValues values = new ContentValues();
        values.put("idMaquina",cotizacion.idMaquina);
        values.put("costoMaquina",cotizacion.costoMaquina);
        values.put("accesorios",cotizacion.accesoriosCotizados);
        values.put("totalAccesorios",cotizacion.totalAccesorios);
        values.put("totalDias",cotizacion.dias);
        values.put("operador",cotizacion.operador);
        values.put("combustible",cotizacion.combustible);
        values.put("litrosCombustible",cotizacion.litrosCombustible);
        values.put("costoCombustible",cotizacion.costoCombustible);
        values.put("totalCotizacio",cotizacion.CostoTotalCotizacion);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean CotizacionGuardada=db.insert("cotizacion",null,values)>0;
        db.close();
        return CotizacionGuardada;
    }

    public boolean guardarCombustible(Combustible combustible){
        ContentValues values = new ContentValues();
        values.put("combustible","diesel");
        values.put("precio",combustible.precioCombustible);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean CombustibleGuardado=db.insert("Combustible",null,values)>0;
        db.close();
        return CombustibleGuardado;
    }
    public boolean guardarOperador(Operador operador){
        ContentValues values = new ContentValues();
        values.put("operador","operador");
        values.put("sueldo",operador.sueldo);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean OperadorGuardado=db.insert("Operador",null,values)>0;
        db.close();
        return OperadorGuardado;
    }
    public boolean guardarFlete(Flete flete){

        ContentValues values = new ContentValues();
        values.put("Flete","local");
        values.put("precioBase",flete.precioBase);
        values.put("costoExtra",flete.precioExtra);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean FleteGuardado=db.insert("Fletes",null,values)>0;
        db.close();
        return FleteGuardado;
    }
    public boolean eliminarAccesorio(int id){

        SQLiteDatabase db= this.getWritableDatabase();
        boolean AccesorioEliminado = db.delete("Accesorios","idAccesorios = "+id,null)>0;
        db.close();
        return AccesorioEliminado;
    }
    public boolean eliminarMaquina(int id){

        SQLiteDatabase db= this.getWritableDatabase();
        boolean MaquinaEliminada = db.delete("Maquinas","idMaquina = "+id,null)>0;
        db.close();
        return MaquinaEliminada;
    }
    public List<Combustible>leerCombustible(){
        List<Combustible> listadoComb =new ArrayList<>();

        String sql ="SELECT * FROM Combustible";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                int idCombustible =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idCombustible")));
                String Combustible=cursor.getString(cursor.getColumnIndex("combustible"));
                int precioCom=Integer.parseInt(cursor.getString(cursor.getColumnIndex("precio")));

                Combustible combustible=new Combustible();
                combustible.idCombustible=idCombustible;
                combustible.combustible=Combustible;
                combustible.precioCombustible=precioCom;
                listadoComb.add(combustible);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return listadoComb;
    }
    public List<Flete>leerFletes(){
        List<Flete> listadoFlete =new ArrayList<>();
        String sql ="SELECT * FROM Fletes";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                int idFlete =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idFlete")));
                String fletes=cursor.getString(cursor.getColumnIndex("Flete"));
                int precioBase=Integer.parseInt(cursor.getString(cursor.getColumnIndex("precioBase")));
                int precioExtra=Integer.parseInt(cursor.getString(cursor.getColumnIndex("costoExtra")));

                Flete flete =new Flete();
                flete.id=idFlete;
                flete.flete=fletes;
                flete.precioBase=precioBase;
                flete.precioExtra=precioExtra;
                listadoFlete.add(flete);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listadoFlete;
    }

    public List<Operador>leerOperador(){
        List<Operador> listadoOperador =new ArrayList<>();

        String sql ="SELECT * FROM Operador";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                int idOperador =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idOperador")));
                String Operador=cursor.getString(cursor.getColumnIndex("operador"));
                int Sueldo=Integer.parseInt(cursor.getString(cursor.getColumnIndex("sueldo")));

                Operador operador=new Operador();
                operador.id=idOperador;
                operador.operador=Operador;
                operador.sueldo=Sueldo;
                listadoOperador.add(operador);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return listadoOperador;
    }
    public boolean ActualizarMaquina(Maquinas maquinas){

        ContentValues values = new ContentValues();
        values.put("maquina",maquinas.maquina);
        values.put("precioBase",maquinas.precioBase);
        values.put("idTipoMaquina",maquinas.idTipoMaquina);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean MaquinaActualizada = db.update("Maquinas",values,"idMaquina = "+maquinas.idMaquina,null)>0;
        db.close();
        return MaquinaActualizada;
    }
    public boolean ActualizarCombustible(Combustible combustible){

        ContentValues values = new ContentValues();
        values.put("combustible",combustible.combustible);
        values.put("precio",combustible.precioCombustible);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean CombustibleActualizado = db.update("Combustible",values,"idCombustible = "+combustible.idCombustible,null)>0;
        db.close();
        return CombustibleActualizado;
    }
    String TablaFletes="CREATE TABLE Fletes"+
            "(idFlete INTEGER PRIMARY KEY AUTOINCREMENT,Flete TEXT,precioBase INT,costoExtra INT)";
    public boolean ActualizarFlete(Flete flete){

        ContentValues values = new ContentValues();

        values.put("precioBase",flete.precioBase);
        values.put("costoExtra",flete.precioExtra);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean FleteActualizado = db.update("Fletes",values,"idOperador = "+flete.id,null)>0;
        db.close();
        return FleteActualizado;
    }
    public boolean ActualizarOperador(Operador operador){

        ContentValues values = new ContentValues();

        values.put("sueldo",operador.sueldo);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean OperadorActualizado = db.update("Operador",values,"idOperador = "+operador.id,null)>0;
        db.close();
        return OperadorActualizado;
    }
    public boolean ActualizarAccesorio(Accesorios accesorios){

        ContentValues values = new ContentValues();
        values.put("accesorio",accesorios.getAccesorio());
        values.put("costoExtra",accesorios.getCostoExtra());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean AccesorioActualizado = db.update("Accesorios",values,"idAccesorios = "+accesorios.idAccesorio,null)>0;
        db.close();
        return AccesorioActualizado;
    }
    public boolean guardarAccesorio(Accesorios accesorios){

        ContentValues values = new ContentValues();
        values.put("accesorio",accesorios.accesorio);
        values.put("idMaquina",accesorios.idMaquina);
        values.put("costoExtra",accesorios.costoExtra);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean AccesorioGuardado=db.insert("Accesorios",null,values)>0;
        db.close();
        return AccesorioGuardado;
    }
    public int obtenerId(String nombre){
        String sql ="SELECT idMaquina FROM Maquinas WHERE maquina="+"'"+nombre+"'";
        //String sql ="SELECT * FROM " + "sqlite_sequence";
        SQLiteDatabase db =this.getWritableDatabase();
       Cursor cursor=db.rawQuery(sql,null);
        //int resultado =db.rawQuery(sql,null).getInt()-1;
        cursor.moveToLast();
//        int resultado= Integer.parseInt(String.valueOf(cursor));
        int id = cursor.getInt(0);
        db.close();
        return id;
    }
    public int obtenerPrecioBaseFlete(){
        String sql ="SELECT precioBase FROM Fletes where idFlete=1";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToLast();
        int precioBase = cursor.getInt(0);
        db.close();
        return precioBase;
    }
    public int obtenerPrecioExtraFlete(){
        String sql ="SELECT costoExtra FROM Fletes where idFlete=1";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToLast();
        int precioExtra = cursor.getInt(0);
        db.close();
        return precioExtra;
    }


    public ArrayList<Maquinas>leer(){
        ArrayList<Maquinas> listadoMaquinas =new ArrayList<>();
        String sql ="SELECT * FROM Maquinas ORDER BY idMaquina";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                int idMaquina =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idMaquina")));
                String maquina=cursor.getString(cursor.getColumnIndex("maquina"));
                int precioBase =Integer.parseInt(cursor.getString(cursor.getColumnIndex("precioBase")));
                int idTipoMaquina=Integer.parseInt(cursor.getString(cursor.getColumnIndex("idTipoMaquina")));
                Maquinas maquinas=new Maquinas();
                maquinas.idMaquina=idMaquina;
                maquinas.maquina=maquina;
                maquinas.precioBase=precioBase;
                maquinas.idTipoMaquina=idTipoMaquina;
                listadoMaquinas.add(maquinas);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listadoMaquinas;
    }
    public ArrayList<Maquinas>leerSeleccion(int idTipoMaqu){
        ArrayList<Maquinas> listadoMaquinasSelecion=new ArrayList<>();
        String sql ="SELECT * FROM Maquinas WHERE idTipoMaquina="+"'"+idTipoMaqu+"'";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                int idMaquina =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idMaquina")));
                String maquina=cursor.getString(cursor.getColumnIndex("maquina"));
                int precioBase =Integer.parseInt(cursor.getString(cursor.getColumnIndex("precioBase")));
                int idTipoMaquina=Integer.parseInt(cursor.getString(cursor.getColumnIndex("idTipoMaquina")));
                Maquinas maquinas=new Maquinas();
                maquinas.idMaquina=idMaquina;
                maquinas.maquina=maquina;
                maquinas.precioBase=precioBase;
                maquinas.idTipoMaquina=idTipoMaquina;
                listadoMaquinasSelecion.add(maquinas);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listadoMaquinasSelecion;
    }
    public ArrayList<Accesorios>leerAccesorios(int id){
        ArrayList<Accesorios> listadoAccesorios =new ArrayList<>();
        String sql ="SELECT * FROM Accesorios where idMaquina="+id;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                int idAccesorio =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idAccesorios")));
                String accesorio=cursor.getString(cursor.getColumnIndex("accesorio"));
                int idMaquina =Integer.parseInt(cursor.getString(cursor.getColumnIndex("idMaquina")));
                int precioExtra =Integer.parseInt(cursor.getString(cursor.getColumnIndex("costoExtra")));
                Accesorios accesorios=new Accesorios();
                accesorios.idAccesorio=idAccesorio;
                accesorios.accesorio=accesorio;
                accesorios.costoExtra=precioExtra;
                listadoAccesorios.add(accesorios);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listadoAccesorios;
    }
    public int contador(){
        SQLiteDatabase db =this.getWritableDatabase();
        String sql ="SELECT * FROM Combustible";
        int resultado =db.rawQuery(sql,null).getCount();
        db.close();
        return resultado;
    }
    public ArrayList<Accesorios>leerAccesorio(ArrayList id){
        ArrayList<Accesorios> Accesorio =new ArrayList<>();
       // int idAc;
        for (int i = 0; i < id.size(); i++) {
            int idAc=(int)id.get(i);
            String sql = "SELECT * FROM Accesorios where idAccesorios=" + idAc;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    int idAccesorio = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idAccesorios")));
                    String accesorio = cursor.getString(cursor.getColumnIndex("accesorio"));
                    int idMaquina = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idMaquina")));
                    int precioExtra = Integer.parseInt(cursor.getString(cursor.getColumnIndex("costoExtra")));
                    Accesorios accesorios = new Accesorios();
                    accesorios.idAccesorio = idAccesorio;
                    accesorios.accesorio = accesorio;
                    accesorios.costoExtra = precioExtra;
                    Accesorio.add(accesorios);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }

        return Accesorio;
    }
    public ArrayList<Accesorios>leerPreciosAccesorio(ArrayList id,int dias){
        ArrayList<Accesorios> PrecioAccesorio =new ArrayList<>();
        // int idAc;
        for (int i = 0; i < id.size(); i++) {
            int idAc=(int)id.get(i);
            String sql = "SELECT * FROM Accesorios where idAccesorios=" + idAc;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    int idAccesorio = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idAccesorios")));
                    String accesorio = cursor.getString(cursor.getColumnIndex("accesorio"));
                    int idMaquina = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idMaquina")));
                    int precioExtra = Integer.parseInt(cursor.getString(cursor.getColumnIndex("costoExtra")));
                    int total=precioExtra*dias;
                    Accesorios accesorios = new Accesorios();
                    accesorios.idAccesorio = idAccesorio;
                    accesorios.accesorio = accesorio;
                    accesorios.costoExtra = total;
                    PrecioAccesorio.add(accesorios);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }

        return PrecioAccesorio;
    }
    public int contadorOperador(){
        SQLiteDatabase db =this.getWritableDatabase();
        String sql ="SELECT * FROM Operador";
        int resultado =db.rawQuery(sql,null).getCount();
        db.close();
        return resultado;
    }
    public int contadorFlete(){
        SQLiteDatabase db =this.getWritableDatabase();
        String sql ="SELECT * FROM Fletes";
        int resultado =db.rawQuery(sql,null).getCount();
        db.close();
        return resultado;
    }
    public int precioOperador(){
        SQLiteDatabase db =this.getWritableDatabase();
        String sql ="SELECT sueldo FROM Operador";
        Cursor cursor=db.rawQuery(sql,null);
        int Sueldo=0;
        if(cursor.moveToFirst()){
            do {
                 Sueldo=Integer.parseInt(cursor.getString(cursor.getColumnIndex("sueldo")));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Sueldo;
    }
    public int CostoCombustible(){
        SQLiteDatabase db =this.getWritableDatabase();
        String sql ="SELECT precio FROM Combustible";
        Cursor cursor=db.rawQuery(sql,null);
        int precio=0;
        if(cursor.moveToFirst()){
            do {
                precio=Integer.parseInt(cursor.getString(cursor.getColumnIndex("precio")));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return precio;
    }

    public int CostosAccesorios(ArrayList id){
        int precio=0;
        for (int i = 0; i < id.size(); i++) {
            int idAc=(int)id.get(i);
            String sql = "SELECT costoExtra FROM Accesorios where idAccesorios=" + idAc;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    int precioExtra = Integer.parseInt(cursor.getString(cursor.getColumnIndex("costoExtra")));
                    precio=precio+precioExtra;
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }
        return precio;
    }
    public int contadorMaquinas(){
        SQLiteDatabase db =this.getWritableDatabase();
        String sql ="SELECT * FROM Maquinas";
        int resultado =db.rawQuery(sql,null).getCount();
        db.close();
        return resultado;
    }
    public int contadorAccesorios(int id){
        SQLiteDatabase db =this.getWritableDatabase();

        String sql ="SELECT * FROM Accesorios WHERE idMaquina="+"'"+id+"'";
        int resultado =db.rawQuery(sql,null).getCount();
        db.close();
        return resultado;
    }


}




