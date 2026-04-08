package com.example.tienditademo.models;
//Esta clase es instanciada (se crean objetos de ella) en el Repository
//cuando lee el archivo y en los Controllers cuando el usuario llena un formulario.
//Viaja a traves del Service como mensajero de datos.

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    // Es el metodo que se usa para crear un nuevo producto
    // Cuando otra clase quiere crear un producto, debe entregarle estos 5 datos.
    public Producto(String id, String nombre, double precio, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getCategoria() {
        return categoria;
    }

}
