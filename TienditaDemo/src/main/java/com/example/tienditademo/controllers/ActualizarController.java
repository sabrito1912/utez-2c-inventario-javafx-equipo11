package com.example.tienditademo.controllers;

import com.example.tienditademo.models.Producto;
import com.example.tienditademo.services.InventarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ActualizarController {
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
    public void buscarPorId(ActionEvent event){
        try {
            String id = txtId.getText();
            Producto p = service.buscarProductoID(id);
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(p.getPrecio() + "");
            txtStock.setText(p.getStock() + "");
            txtCategoria.setText(p.getCategoria());
            txtId.setDisable(true);
        }catch (IllegalArgumentException e){
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso: " + e.getMessage());
        }catch (IOException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al buscar: ");
        }
    }
    @FXML
    public void actualizar(ActionEvent event) {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            Double precio = Double.parseDouble(txtPrecio.getText());
            Integer stock = Integer.parseInt(txtStock.getText());
            String categoria = txtCategoria.getText();

            service.actualizarProducto(id, nombre, precio, stock, categoria);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro actualizado correctamente");

            cerrarVentana(event);
        }catch (NumberFormatException e){
            mostrarAlerta(Alert.AlertType.WARNING, "Error: El precio y stock deben ser números validos");
        } catch (IllegalArgumentException e){
            mostrarAlerta(Alert.AlertType.WARNING, "Error al actualizar: " + e.getMessage());
        }catch (IOException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error con el archivo: " + e.getMessage());
        }

    }
    @FXML
    public void cancelar(ActionEvent event) {
        cerrarVentana(event);
    }

    private void mostrarAlerta(Alert.AlertType type, String mensaje) {
        Alert alerta = new Alert(type);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
