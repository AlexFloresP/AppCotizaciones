package com.alex.mycot;

public class Tiempo {
    public Tiempo(int id,String tiempo){
        this.id=id;
        this.tiempo=tiempo;

    }
    public Tiempo(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    int id;
    String tiempo;
}
