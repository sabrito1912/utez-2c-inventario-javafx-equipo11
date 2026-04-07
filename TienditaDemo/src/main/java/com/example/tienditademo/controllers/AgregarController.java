package com.example.tienditademo.controllers;

import com.example.tienditademo.services.InventarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class AgregarController {
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

    private InventarioService service = new InventarioService();

    @FXML
    public void agregar(ActionEvent event){
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            if (nombre.matches(".*\\d.*")) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Validación");
                alerta.setHeaderText(null);
                alerta.setContentText("El nombre del producto no puede contener números.");
                alerta.showAndWait();
                return;
            }
            String precio = txtPrecio.getText();
            String stock = txtStock.getText();
            String categoria = txtCategoria.getText();

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
