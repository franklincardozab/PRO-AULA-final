package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    private String id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String contrasena;

    @Size(max = 255)
    private String telefono;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(final String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

}