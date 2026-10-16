
package com.example.acunan.controller;

import com.example.acunan.Dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidoField;

    @FXML
    private TextField correoField;

    @FXML
    private TextField edadField;

    @FXML
    private TableView<UserDto> tablaUsuarios;

    @FXML
    private TableColumn<UserDto, Integer> colId;

    @FXML
    private TableColumn<UserDto, String> colNombre;

    @FXML
    private TableColumn<UserDto, String> colApellido;

    @FXML
    private TableColumn<UserDto, String> colCorreo;

    @FXML
    private TableColumn<UserDto, Integer> colEdad;

    @FXML
    private Label mensajeLabel;

    private final ObservableList<UserDto> usuarios =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colApellido.setCellValueFactory(
                new PropertyValueFactory<>("apellido"));

        colCorreo.setCellValueFactory(
                new PropertyValueFactory<>("correo"));

        colEdad.setCellValueFactory(
                new PropertyValueFactory<>("edad"));

        tablaUsuarios.setItems(usuarios);

        // Al seleccionar una fila, cargar sus datos en los campos
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionado) -> {
                    if (seleccionado != null) {
                        idField.setText(
                                String.valueOf(seleccionado.getId()));
                        nombreField.setText(seleccionado.getNombre());
                        apellidoField.setText(seleccionado.getApellido());
                        correoField.setText(seleccionado.getCorreo());
                        edadField.setText(
                                String.valueOf(seleccionado.getEdad()));
                    }
                });
    }

    @FXML
    private void agregarUsuario() {

        try {
            int id = Integer.parseInt(idField.getText().trim());
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String correo = correoField.getText().trim();
            int edad = Integer.parseInt(edadField.getText().trim());

            if (nombre.isEmpty() || apellido.isEmpty()
                    || correo.isEmpty()) {
                mostrarAlerta("Error",
                        "Completa todos los campos.");
                return;
            }

            if (id < 0 || edad < 0) {
                mostrarAlerta("Error",
                        "El ID y la edad no pueden ser negativos.");
                return;
            }

            if (buscarUsuario(id) != null) {
                mostrarAlerta("Error",
                        "Ya existe un usuario con ese ID.");
                return;
            }

            UserDto nuevo = new UserDto(
                    id, nombre, apellido, correo, edad);

            usuarios.add(nuevo);
            tablaUsuarios.refresh();
            limpiarCampos();

            mensajeLabel.setText(
                    "Usuario agregado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error",
                    "El ID y la edad deben ser números enteros.");
        }
    }

    // MODIFICAR USUARIO
    @FXML
    private void modificarUsuario() {

        try {
            int id = Integer.parseInt(idField.getText().trim());

            UserDto usuario = buscarUsuario(id);

            if (usuario == null) {
                mostrarAlerta("Error",
                        "No se encontró el usuario.");
                return;
            }

            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String correo = correoField.getText().trim();
            int edad = Integer.parseInt(edadField.getText().trim());

            if (nombre.isEmpty() || apellido.isEmpty()
                    || correo.isEmpty()) {
                mostrarAlerta("Error",
                        "Completa todos los campos.");
                return;
            }

            if (edad < 0) {
                mostrarAlerta("Error",
                        "La edad no puede ser negativa.");
                return;
            }

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);
            usuario.setEdad(edad);

            tablaUsuarios.refresh();
            limpiarCampos();

            mensajeLabel.setText(
                    "Usuario modificado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error",
                    "Ingresa un ID y una edad válidos.");
        }
    }

    // ELIMINAR USUARIO
    @FXML
    private void eliminarUsuario() {

        try {
            int id = Integer.parseInt(idField.getText().trim());

            UserDto usuario = buscarUsuario(id);

            if (usuario == null) {
                mostrarAlerta("Error",
                        "No se encontró el usuario.");
                return;
            }

            usuarios.remove(usuario);
            limpiarCampos();

            mensajeLabel.setText(
                    "Usuario eliminado correctamente.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error",
                    "Ingresa un ID válido para eliminar.");
        }
    }

    // LIMPIAR CAMPOS
    @FXML
    private void limpiarCampos() {
        idField.clear();
        nombreField.clear();
        apellidoField.clear();
        correoField.clear();
        edadField.clear();

        tablaUsuarios.getSelectionModel().clearSelection();
    }

    // BUSCAR USUARIO POR ID
    private UserDto buscarUsuario(int id) {

        for (UserDto usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    // MOSTRAR MENSAJES DE ERROR
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
