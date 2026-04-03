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
    public void eliminarProducto(String id) throws IOException{
        if( id==null || id.isBlank()){
            throw new IllegalArgumentException("Ingresa el ID del producto a eliminar");
        }
        List<Producto> lista = repo.cargarTodos();
        boolean cBorro = lista.removeIf(p -> p.getId().equals(id.trim()));
        if(!cBorro){
            throw new IllegalArgumentException("No se encontró ningun producto con el ID: " + id);
        }
        repo.sobrescribirArchivo(lista);
    }
    public Producto buscarProductoID(String id) throws IOException{
        if(id==null || id.isBlank()){
            throw new IllegalArgumentException("Ingresa un ID para buscar");
        }
        List<Producto> lista = repo.cargarTodos();
        for (Producto p : lista){
            if(p.getId().equals(id.trim())){
                return p;
            }
        }
        throw new IllegalArgumentException("No se encontró ningun producto con el ID: " + id);
    }
    public void actualizarProducto(String idViejo, String nombre, Double precio, Integer stock, String categoria) throws IOException{
        if(idViejo==null || idViejo.isBlank()){
            throw new IllegalArgumentException("El ID es obligatorio para actualizar");
        }

        List<Producto> lista = repo.cargarTodos();
        boolean actualizado = false;
        for(int i=0; i< lista.size(); i++){
            if (lista.get(i).getId().equals(idViejo.trim())){
                Producto productoActualizado = new Producto(idViejo.trim(), nombre, precio, stock, categoria);
                lista.set(i, productoActualizado);
                actualizado = true;
                break;
            }
        }
        if(!actualizado){
            throw new IllegalArgumentException("Hubo un error al actualizar");
        }
        repo.sobrescribirArchivo(lista);
    }
}
