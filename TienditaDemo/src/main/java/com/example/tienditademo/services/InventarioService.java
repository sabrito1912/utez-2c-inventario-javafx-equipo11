package com.example.tienditademo.services;
import com.example.tienditademo.models.Producto;
import com.example.tienditademo.repositories.InventarioRepository;
import java.io.IOException;
import java.util.List;

public class InventarioService {
    // Crea una conexión con el repo para poder usarlo a lo largo de la clase
    InventarioRepository repo = new InventarioRepository();
    public List<Producto>obtenerTodos() throws IOException{
        return repo.cargarTodos();
    }

    // Recibe los textos y los revisa para que no tengan errores
    public void agregarProducto(String id, String nombre, String precioStr, String stockStr, String categoria) throws IOException{
        validate(id, nombre, precioStr, stockStr, categoria);

        double precio = Double.parseDouble(precioStr);
        int stock = Integer.parseInt(stockStr);
        Producto nuevo = new Producto(id, nombre, precio, stock, categoria);
        repo.guardarNuevo(nuevo); // Le dice al repositorio que lo guarde en el archivo
    }

    // Metodo privado para validar los datos
    private void validate(String id, String nombre, String precioStr, String stockStr, String categoria) throws IOException{
        if(id==null || id.isBlank()) throw new IllegalArgumentException("El código no puede estar vacío");
        if(nombre.isBlank() || nombre.length()<3) throw new IllegalArgumentException("El código no puede estar vacío");

        //  Pide todos los productos actuales para ver si el ID ya existe
        List<Producto> existences=repo.cargarTodos();
        for (Producto p : existences){
            if(p.getId().equals(id.trim())){
                throw new IllegalArgumentException("El código de producto ya existe");
            }
        }
        try {
            // Intenta convertir el texto "precio" a un valor double
            double precio = Double.parseDouble(precioStr);
            if (precio<=0) throw new IllegalArgumentException("El precio debe ser mayor a 0");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El precio debe ser numerico");
        }
        try {
            // Intenta convertir el texto "stock" a un valor int
            int stock = Integer.parseInt(stockStr);
            if(stock<0) throw new IllegalArgumentException("El stock no puede ser negativo");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El stock debe ser un número entero");
        }
    }

    // Elimina productos
    public void eliminarProducto(String id) throws IOException{
        if( id==null || id.isBlank()){
            throw new IllegalArgumentException("Ingresa el ID del producto a eliminar");
        }
        // Carga toda la lista de productos
        List<Producto> lista = repo.cargarTodos();
        // Borra de la lista el producto que coincida con el ID
        boolean cBorro = lista.removeIf(p -> p.getId().equals(id.trim()));
        if(!cBorro){
            throw new IllegalArgumentException("No se encontró ningun producto con el ID: " + id);
        }
        // Una vez que borró el producto se reescribe la lista
        repo.sobrescribirArchivo(lista);
    }

    // Busca y encuentra productos
    public Producto buscarProductoID(String id) throws IOException{
        if(id==null || id.isBlank()){
            throw new IllegalArgumentException("Ingresa un ID para buscar");
        }
        List<Producto> lista = repo.cargarTodos();
        for (Producto p : lista){
            // Si enceuntra uno que coincida lo devuelve al momento
            if(p.getId().equals(id.trim())){
                return p;
            }
        }
        throw new IllegalArgumentException("No se encontró ningun producto con el ID: " + id);
    }

    // Actualiza los datos del producto que ya existe
    public void actualizarProducto(String idViejo, String nombre, Double precio, Integer stock, String categoria) throws IOException{
        if(idViejo==null || idViejo.isBlank()){
            throw new IllegalArgumentException("El ID es obligatorio para actualizar");
        }

        List<Producto> lista = repo.cargarTodos();
        boolean actualizado = false;
        for(int i=0; i< lista.size(); i++){
            if (lista.get(i).getId().equals(idViejo.trim())){
                // Crea un producto "nuevo" con los datos actualizados
                Producto productoActualizado = new Producto(idViejo.trim(), nombre, precio, stock, categoria);
                lista.set(i, productoActualizado);
                actualizado = true;
                break;
            }
        }
        if(!actualizado){
            throw new IllegalArgumentException("Hubo un error al actualizar");
        }
        // Una vez que se actualiza el producto reescribe la lista
        repo.sobrescribirArchivo(lista);
    }
}
