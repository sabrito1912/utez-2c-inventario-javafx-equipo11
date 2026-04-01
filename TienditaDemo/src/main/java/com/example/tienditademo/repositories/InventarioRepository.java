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
            if (pathFile.getParent() != null && Files.notExists(pathFile.getParent())) {
                Files.createDirectories(pathFile.getParent());
            }
            Files.createFile(pathFile);
        }
    }

    public List<Producto> cargarTodos() throws IOException{
        ensureFile();
        List<String> lineas = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
        List<Producto> productos = new ArrayList<>();

        for (String linea : lineas){
            if(linea.trim().isEmpty()){
                continue;
            }
            String[] datos = linea.split(",");
            if(datos.length ==5){
                Producto p =new Producto(
                        datos[0],
                        datos[1],
                        Double.parseDouble(datos[2]),
                        Integer.parseInt(datos[3]),
                        datos[4]
                );
                productos.add(p);
            }
        }
        return productos;
    }
    public void guardarNuevo(Producto p) throws IOException{
        ensureFile();
        String linea = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock() + "," + p.getCategoria();

        Files.writeString(pathFile, linea + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
    public void sobrescribirArchivo(List<Producto> productosActualizados) throws IOException{
        ensureFile();
        List<String> lineasNuevas = new ArrayList<>();

        for (Producto p : productosActualizados) {
            String linea = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock() + "," + p.getCategoria();
            lineasNuevas.add(linea);
        }

        Files.write(pathFile, lineasNuevas,
                StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }

}

