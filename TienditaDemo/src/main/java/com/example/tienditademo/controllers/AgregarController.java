package com.example.tienditademo.controllers;

import com.example.tienditademo.services.InventarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node; // Representa cualquier boton o texto en la pantalla
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;  // Representa la ventana del sistema operativo
import java.io.IOException;

public class AgregarController {
    //"Invocaciones" de la vista
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtCategoria;
    // Crea su instancia de Service para mandarle los datos guardados
    private InventarioService service = new InventarioService();

    @FXML
    public void agregar(ActionEvent event){
        try {
            // Lee los textos de todos los campos
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            // Regex revisa que no haya números en el nombre desde la interfaz
            if (nombre.matches(".*\\d.*")) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Validación");
                alerta.setHeaderText(null);
                alerta.setContentText("El nombre del producto no puede contener números.");
                alerta.showAndWait();
                return;
            }
            String precio = txtPrecio.getText();
            try {
                double precioNum = Double.parseDouble(precio);
                if (precioNum <= 0) {
                    mostrarAlerta(Alert.AlertType.ERROR, "El precio no puede ser cero o menor a 0");
                    return;
                }
            } catch (NumberFormatException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Por favor ingresa un precio numérico válido");
                return;
            }
            String stock = txtStock.getText();
            String categoria = txtCategoria.getText();
            if (categoria.matches(".*\\d.*")) {
                mostrarAlerta(Alert.AlertType.ERROR, "La categoría no puede contener números.");
                return;
            }
            // Manda todos los textos a service para que los revise y guarde
            service.agregarProducto(id, nombre, precio, stock, categoria);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Producto agregado exitosamente");
            cerrarVentana(event);
        }catch (IllegalArgumentException e){
            mostrarAlerta(Alert.AlertType.WARNING, "Error de datos: " + e.getMessage());
        }catch (IOException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al guardar: " + e.getMessage());
        }
    }

    @FXML
    public void  cancelar(ActionEvent event){
        cerrarVentana(event);
    }

    // Misma logica de alertas que en el controlador de eliminar
    private void mostrarAlerta(Alert.AlertType type, String mensaje){
        Alert alerta = new Alert(type);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void cerrarVentana(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
