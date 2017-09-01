package com.jeferson.pizzaapp;

/**
 * Created by Usuario on 31/08/2017.
 */

public class Variables {

    private String tipo_pizza,tipo_maza,tipo_extra;
    private int precio, precio_extra;

    public Variables() {
        this.tipo_pizza = tipo_pizza;
        this.tipo_maza = tipo_maza;
        this.tipo_extra = tipo_extra;
        this.precio = precio;
        this.precio_extra = precio_extra;
    }

    public String getTipo_pizza() {
        return tipo_pizza;
    }

    public void setTipo_pizza(String tipo_pizza) {
        this.tipo_pizza = tipo_pizza;
    }

    public String getTipo_maza() {
        return tipo_maza;
    }

    public void setTipo_maza(String tipo_maza) {
        this.tipo_maza = tipo_maza;
    }

    public String getTipo_extra() {
        return tipo_extra;
    }

    public void setTipo_extra(String tipo_extra) {
        this.tipo_extra = tipo_extra;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPrecio_extra() {
        return precio_extra;
    }

    public void setPrecio_extra(int precio_extra) {
        this.precio_extra = precio_extra;
    }

}
