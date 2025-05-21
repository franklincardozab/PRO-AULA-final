package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;

public class HotelDTO {

    private String id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String direccion; // Corregido aquí

    private Integer categoria;

    @Size(max = 255)
    private String descripcion;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(final String direccion) {
        this.direccion = direccion;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(final Integer categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
}