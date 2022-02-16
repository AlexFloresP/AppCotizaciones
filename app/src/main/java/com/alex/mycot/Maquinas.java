package com.alex.mycot;

public class Maquinas {

public Maquinas(int idMaquina,String maquina,int precioBase,int idTipoMaquina){
this.idMaquina=idMaquina;
this.maquina=maquina;
this.precioBase=precioBase;
this.idTipoMaquina=idTipoMaquina;
}
    int idTipoMaquina;

    public Maquinas() {

    }
    public int getIdTipoMaquina() {
        return idTipoMaquina;
    }

    public void setIdTipoMaquina(int idTipoMaquina) {
        this.idTipoMaquina = idTipoMaquina;
    }
    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    int idMaquina;
    String maquina;

    public int getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    int precioBase;
}
