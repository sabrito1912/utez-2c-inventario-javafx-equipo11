package com.example.tienditademo.controllers;

import com.example.tienditademo.services.InventarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class EliminarController {
    @FXML private TextField txtIdEliminar;
    private InventarioService service = new InventarioService();

    @FXML
    public void eliminar(ActionEvent event) {
        try{
            String id = txtIdEliminar.getText();
            service.eliminarProducto(id);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro eliminado correctamente");
            cerrarVentana(event);
        }catch (IllegalArgumentException e){
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso: " + e.getMessage());
        }catch(IOException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Hubo un Error: " + e.getMessage());
        }
    }

    @FXML
    public void cancelar(ActionEvent event){
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
