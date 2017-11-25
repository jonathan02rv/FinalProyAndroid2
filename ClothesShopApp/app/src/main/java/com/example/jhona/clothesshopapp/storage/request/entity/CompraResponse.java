package com.example.jhona.clothesshopapp.storage.request.entity;

/**
 * Created by jhonahome on 24/11/17.
 */

public class CompraResponse {
    private String objectId;
    private String fecha;
    private String cliente;
    private String monto;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
