package com.example.tienditademo.repositories;

import com.example.tienditademo.models.Producto;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class InventarioRepository {
    private final Path pathFile = Paths.get("data", "inventario.csv");

    private void ensureFile() throws IOException {
        if (Files.notExists(pathFile)) {
            // Verifica si la carpeta principal ("data") existo si no, la crea
            if (pathFile.getParent() != null && Files.notExists(pathFile.getParent())) {
                Files.createDirectories(pathFile.getParent());
            }
            // Finalmente, crea el archivo de texto en blanco
            Files.createFile(pathFile);
        }
    }

    // Metodo para leer todos los productos del archivo y devolverlos como una lista
    public List<Producto> cargarTodos() throws IOException{
        ensureFile();
        // Lee todas las líneas de texto del archivo y las guarda en una lista de textos
        List<String> lineas = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
        List<Producto> productos = new ArrayList<>();

        for (String linea : lineas){
            if(linea.trim().isEmpty()){
                continue;
            }
            // Divide la línea de texto cada vez que encuentra una coma
            // Guarda los pedazos en un arreglo (una pequeña lista fija) llamado 'datos'
            String[] datos = linea.split(",");
            if(datos.length ==5){
                Producto p =new Producto(
                        datos[0], // ID
                        datos[1], // Nombre
                        Double.parseDouble(datos[2]), // Convierte el texto del precio a un número con decimales
                        Integer.parseInt(datos[3]), // Convierte el texto del stock a un número entero
                        datos[4] // Categoria
                );
                // Agrega el nuevo producto a la lista final
                productos.add(p);
            }
        }
        // Devuelve la lista llena de productos
        return productos;
    }

    // Metodo para guardar un solo producto nuevo al final del archivo
    public void guardarNuevo(Producto p) throws IOException{
        ensureFile();
        // Junta todos los datos del producto en un solo texto, separando cada dato con una coma
        String linea = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock() + "," + p.getCategoria();

        Files.writeString(pathFile, linea + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    // Metodo para borrar el archivo y reescribir con una lista de productos modificados
    public void sobrescribirArchivo(List<Producto> productosActualizados) throws IOException{
        ensureFile();
        List<String> lineasNuevas = new ArrayList<>();

        for (Producto p : productosActualizados) {
            // Convierte cada producto en un texto separado por comas
            String linea = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock() + "," + p.getCategoria();
            lineasNuevas.add(linea);
        }

        // Escribe toda la lista de textos en el archivo
        Files.write(pathFile, lineasNuevas,
                StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }

}

