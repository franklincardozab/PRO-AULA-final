package io.bootify.connect_stay_book_new.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "hotel")
public class Hotel {

    @Id
    private String id;

    private String nombre;
    private String direccion; // Corregido "dirreccion" -> "direccion"
    private Integer categoria;
    private String descripcion;

    @DBRef
    private Set<Habitacion> habitaciones; // Referencia a habitaciones

    // Getters y Setters

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

    public Set<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(final Set<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}