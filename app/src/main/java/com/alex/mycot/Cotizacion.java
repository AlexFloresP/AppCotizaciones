package com.alex.mycot;

public class Cotizacion {

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getCostoMaquina() {
        return costoMaquina;
    }

    public void setCostoMaquina(int costoMaquina) {
        this.costoMaquina = costoMaquina;
    }

    public String getAccesoriosCotizados() {
        return accesoriosCotizados;
    }

    public void setAccesoriosCotizados(String accesoriosCotizados) {
        this.accesoriosCotizados = accesoriosCotizados;
    }

    public int getTotalAccesorios() {
        return totalAccesorios;
    }

    public void setTotalAccesorios(int totalAccesorios) {
        this.totalAccesorios = totalAccesorios;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getOperador() {
        return operador;
    }

    public void setOperador(int operador) {
        this.operador = operador;
    }

    public int getSueldoOperador() {
        return SueldoOperador;
    }

    public void setSueldoOperador(int sueldoOperador) {
        SueldoOperador = sueldoOperador;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getLitrosCombustible() {
        return litrosCombustible;
    }

    public void setLitrosCombustible(int litrosCombustible) {
        this.litrosCombustible = litrosCombustible;
    }

    public int getCostoCombustible() {
        return costoCombustible;
    }

    public void setCostoCombustible(int costoCombustible) {
        this.costoCombustible = costoCombustible;
    }

    public int getCostoTotalCotizacion() {
        return CostoTotalCotizacion;
    }

    public void setCostoTotalCotizacion(int costoTotalCotizacion) {
        CostoTotalCotizacion = costoTotalCotizacion;
    }

    int idCotizacion;
    int idMaquina;
    int costoMaquina;
    String accesoriosCotizados;
    int totalAccesorios;
    int dias;
    int operador;
    int SueldoOperador;
    int combustible;
    int litrosCombustible;
    int costoCombustible;
    int CostoTotalCotizacion;
}
