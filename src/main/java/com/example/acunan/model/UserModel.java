package com.example.acunan.model;
import com.example.acunan.Dto.UserDto;

import java.util.ArrayList;
import java.util.Scanner;

public class UserModel {


    static Scanner teclado = new Scanner(System.in);
    static ArrayList<UserDto> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== GESTOR DE USUARIOS =====");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Modificar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Mostrar usuarios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    agregarUsuario();
                    break;
                case 2:
                    modificarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    mostrarUsuarios();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);
        teclado.close();
    }
    public static int leerEntero() {
        while (!teclado.hasNextInt()) {
            System.out.print("Ingrese un numero entero valido: ");
            teclado.nextLine();
        }

        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }
    public static UserDto buscarUsuario(int id) {
        for (UserDto usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    public static void agregarUsuario() {

        System.out.println("\n--- AGREGAR USUARIO ---");
        System.out.print("Ingrese el ID: ");
        int id = leerEntero();
        if (buscarUsuario(id) != null) {
            System.out.println("Ese ID ya existe.");
            return;
        }

        System.out.print("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = teclado.nextLine();
        System.out.print("Ingrese el correo: ");
        String correo = teclado.nextLine();
        System.out.print("Ingrese la edad: ");
        int edad = leerEntero();

        if (edad < 0) {
            System.out.println("La edad no puede ser negativa.");
            return;
        }

        UserDto nuevo = new UserDto(id, nombre, apellido, correo, edad);
        usuarios.add(nuevo);
        System.out.println("Usuario agregado correctamente.");
    }

    public static void modificarUsuario() {

        System.out.println("\n--- MODIFICAR USUARIO ---");
        System.out.print("Ingrese el ID del usuario: ");
        int id = leerEntero();
        UserDto usuario = buscarUsuario(id);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        usuario.setNombre(teclado.nextLine());
        System.out.print("Nuevo apellido: ");
        usuario.setApellido(teclado.nextLine());
        System.out.print("Nuevo correo: ");
        usuario.setCorreo(teclado.nextLine());
        System.out.print("Nueva edad: ");
        int edad = leerEntero();
        if (edad < 0) {
            System.out.println("Edad no valida. Se conserva la edad anterior.");
        } else {
            usuario.setEdad(edad);
        }
        System.out.println("Datos del usuario actualizados.");
    }

    public static void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        System.out.print("Ingrese el ID del usuario: ");
        int id = leerEntero();
        UserDto usuario = buscarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        usuarios.remove(usuario);
        System.out.println("Usuario eliminado correctamente.");
    }

    public static void mostrarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (UserDto usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
