package com.example.acunan.Dto;

public class UserDto {

    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public UserDto(int edad, String correo, String apellido, String nombre, int id) {
        this.edad = edad;
        this.correo = correo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.id = id;
    }
}
