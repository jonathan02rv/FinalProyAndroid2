package com.example.jhona.clothesshopapp.storage.request.entity;

import java.io.Serializable;

/**
 * Created by jhonahome on 24/11/17.
 */

public class ProductEntity implements Serializable {
    private int id;
    private String brand;
    private String nombre;
    private String cantidad;
    private String total;
    private  int estado;

    public ProductEntity(){}

    public ProductEntity(int id, String brand, String name, String cantidad, String total) {
        this.id = id;
        this.brand = brand;
        this.nombre = name;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = 0;
    }

    public ProductEntity(String brand, String name, String cantidad, String total) {
        this.brand = brand;
        this.nombre = name;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
