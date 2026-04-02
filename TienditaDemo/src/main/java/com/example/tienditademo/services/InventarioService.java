package com.example.tienditademo.services;
import com.example.tienditademo.models.Producto;
import com.example.tienditademo.repositories.InventarioRepository;
import java.io.IOException;
import java.util.List;

public class InventarioService {
    InventarioRepository repo = new InventarioRepository();
    public List<Producto>obtenerTodos() throws IOException{
        return repo.cargarTodos();
    }

    public void agregarProducto(String id, String nombre, String precioStr, String stockStr, String categoria) throws IOException{
        validate(id, nombre, precioStr, stockStr, categoria);

        double precio = Double.parseDouble(precioStr);
        int stock = Integer.parseInt(stockStr);
        Producto nuevo = new Producto(id, nombre, precio, stock, categoria);
        repo.guardarNuevo(nuevo);
    }

    private void validate(String id, String nombre, String precioStr, String stockStr, String categoria) throws IOException{
        if(id==null || id.isBlank()) throw new IllegalArgumentException("El código no puede estar vacío");
        if(nombre.isBlank() || nombre.length()<3) throw new IllegalArgumentException("El código no puede estar vacío");

        List<Producto> existences=repo.cargarTodos();
        for (Producto p : existences){
            if(p.getId().equals(id.trim())){
                throw new IllegalArgumentException("El código de producto ya existe");
            }
        }
        try {
            double precio = Double.parseDouble(precioStr);
            if (precio<=0) throw new IllegalArgumentException("El precio debe ser mayor a 0");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El precio debe ser numerico");
        }
        try {
            int stock = Integer.parseInt(stockStr);
            if(stock<0) throw new IllegalArgumentException("El stock no puede ser negativo");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El stock debe ser un número entero");
        }
    }
}
