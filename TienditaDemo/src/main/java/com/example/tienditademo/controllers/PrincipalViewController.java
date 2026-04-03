package com.example.tienditademo.controllers;

import com.example.tienditademo.HelloApplication;
import com.example.tienditademo.models.Producto;
import com.example.tienditademo.services.InventarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalViewController {
    private InventarioService service = new InventarioService();
    private ObservableList<Producto> listaObservable = FXCollections.observableArrayList();
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableColumn<Producto, String> colId;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        cargarTabla();

        FilteredList<Producto> datosFiltrados = new FilteredList<>(listaObservable, b -> true);

        txtBuscar.textProperty().addListener((observable, valorViejo, valorNuevo) -> {
            datosFiltrados.setPredicate(producto -> {
                if (valorNuevo == null || valorNuevo.isEmpty()) return true;
                String filtro = valorNuevo.toLowerCase();
                if (producto.getNombre().toLowerCase().contains(filtro)) return true;
                if (producto.getId().toLowerCase().contains(filtro)) return true;
                return false;
            });
        });

        SortedList<Producto> datosOrdenados = new SortedList<>(datosFiltrados);
        datosOrdenados.comparatorProperty().bind(tablaProductos.comparatorProperty());
        tablaProductos.setItems(datosOrdenados);
    }

    public void cargarTabla(){
        try{
            listaObservable.clear();
            listaObservable.addAll(service.obtenerTodos());
        }catch (IOException e){
            System.out.println("No se pudo cargar el inventario: " + e.getMessage());
        }
    }
    public void agregar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/agregar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Agregar Producto");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarTabla();

    }

    public void actualizar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/actualizar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Actualizar Producto");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarTabla();
    }

    public void eliminar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/eliminar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Eliminar Producto");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarTabla();
    }
}
